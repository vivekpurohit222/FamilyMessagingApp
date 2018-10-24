package com.example.sanketpatel.familymessagingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Chooser extends AppCompatActivity {
    ImageButton add,display;
    static ArrayList<String> Userlist = new ArrayList<String>();
    static ArrayList<String> Userlist2 = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);

        add=(ImageButton)findViewById(R.id.newentry);
        display=(ImageButton)findViewById(R.id.display);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(Chooser.this,Entry.class);
                startActivity(i);

            }
        });


        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference ref1= FirebaseDatabase.getInstance().getReference();
                DatabaseReference ref2,ref3,ref4;
                ref2 = ref1.child("student").child("Word");
                ref3 = ref1.child("student").child("Description");

                ref2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Userlist = new ArrayList<String>();
                        String s="";
                        // Result will be holded Here
                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                            Userlist.add(String.valueOf(dsp.getValue())); //add result into array list
                            s=s+String.valueOf(dsp.getValue());
                        }

                        // Intent i=new Intent(Chooser.this,MainActivity.class);
                        //startActivity(i);


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



                ref3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Userlist2 = new ArrayList<String>();
                        String s="";
                        // Result will be holded Here
                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                            Userlist2.add(String.valueOf(dsp.getValue())); //add result into array list
                            s=s+String.valueOf(dsp.getValue());
                        }

                        Intent i=new Intent(Chooser.this,Display.class);
                        startActivity(i);


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



            }
        });

    }
}
