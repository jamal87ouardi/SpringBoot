package com.example.m214_retrofit_1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Second#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Second extends Fragment {


    EditText id, nom ,image;
    Button btn;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Second() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Second.
     */
    // TODO: Rename and change types and number of parameters
    public static Second newInstance(String param1, String param2) {
        Second fragment = new Second();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_second, container, false);


        id=v.findViewById(R.id.ed_id);
        nom=v.findViewById(R.id.ed_nom);
        image=v.findViewById(R.id.ed_image);
        btn = v.findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper db = new DBHelper(getActivity());



                String name = nom.getText().toString();

                String url = image.getText().toString();

                if (id.getText().toString().equals("") || name.equals("") || url.equals("")) {

                    Toast.makeText(getActivity(), "Tous les champs sont requis", Toast.LENGTH_LONG).show();

                }

                else {

                    int input_id = Integer.parseInt(id.getText().toString());

                    if (db.search(input_id))
                        Toast.makeText(getActivity(), "ID éxiste déja", Toast.LENGTH_LONG).show();

                    else {

                        Movie m = new Movie();

                        m.setId(input_id);

                        m.setName(name);

                        m.setImage(url);

                        long r = db.add_Movie(m);

                        if (r != -1) {
                            Toast.makeText(getActivity(), "Ajouté avec succès", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getActivity(), "Echec d'ajout", Toast.LENGTH_LONG).show();
                        }
                    }
                }

            }
        });




        return v;
    }
}