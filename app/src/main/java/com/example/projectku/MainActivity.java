package com.example.projectku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView Mapel = findViewById(R.id.Mapel);
        Mapel.setOnClickListener(new View.OnClickListener() {

                                     @Override
                                     public void onClick(View v) {
                                         Intent intent = new Intent(MainActivity.this, MapelActivity.class);
                                         startActivity(intent);
                                     }
                                 }
        );

        ImageView Materi = findViewById(R.id.Materi);
        Materi.setOnClickListener(new View.OnClickListener() {

                                      @Override
                                      public void onClick(View v) {
                                          Intent intent = new Intent(MainActivity.this, MateriActivity.class);
                                          startActivity(intent);
                                      }
                                  }
        );

        ImageView kelas = findViewById(R.id.kelas);
        kelas.setOnClickListener(new View.OnClickListener() {

                                     @Override
                                     public void onClick(View v) {
                                         Intent intent = new Intent(MainActivity.this, KelasActivity.class);
                                         startActivity(intent);
                                     }
                                 }
        );
        ImageView Account = findViewById(R.id.Account);
        Account.setOnClickListener(new View.OnClickListener() {

                                       @Override
                                       public void onClick(View v) {
                                           Intent intent = new Intent(MainActivity.this, AccountActivity.class);
                                           startActivity(intent);
                                       }
                                   }
        );
        ImageView Setting = findViewById(R.id.Setting);
        Setting.setOnClickListener(new View.OnClickListener() {

                                       @Override
                                       public void onClick(View v) {
                                           Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                                           startActivity(intent);
                                       }
                                   }
        );
        ImageView Nilai = findViewById(R.id.Nilai);
        Nilai.setOnClickListener(new View.OnClickListener() {

                                     @Override
                                     public void onClick(View v) {
                                         Intent intent = new Intent(MainActivity.this, NilaiActivity.class);
                                         startActivity(intent);
                                     }
                                 }
        );
    }
}