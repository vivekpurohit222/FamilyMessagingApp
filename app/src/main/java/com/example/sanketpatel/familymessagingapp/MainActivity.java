package com.example.sanketpatel.familymessagingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener {

    GoogleApiClient googleApiClient;
    SignInButton signInButton;
    Button logout;
    int req=9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logout=(Button)findViewById(R.id.logout);
        signInButton=(SignInButton)findViewById(R.id.login_press);
        signInButton.setOnClickListener(this);
        logout.setOnClickListener(this);
        GoogleSignInOptions signInOptions=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient=new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions).build();



    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.login_press:
                signin();
                break;
            case R.id.logout:
                signout();
                break;
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    void signin()
    {
        Intent intent=Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent,req);
    }

    void signout()
    {

        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {

            }
        });
    }
    void handleresult(GoogleSignInResult googleSignInResult)
    {
        if(googleSignInResult.isSuccess()){
            GoogleSignInAccount account=googleSignInResult.getSignInAccount();
            Toast.makeText(MainActivity.this,""+account.getDisplayName(),Toast.LENGTH_LONG).show();
            Intent i=new Intent(MainActivity.this,Chooser.class);
            startActivity(i);
        }
    }


    void updateUT(boolean islogin)
    {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==req){
            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleresult(result);

        }
    }


}
