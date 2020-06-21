package com.example.projectku;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class AccountActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    SessionManager sessionManager;
    RequestQueue requestQueue;

    TextView textNis, textNama, textJK, textTempat, textTanggal, textAgama, textAlamat, textTahun;
    ImageView fotoUser;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        initViews();

        id = sessionManager.getIdPengguna();

        loadDataAkun();
    }

    private void initViews(){
        textNis = findViewById(R.id.txtNis);
        textNama = findViewById(R.id.txtNama);
        textJK = findViewById(R.id.txtJenisKelamin);
        textTanggal = findViewById(R.id.txtTanggalLahir);
        textTempat = findViewById(R.id.txtTempatLahir);
        textAgama = findViewById(R.id.txtAgama);
        textAlamat = findViewById(R.id.txtAlamat);
        textTahun = findViewById(R.id.txtTahunMasuk);
        fotoUser = findViewById(R.id.fotoUser);

        sessionManager = new SessionManager(this);
        progressDialog = new ProgressDialog(this);
        requestQueue = Volley.newRequestQueue(this);
    }


    private void loadDataAkun() {
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String url = ApiEndpoints.AKUN + id;

        StringRequest akunRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    String message = jsonObject.getString("message");

                    if (status.equals("true")) {
                        JSONObject user = jsonObject.getJSONObject("user");

                        String nis = user.getString("nis");
                        String nama = user.getString("nama");
                        String jk = user.getString("jenis_kelamin");
                        String tempat = user.getString("tempat_lahir");
                        String tanggal = user.getString("tgl_lahir");
                        String agama = user.getString("agama");
                        String alamat = user.getString("alamat");
                        String tahun = user.getString("tahun_masuk");
                        String foto = ApiEndpoints.FOTO_PATH + user.getString("foto");

//                        set value dari tiap text dan imageview
                        Picasso.get().load(foto).into(fotoUser);

                        textNis.setText(nis);
                        textNama.setText(nama);
                        textJK.setText(jk);
                        textTempat.setText(tempat);
                        textTanggal.setText(tanggal);
                        textAgama.setText(agama);
                        textAlamat.setText(alamat);
                        textTahun.setText(tahun);

                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }

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

        requestQueue.add(akunRequest);
    }
}