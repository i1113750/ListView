package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.myapplication.Adapters.adapter;
import com.example.myapplication.helpers.QueueUtils;
import com.example.myapplication.models.Contacto;
import com.android.volley.toolbox.ImageLoader;
import java.util.ArrayList;

public class NosotrosActivity extends AppCompatActivity {
    ListView contactosList;
    adapter contactoAdaptador;
    QueueUtils.QueueObject queue=null;
    ImageLoader queueImage = null;
    ArrayList<Contacto> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nosotros);

        queue= QueueUtils.getInstance(this.getApplicationContext());
        queueImage=queue.getImageLoader();
        items=new ArrayList<>();
        Contacto.injectContactsFromCloud(queue,items,this);
        //hasta aqui es el codigo
        contactosList = findViewById(R.id.contactosList);
        contactoAdaptador = new adapter(this, items,queueImage);
        contactosList.setAdapter(contactoAdaptador);
    }
    public void refreshList(){
        if(contactoAdaptador!=null){
            contactoAdaptador.notifyDataSetChanged();
        }
    }
}