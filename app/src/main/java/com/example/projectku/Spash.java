package com.example.projectku;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Spash extends AppCompatActivity {
    private int waktu_loading=1000;

    //4000=4 detik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_spash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //setelah loading maka akan langsung berpindah ke login activity
                Intent login=new Intent(Spash.this, LoginActivity.class);
                startActivity(login);
                finish();

            }
        },waktu_loading);
    }
}

