package com.example.tugas1_bunga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void message(View view) {
        String nomor = "080987654321" ;
        Intent message = new Intent(Intent.ACTION_VIEW);
        message.setData(Uri.fromParts("sms", nomor, null));
        startActivity(message);
    }
}