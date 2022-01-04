package com.example.covid19_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends AppCompatActivity {

    MediaPlayer music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();


        Thread thread=new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    music=MediaPlayer.create(SplashScreen.this,R.raw.splashsound);
                    music.start();
                    sleep(6000);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    Intent intent=new Intent(SplashScreen.this,LoginActivity.class);
                    startActivity(intent);
                }

            }
        };
        thread.start();

    }
    @Override
    protected void onPause() {
        music.release();
        super.onPause();
    }
}