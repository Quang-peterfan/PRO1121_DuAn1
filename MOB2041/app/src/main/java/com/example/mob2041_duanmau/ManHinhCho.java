package com.example.mob2041_duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ManHinhCho extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_cho);

        ImageView ivLogo = findViewById(R.id.ivLogo);
        Glide.with(this).load(R.mipmap.juggling).into(ivLogo); // truyền h_ả lên

        new Handler().postDelayed(new Runnable() { // delay 2s
            @Override
            public void run() {
                startActivity(new Intent(ManHinhCho.this,Login_Activity.class));

            }
        },2000);
    }
}