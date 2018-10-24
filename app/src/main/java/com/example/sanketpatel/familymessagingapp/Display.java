package com.example.sanketpatel.familymessagingapp;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class Display extends AppCompatActivity {

    private ViewPager viewPager;
    private SlideAdapter slideAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        viewPager=(ViewPager)findViewById(R.id.pager);
        slideAdapter=new SlideAdapter(this,Chooser.Userlist,Chooser.Userlist2);
        viewPager.setAdapter(slideAdapter);

    }
}
