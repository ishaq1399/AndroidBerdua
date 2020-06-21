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
import com.example.projectku.mapel.MapelAdapter;
import com.example.projectku.mapel.MapelModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MapelActivity extends AppCompatActivity implements MapelAdapter.OnMapelClickListener {

    public RecyclerView rv;
    public MapelAdapter mapelAdapter;
    public RecyclerView.LayoutManager layoutManager;
    public List<MapelModel> listMapelModel = new ArrayList<>();

    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapel);

        requestQueue = Volley.newRequestQueue(this);
        progressDialog = new ProgressDialog(this);
        sessionManager = new SessionManager(this);

        rv = findViewById(R.id.rvMapel);

        loadMapel();
    }

    @Override
    public void onClick(View view, int position) {
        MapelModel mapelModel = listMapelModel.get(position);
        Toast.makeText(this, mapelModel.getNamaMapel(),
                Toast.LENGTH_LONG).show();
    }

    private void loadMapel() {
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String url = ApiEndpoints.MAPEL + sessionManager.getIdPengguna();

        StringRequest mapelRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray dataMapel = jsonObject.getJSONArray("mapel");

                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                    for (int i = 0; i < dataMapel.length(); i++) {
                        JSONObject barisMapel = dataMapel.getJSONObject(i);

                        String id = barisMapel.getString("id");
                        String hari = barisMapel.getString("hari_id");
                        String nama = barisMapel.getString("nama");
                        String jamMulai = barisMapel.getString("jam_mulai");
                        String jamSelesai = barisMapel.getString("jam_selesai");
                        String pengajar = barisMapel.getString("pengajar");

                        listMapelModel.add(new MapelModel(id, hari, jamMulai, jamSelesai, pengajar, nama));
                    }

                    mapelAdapter = new MapelAdapter(listMapelModel);
                    mapelAdapter.setListener(MapelActivity.this);

                    layoutManager = new LinearLayoutManager(MapelActivity.this);
                    rv.setAdapter(mapelAdapter);
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

        requestQueue.add(mapelRequest);
    }
}
