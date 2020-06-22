package com.example.projectku;

import android.app.ProgressDialog;
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

import com.example.projectku.nilai.NilaiAdapter;
import com.example.projectku.nilai.NilaiModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NilaiActivity extends AppCompatActivity implements NilaiAdapter.OnNilaiClickListener {

    public RecyclerView rv;
    public NilaiAdapter nilaiAdapter;
    public RecyclerView.LayoutManager layoutManager;
    public List<NilaiModel> listNilaiModel = new ArrayList<>();

    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai);

        requestQueue = Volley.newRequestQueue(this);
        progressDialog = new ProgressDialog(this);
        sessionManager = new SessionManager(this);

        rv = findViewById(R.id.rvNilai);

        loadNilai();
    }

    @Override
    public void onClick(View view, int position) {
        NilaiModel nilaiModel = listNilaiModel.get(position);
        Toast.makeText(this, nilaiModel.getNilai(),
                Toast.LENGTH_LONG).show();
    }

    private void loadNilai() {
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String url = ApiEndpoints.NILAI + sessionManager.getIdPengguna();

        StringRequest nilaiRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray arrNilai = jsonObject.getJSONArray("nilai");

                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                    for (int i = 0; i < arrNilai.length(); i++) {
                        JSONObject barisNilai = arrNilai.getJSONObject(i);
                        JSONObject objTugas = barisNilai.getJSONObject("tugas");
                        JSONObject objHistory = barisNilai.getJSONObject("history");


                        String id_nilai = objTugas.getString("id");
                        String judul = objTugas.getString("judul");
                        String nilai = objTugas.getString("nilai");
                        String waktu = objHistory.getString("mulai");

                        listNilaiModel.add(new NilaiModel(id_nilai, judul, nilai, waktu));
                    }

                    nilaiAdapter = new NilaiAdapter(listNilaiModel);
                    nilaiAdapter.setListener(NilaiActivity.this);

                    layoutManager = new LinearLayoutManager(NilaiActivity.this);
                    rv.setAdapter(nilaiAdapter);
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

        requestQueue.add(nilaiRequest);
    }
}