package com.example.tugas1_bunga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class DetailActivity extends AppCompatActivity {
    private ImageView imgPhoto;
    private TextView tvNama, tvDesc;

    public static final String ITEM_EXTRA = "item_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imgPhoto = findViewById(R.id.iv_item_photo);
        tvNama = findViewById(R.id.tv_item_name);
        tvDesc = findViewById(R.id.tv_item_description);


                Artis artis = getIntent().getParcelableExtra(ITEM_EXTRA);
                if (artis != null) {
                    Glide.with(this)
                            .load(artis.getPhoto())
                            .into(imgPhoto);
                    tvNama.setText(artis.getName());
                    tvDesc.setText(artis.getDescription());
                }

                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle("Profil Artis Hollywood");
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
            }
        }