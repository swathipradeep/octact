package com.techpalle.octact;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Login l=new Login();
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction t=manager.beginTransaction();
        t.add(R.id.container,l);
        t.commit();

    }

    public void passData() {
        Login l=new Login();
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction t=manager.beginTransaction();
        t.replace(R.id.container,l);
        t.addToBackStack(null);
        t.commit();

    }

    public void data() {
        RegisterFragment r=new RegisterFragment();
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction t=manager.beginTransaction();
        t.replace(R.id.container,r);
        t.addToBackStack(null);
        t.commit();
    }

    public void data2(String email) {
        HomeFragment h=new HomeFragment();
        Bundle b=new Bundle();
        b.putString("email",email);
        h.setArguments(b);
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction t=manager.beginTransaction();
        t.addToBackStack(null);
        t.replace(R.id.container,h);
        t.commit();
    }

    public void sendData(String fname, String lname, String email, String mobile, String place) {
       EditProfile ep=new EditProfile();
        Bundle b=new Bundle();
        b.putString("fname",fname);
        b.putString("lname",lname);
        b.putString("email",email);
        b.putString("mobile",mobile);
        b.putString("place",place);
        ep.setArguments(b);
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction t=manager.beginTransaction();
        t.replace(R.id.container,ep);
        t.addToBackStack(null);
        t.commit();
    }

    public void redirect(String userMail) {
        HomeFragment h=new HomeFragment();
        Bundle b=new Bundle();
        b.putString("email",userMail);
        h.setArguments(b);
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction t=manager.beginTransaction();
        t.replace(R.id.container,h);
        t.addToBackStack(null);
        t.commit();

    }
}
