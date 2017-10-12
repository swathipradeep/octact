package com.techpalle.octact;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    TextView tv1,tv2,tv3,tv4;
    Button Edit;
    String fname,lname,email,mobile,place;
    //declare mydatabase variable
    MyDatabase m;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View v=inflater.inflate(R.layout.fragment_home, container, false);
        tv1= (TextView) v.findViewById(R.id.textView);
        tv2= (TextView) v.findViewById(R.id.textView2);
        tv3= (TextView) v.findViewById(R.id.textView3);
        tv4= (TextView) v.findViewById(R.id.textView4);
        Edit= (Button) v.findViewById(R.id.editProfile);

        Bundle b=getArguments();
        String userEmail=b.getString("email");
        //2. create database object
        m = new MyDatabase(getActivity());
        //3. open the database
        m.open();
        //cursor is used to read the data from table
        Cursor c = m.fetchUserDetails(userEmail);
        //now cursor contains data coming from table, let us read row by row.
        StringBuilder sb = new StringBuilder();
        if(c != null){
            while(c.moveToNext() == true){
                 fname = c.getString(1);
                 lname = c.getString(2);
                 email = c.getString(3);
                 mobile = c.getString(5);
                 place = c.getString(6);
            }
        }
        tv1.setText(fname +" "+ lname);
        tv2.setText("Email : "+email);
        tv3.setText("Mobile: "+mobile);
        tv4.setText("Place : "+place);
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity= (MainActivity) getActivity();
                mainActivity.sendData(fname,lname,email,mobile,place);

            }
        });


        return v;
    }

}
