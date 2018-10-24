package com.example.sanketpatel.familymessagingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.sanketpatel.familymessagingapp.Geo.currentLatitude;
import static com.example.sanketpatel.familymessagingapp.Geo.currentLongitude;

public class Entry extends AppCompatActivity {

    EditText editText,editText2;
    TextView word;
    Button b,b2;
    DatabaseReference rootRef,demoRef,demoref2,demoref3,demoref4;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        editText=(EditText)findViewById(R.id.write);
        editText2=(EditText)findViewById(R.id.desc);
        b=(Button)findViewById(R.id.press);
        b2=(Button)findViewById(R.id.fetch);
        rootRef = FirebaseDatabase.getInstance().getReference("student");
        demoRef = rootRef.child("Word");
        demoref2 = rootRef.child("Description");
        demoref3 = rootRef.child("Lattitude");
        demoref4 = rootRef.child("Longitude");

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                demoRef.push().setValue(editText.getText().toString());
                demoref2.push().setValue(editText2.getText().toString());
                String s=""+currentLatitude;
                String s2=""+currentLongitude;
                demoref3.push().setValue(s);
                demoref4.push().setValue(s2);
                Toast.makeText(Entry.this,"Submitted",Toast.LENGTH_SHORT).show();


            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference ref1= FirebaseDatabase.getInstance().getReference();
                DatabaseReference ref2,ref3,ref4;
                ref2 = ref1.child("student").child("Word");

                ref2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ArrayList<String> Userlist = new ArrayList<String>();
                        String s="";
                        // Result will be holded Here
                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                            Userlist.add(String.valueOf(dsp.getValue())); //add result into array list
                            s=s+String.valueOf(dsp.getValue());
                        }


                        TextView t=(TextView)findViewById(R.id.word);
                        t.setText(s);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}
