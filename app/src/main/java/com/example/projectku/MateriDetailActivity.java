package com.example.projectku;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MateriDetailActivity extends AppCompatActivity {
    String id_materi, namaMateri, url;

    WebView webDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi_detail);

        webDetail = findViewById(R.id.webMateriDetail);
        WebSettings webSettings = webDetail.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webDetail.setWebViewClient(new WebViewClient());

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            id_materi = extras.getString("ID_MATERI");
            namaMateri = extras.getString("NAMA_MATERI");
            url = ApiEndpoints.MATERI_DETAIL + id_materi;
        }

        getSupportActionBar().setTitle(namaMateri);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webDetail.loadUrl(url);

        webDetail.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                DownloadManager.Request request = new DownloadManager.Request(
                        Uri.parse(url));

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webDetail.canGoBack()) {
            webDetail.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }
}