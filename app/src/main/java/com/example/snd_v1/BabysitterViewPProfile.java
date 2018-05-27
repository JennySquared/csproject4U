package com.example.snd_v1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BabysitterViewPProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_babysitter_view_pprofile);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Users/Babysitter");


        final int tag = (getIntent().getExtras().getInt("p"));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_view_bprofile);

        myRef.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String name = dataSnapshot.child(tag+"").child("name").getValue(String.class);
                String postalCode = dataSnapshot.child(tag+"").child("address").getValue(String.class);
                String age = dataSnapshot.child(tag+"").child("age").getValue(String.class);
                String qualifications = "\n\nQualifications:\n"+ dataSnapshot.child(tag+"").child("qualifications").getValue(String.class);
                String bio = "\n\nBio:\n"+ dataSnapshot.child(tag+"").child("bio").getValue(String.class);
                String ratings = dataSnapshot.child(tag+"").child("rating").getValue(String.class);
                TextView n = findViewById(R.id.name);
                n.setText(name);
                TextView a = findViewById(R.id.age);
                a.setText(age);
                TextView r = findViewById(R.id.tEnd);
                r.setText(ratings);
                TextView pc = findViewById(R.id.textView6);
                pc.setText(postalCode);
                TextView b = findViewById(R.id.bio);
                b.setText(bio);
                TextView q = findViewById(R.id.child);
                q.setText(qualifications);
                Toast.makeText(getApplicationContext(),"You are viewing " + name+"'s Profile",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        name.setText(tag+"");
    }
}