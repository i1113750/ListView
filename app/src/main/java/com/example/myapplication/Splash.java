package com.example.myapplication;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.concurrent.TimeUnit;


public class Splash extends AppCompatActivity {
@Override
    protected void onCreate (@Nullable Bundle saveInstanceState){
            super.onCreate(saveInstanceState);
    Intent o =new Intent(this, ProductosActivity.class);
    try {
        TimeUnit.SECONDS.sleep (5);
            } catch (InterruptedException e) {
        e.printStackTrace();
    }

    startActivity(o);
    finish();
        }
}
