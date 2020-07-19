package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.helpers.QueueUtils;
import com.example.myapplication.models.Contacto;

public class ContactoDetalleActivity extends AppCompatActivity {
    QueueUtils.QueueObject queue=null;
    int contactoId;
    Contacto contactoObject= new Contacto(0,"","","","");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_detalle);
        Intent o=getIntent();
        contactoId=o.getIntExtra("contactoId",-1);
        //en caso de que nadie envie un parametro ponemos -1
        if (contactoId<=-1){
            Toast.makeText(this,"No se selecciono nada",Toast.LENGTH_LONG).show();
        }
        //Consumimos informacion detallada de la nube
        contactoObject.id=contactoId;//setear para que no salga error con el Id 0
        queue= QueueUtils.getInstance(this.getApplicationContext());
        Contacto.injecContactFromCloud(queue, contactoObject,this);

    }
    public void refresh(){
        TextView txtNombre=findViewById(R.id.txtNombre);
        txtNombre.setText(contactoObject.FirstName);
    }
}