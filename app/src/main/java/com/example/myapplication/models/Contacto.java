package com.example.myapplication.models;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.ContactoDetalleActivity;
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
    public String urlImage;
    public int id;
    public Contacto(int _id,String _FirstName, String _LastName, String _Phone,String _urlImage){
        this.id=_id;
        this.FirstName=_FirstName;
        this.LastName=_LastName;
        this.Phone=_Phone;
        this.urlImage=_urlImage;
    }
    public static ArrayList getCollection() {
        ArrayList<Contacto> collection= new ArrayList<>();
        collection.add(new Contacto(0,"Yerry","Rodriguez","954059603","avatar"));
        collection.add(new Contacto(0,"Jamil","Hurtado","97865412","avatar"));
        return collection;
    }
    public static void injecContactFromCloud(final QueueUtils.QueueObject o,
                                             final  Contacto contacto,
                                             final ContactoDetalleActivity _interface){
        String url="https://reqres.in/api/users/"+contacto.id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response.has("data")) {

                            try {
                                JSONObject objeto = response.getJSONObject("data");
                                    contacto.FirstName=objeto.getString("firts_name");
                                    contacto.LastName=objeto.getString("last_name");
                                    contacto.urlImage=objeto.getString("avatar");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            _interface.refresh(); // Esta función debemos implementarla
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
                                    contactos.add(new Contacto(o.getInt("id"),o.getString("first_name"),
                                            o.getString("last_name"), o.getString("created_at"), o.getString("avatar")));
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

