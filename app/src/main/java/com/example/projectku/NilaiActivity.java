package com.example.projectku;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.appcompat.app.AppCompatActivity;

public class NilaiActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        TableLayout tableLayoutid = (TableLayout) this.findViewById(R.id.tabelayoutid);
        TableRow row = (TableRow) getLayoutInflater().inflate(R.layout.layout_row,null);
        tableLayoutid.addView(row);
        return true;
    }
}
