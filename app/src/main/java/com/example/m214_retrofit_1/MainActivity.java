package com.example.m214_retrofit_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://run.mocky.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieApi movieApi = retrofit.create(MovieApi.class);

        Call<List<Movie>> call = movieApi.getMovies();

        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if(response.code()==200)
                {
                    List<Movie> movies = response.body();
                    save_Localy(movies);
                }



            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error contacting API", Toast.LENGTH_SHORT).show();
            }


        });




        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, new First()).commit();




    }

    private void save_Localy(List<Movie> movies) {

        DBHelper db = new DBHelper(getApplicationContext());
        db.clear_All();
        for(Movie m : movies) {
            db.add_Movie(m);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.main_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {

            case R.id.item_afficher:

                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new First()).commit();
                return true;
            case R.id.item_ajout:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new Second()).commit();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}