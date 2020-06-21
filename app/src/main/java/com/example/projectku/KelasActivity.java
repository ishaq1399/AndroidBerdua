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
import com.example.projectku.kelas.KelasAdapter;
import com.example.projectku.kelas.KelasModel;
import com.example.projectku.materi.MateriAdapter;
import com.example.projectku.materi.MateriModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class KelasActivity extends AppCompatActivity implements KelasAdapter.OnKelasClickListener {

    public RecyclerView rv;
    public KelasAdapter kelasAdapter;
    public RecyclerView.LayoutManager layoutManager;
    public List<KelasModel> listKelasModel = new ArrayList<>();

    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelas);

        requestQueue = Volley.newRequestQueue(this);
        progressDialog = new ProgressDialog(this);
        sessionManager = new SessionManager(this);

        rv = findViewById(R.id.rvKelas);

        loadKelas();
    }

    @Override
    public void onClick(View view, int position) {
        KelasModel kelasModel = listKelasModel.get(position);
        Toast.makeText(this, kelasModel.getNamaSiswa(),
                Toast.LENGTH_LONG).show();
    }

    private void loadKelas() {
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String url = ApiEndpoints.KELAS + sessionManager.getIdPengguna();

        StringRequest kelasRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray dataKelas = jsonObject.getJSONArray("anggota_kelas");

                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                    for (int i = 0 ; i < dataKelas.length(); i++) {
                        JSONObject namaSiswa = dataKelas.getJSONObject(i);

                        String nomor = String.valueOf(i+1);
                        String nama = namaSiswa.getString("nama");

                        listKelasModel.add(new KelasModel(nomor, nama));
                    }

                    kelasAdapter = new KelasAdapter(listKelasModel);
                    kelasAdapter.setListener(KelasActivity.this);

                    layoutManager = new LinearLayoutManager(getApplicationContext());
                    rv.setAdapter(kelasAdapter);
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
