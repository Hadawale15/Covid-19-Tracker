package com.example.covid19_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    PieChart pieChart;
    FirebaseAuth firebaseAuth;
    String Url = "https://disease.sh/v3/covid-19/all";
    TextView tcases, tdeaths, trecovered, tactive, tcritical, ttodaysdeaths, ttodayscases, taffectedcountries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth=FirebaseAuth.getInstance();

        tcases = findViewById(R.id.tcases);
        tdeaths = findViewById(R.id.tdeaths);
        trecovered = findViewById(R.id.trecovered);
        tactive = findViewById(R.id.tactive);
        tcritical = findViewById(R.id.tcritical);
        ttodaysdeaths = findViewById(R.id.ttodaysdeathys);
        ttodayscases = findViewById(R.id.ttodayscases);
        taffectedcountries = findViewById(R.id.taffectedcountries);
        pieChart = findViewById(R.id.piechart_id);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, Url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject object = new JSONObject(response.toString());
                    tcases.setText(object.getString("cases"));
                    tdeaths.setText(object.getString("deaths"));
                    trecovered.setText(object.getString("recovered"));
                    tactive.setText(object.getString("active"));
                    tcritical.setText(object.getString("critical"));
                    ttodaysdeaths.setText(object.getString("todayDeaths"));
                    ttodayscases.setText(object.getString("todayCases"));
                    taffectedcountries.setText(object.getString("affectedCountries"));

          pieChart.addPieSlice(new PieModel("Total Cases",Integer.parseInt(tcases.getText().toString()), Color.parseColor("#B88308")));
          pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(trecovered.getText().toString()), Color.parseColor("#04910A")));
          pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(tdeaths.getText().toString()), Color.parseColor("#F1474C")));
          pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(tactive.getText().toString()), Color.parseColor("#0982EC")));
              pieChart.startAnimation();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(objectRequest);

    }

    public void TrackMethode(View view) {
        Intent intent=new Intent(MainActivity.this,AffectedCountries.class);
        startActivity(intent);
    }


    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(startMain);


    }


}

