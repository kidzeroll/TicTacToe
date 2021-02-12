package com.kidzeroll.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class activity_splash extends AppCompatActivity {

    ImageView imgSplash;
    TextView txtName, txtCopyright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imgSplash = findViewById(R.id.imageView);
        txtName = findViewById(R.id.textView);
        txtCopyright = findViewById(R.id.textView2);

        //fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //animasi
        txtName.setAlpha(0f);
        txtName.animate()
                .translationY(txtName.getHeight())
                .alpha(1f)
                .setStartDelay(1000)
                .setDuration(1200);

        txtCopyright.setAlpha(0f);
        txtCopyright.animate()
                .translationY(txtName.getHeight())
                .alpha(1f)
                .setStartDelay(1500)
                .setDuration(1000);

        imgSplash.setAlpha(0f);
        imgSplash.animate()
                .translationY(txtName.getHeight())
                .alpha(1f)
                .setDuration(800);

        //perpindahan activity setelah 3000ms
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(activity_splash.this,MainActivity.class));
            }
        }, 3000);
    }
}