package com.example.myapplication.models;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.MainActivity;
import com.example.myapplication.helpers.QueueUtils;
import com.example.myapplication.NosotrosActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Contacto {
    public String FirstName;
    public String LastName;
    public String Phone;
    public Contacto(String _FirstName, String _LastName, String _Phone){
        this.FirstName=_FirstName;
        this.LastName=_LastName;
        this.Phone=_Phone;
    }
    public static ArrayList getCollection() {
        ArrayList<Contacto> collection= new ArrayList<>();
        collection.add(new Contacto("Yerry","Rodriguez","954059603"));
        collection.add(new Contacto("Jamil","Hurtado","97865412"));
        return collection;
    }
    public static void injectContactsFromCloud(final QueueUtils.QueueObject o,
                                               final ArrayList<Contacto> contactos,
                                               final NosotrosActivity _interface) {
                                                        //en este caso pusimos nostros activity
                                                        //y no main activity mucho ojo
        String url = "http://fipo.equisd.com/api/users.json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        if (response.has("objects")) {

                            try {
                                JSONArray list = response.getJSONArray("objects");
                                for (int i=0; i < list.length(); i++) {
                                    JSONObject o = list.getJSONObject(i);
                                    contactos.add(new Contacto(o.getString("first_name"),
                                            o.getString("last_name"), o.getString("created_at")));
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            _interface.refreshList(); // Esta función debemos implementarla
                            // en nuestro activity o donde hayamos puesto
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        o.addToRequestQueue(jsonObjectRequest);
    }
}

