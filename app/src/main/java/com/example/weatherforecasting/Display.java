package com.example.weatherforecasting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Display extends AppCompatActivity {

    TextView cityName , main , description,temp,country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        cityName = findViewById(R.id.cityName);
        main = findViewById(R.id.  main);
        description = findViewById(R.id.description);
        temp = findViewById(R.id. temp);
        country = findViewById(R.id.country );


        Intent intent = getIntent();
        Log.d("suyash",intent.getStringExtra("content"));
        String cityNameText = intent.getStringExtra("content");
        Log.d("suyash",cityNameText);
if(cityNameText == null){
    Log.d("suyash","Not Valid");
}

                RequestQueue requestQueue ;
                          requestQueue= Volley.newRequestQueue(this);

                          String url = "https://api.openweathermap.org/data/2.5/weather?q="+cityNameText+"&appid=3df2e145f735a2d392aee657ac7258c1";
                          JsonObjectRequest jsonObjectRequest =
                                  new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                                      @Override
                                      public void onResponse(JSONObject response) {
                                          try {

                                              Log.d("suyash","Name"+response.getString("name"));
                                              cityName.setText("City : "+response.getString("name"));
                                              String weather = response.getString("weather");
                                              //   Log.d("suyash",weather);

                                              JSONArray jsonArray = new JSONArray(weather);
                                              for(int i =0 ;i < jsonArray.length();i++){

                                                  // <uses-permission android:name="android.permission.INTERNET">
                                                  JSONObject jsonObject = jsonArray.getJSONObject(i);
                                                  Log.d("suyash","Main"+jsonObject.getString("main"));
                                                  main.setText("Climate : "+jsonObject.getString("main"));
                                                  Log.d("suyash","Description"+jsonObject.getString("description"));
                                                  description.setText("Description : "+jsonObject.getString("description"));
                                              }

                                              String base =  response.getString("base");
                                              //Log.d("suyash","Base"+base);

                                              String main =   response.getString("main");

                                              JSONObject mainJson = response.getJSONObject("main");
                                              Log.d("suyash","Temp"+mainJson.getString("temp"));
                                              temp.setText("Temperature: "+mainJson.getString("temp"));
                                              JSONObject sysJson =  response.getJSONObject("sys");
                                              Log.d("suyash","Country"+sysJson.getString("country"));
                                              country.setText("Country : "+sysJson.getString("country"));

                                          } catch (JSONException e) {

                                             // Toast.makeText(Display.this, "Please Enter Valid City Name", Toast.LENGTH_SHORT).show();
                                              e.printStackTrace();
                                          }
                                      }
                                  }, new Response.ErrorListener() {
                                      @Override
                                      public void onErrorResponse(VolleyError error) {

                                          Toast.makeText(Display.this, "Please Enter Valid City Name", Toast.LENGTH_SHORT).show();
                                          Log.d("suyash","NEED TO CHECK");
                                      }
                                  });
                          requestQueue.add(jsonObjectRequest);



    }
}