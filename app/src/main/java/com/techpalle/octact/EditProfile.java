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
public class EditProfile extends Fragment {
    EditText fname,lname,mobile,place;
    Button submit;
    Bundle b;
    //declare mydatabase variable
    MyDatabase m;

    public EditProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_edit_profile, container, false);
        //create database object
        m = new MyDatabase(getActivity());
        //open the database
        m.open();

        fname= (EditText) v.findViewById(R.id.fname);
        lname= (EditText) v.findViewById(R.id.lname);
        //email= (EditText) v.findViewById(R.id.email);
        mobile= (EditText) v.findViewById(R.id.mobile);
        place= (EditText) v.findViewById(R.id.place);
        submit= (Button) v.findViewById(R.id.editProfile);
        b=getArguments();
        fname.setText(b.getString("fname"));
        lname.setText(b.getString("lname"));
        //email.setText("Email :"+b.getString("email"));
        mobile.setText(b.getString("mobile"));
        place.setText(b.getString("place"));
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = fname.getText().toString();
                String lastName = lname.getText().toString();
                String userMail = b.getString("email");
                String mobileNumber = mobile.getText().toString();
                String placeName = place.getText().toString();
                int response = m.updateUserProfile(firstName,lastName,userMail,Long.parseLong(mobileNumber),placeName);
                if(response > 0){
                    Toast.makeText(getActivity(),"Profile updated",Toast.LENGTH_SHORT).show();
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.redirect(userMail);
                }
            }
        });
        return v;
    }

}
