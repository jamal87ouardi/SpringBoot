package com.example.m214_retrofit_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://run.mocky.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookApi bookApi = retrofit.create(BookApi.class);

        Call<List<Book>> call = bookApi.getBooks();

        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if(response.code()==200)
                {
                    List<Book> books = response.body();

                    save_Localy(books);

                }



            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Echec de connexion", Toast.LENGTH_SHORT).show();
            }


        });


    }

    private void save_Localy(List<Book> books) {

        DBHelper db = new DBHelper(getApplicationContext());
        db.clear_All();
        for(Book b : books) {
            db.add_Book(b);
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
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new Premier()).commit();
                return true;

            case R.id.item_ajout:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new Deuxieme()).commit();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}