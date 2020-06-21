package com.example.projectku;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projectku.kelas.KelasAdapter;
import com.example.projectku.kelas.KelasModel;
import com.example.projectku.materi.MateriAdapter;
import com.example.projectku.materi.MateriModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MateriActivity extends AppCompatActivity implements MateriAdapter.OnMateriClickListener {

    public RecyclerView rv;
    public MateriAdapter materiAdapter;
    public RecyclerView.LayoutManager layoutManager;
    public List<MateriModel> listMateriModel = new ArrayList<>();

    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);

        rv = findViewById(R.id.rvMateri);
        requestQueue = Volley.newRequestQueue(this);
        progressDialog = new ProgressDialog(this);
        sessionManager = new SessionManager(this);

        loadMateri();
    }

    @Override
    public void onClick(View view, int position) {
        MateriModel materiModel = listMateriModel.get(position);
        Toast.makeText(this, materiModel.getNamamateri(),
                Toast.LENGTH_LONG).show();

        Intent webMateri = new Intent(MateriActivity.this, MateriDetailActivity.class);
        webMateri.putExtra("ID_MATERI", materiModel.getIdMateri());
        webMateri.putExtra("NAMA_MATERI", materiModel.getNamamateri());
        startActivity(webMateri);
    }

    private void loadMateri() {
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String url = ApiEndpoints.MATERI + sessionManager.getIdPengguna();

        StringRequest kelasRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray dataMateri = jsonObject.getJSONArray("materi");

                    String gambar = ApiEndpoints.FOTO_ASSETS+jsonObject.getString("gambar");

                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                    for (int i = 0 ; i < dataMateri.length(); i++) {
                        JSONObject materi = dataMateri.getJSONObject(i);

                        String id = materi.getString("id");
                        String judul = materi.getString("judul");
                        String namaPengajar = materi.getString("nama");

                        listMateriModel.add(new MateriModel(id, judul, namaPengajar, gambar));
                    }

                    materiAdapter = new MateriAdapter(listMateriModel);
                    materiAdapter.setListener(MateriActivity.this);
                    layoutManager = new LinearLayoutManager(getApplicationContext());

                    rv.setAdapter(materiAdapter);
                    rv.setLayoutManager(layoutManager);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(kelasRequest);
    }

}
