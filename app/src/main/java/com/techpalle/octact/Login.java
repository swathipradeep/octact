package com.techpalle.octact;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment {
    EditText et1,et2;
    Button b1,b2;
    //declare mydatabase variable
    MyDatabase m;

    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_login, container, false);

        //create database object
        m = new MyDatabase(getActivity());
        //open the database
        m.open();

        et1= (EditText) v.findViewById(R.id.editText);
        et2= (EditText) v.findViewById(R.id.editText2);
        b1= (Button) v.findViewById(R.id.button);
        b2= (Button) v.findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et1.getText().toString();
                String password = et2.getText().toString();
                // If user exist, fetch the password and return it
                String pwd = m.doLogin(email);
                if( pwd== "NOT EXIST"){
                    Toast.makeText(getActivity(),"Account does not exist",Toast.LENGTH_SHORT).show();
                }else if(pwd.equals(password)){
                    MainActivity m = (MainActivity) getActivity();
                    m.data2(email);
                }else {
                    Toast.makeText(getActivity(),"Incorrect email or password",Toast.LENGTH_SHORT).show();
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    MainActivity m = (MainActivity) getActivity();
                    m.data();
                }
        });
        return v;
    }

    }
