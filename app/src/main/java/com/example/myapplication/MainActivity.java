package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnNosotros=findViewById(R.id.btnNosotros);
        btnNosotros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Nos=new Intent(MainActivity.this,NosotrosActivity.class);
                startActivity(Nos);
            }
        });
        Button btnProductos=findViewById(R.id.btnProducto);
        btnProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Prod=new Intent(MainActivity.this,ProductosActivity.class);
                startActivity(Prod);
            }
        });
    }

}