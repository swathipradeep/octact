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
public class RegisterFragment extends Fragment {
    EditText etFirstName,etLastName,etEmail,etPassword,etCPassword,etMobile,etPlace;
    Button btnRegister;

    //declare mydatabase variable
    MyDatabase m;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_register, container, false);

        //create database object
        m = new MyDatabase(getActivity());
        //open the database
        m.open();

        etFirstName= (EditText) v.findViewById(R.id.firstName);
        etLastName= (EditText) v.findViewById(R.id.lastName);
        etEmail= (EditText) v.findViewById(R.id.email);
        etPassword= (EditText) v.findViewById(R.id.password);
        etCPassword= (EditText) v.findViewById(R.id.cpassword);
        etMobile= (EditText) v.findViewById(R.id.mobile);
        etPlace= (EditText) v.findViewById(R.id.place);
        btnRegister= (Button) v.findViewById(R.id.btnRegister);
        // On click, register the user
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = etFirstName.getText().toString();
                String lname = etLastName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String cpassword = etCPassword.getText().toString();
                String mobile = etMobile.getText().toString();
                String place = etPlace.getText().toString();

                if(fname.equals("")||lname.equals("") || email.equals("")  || password.equals("") ||cpassword.equals("") ||mobile.equals("") || place.equals("")){
                     Toast.makeText(getActivity(),"Enter all fields",Toast.LENGTH_SHORT).show();
                }else if(password.equals(cpassword)){
                    if(!m.isUserExist(email)){
                        m.registerUser(fname,lname,email,password, Long.parseLong(mobile),place);
                        MainActivity mainActivity= (MainActivity) getActivity();
                        mainActivity.passData();
                        Toast.makeText(getActivity(),"Registraton success",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(),"User already exist",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getActivity(),"Password miss match",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }

}
