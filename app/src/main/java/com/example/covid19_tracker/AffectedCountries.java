package com.example.covid19_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AffectedCountries extends AppCompatActivity {

    ListView listView;
    EditText editText;
   public static List<Model>  list=new ArrayList<>();
    List<Model> search_list;
    private String Url="https://disease.sh/v3/covid-19/countries";
    Adapter adapter;
    public static final String Name_Key ="name_key";
    public static final String Name_Key2 ="name_key2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affected_countries);

        listView=findViewById(R.id.listview_id);
        editText=findViewById(R.id.searchbox_id);
        search_list=new ArrayList<>();

        fetchData();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                try {
                    String a=Integer.toString(position);
                   // Toast.makeText(AffectedCountries.this,a , Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AffectedCountries.this,DetailsActivity.class).putExtra("position",a));

                }catch (Exception e)
                {
                    Toast.makeText(AffectedCountries.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        getSupportActionBar().setTitle("Affected Countries");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.home);
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void fetchData() {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest arrayRequest=new JsonArrayRequest(Request.Method.GET, Url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i <= response.length(); i++) {

                    try {
                        JSONObject object = response.getJSONObject(i);
                        String countryName1 = object.getString("country");
                        String population1 = object.getString("population");
                        String cases1 = object.getString("cases");
                        String todayscases1 = object.getString("todayCases");
                        String deaths1 = object.getString("deaths");
                        String todaysdeaths1 = object.getString("todayDeaths");
                        String recovered1 = object.getString("recovered");
                        String todaysrecovered1 = object.getString("todayRecovered");
                        String active1 = object.getString("active");
                        String critical1 = object.getString("critical");

                        JSONObject object2 = object.getJSONObject("countryInfo");
                        String flag1 = object2.getString("flag");

                        Model model = new Model(flag1, countryName1, population1, cases1, todayscases1, deaths1, todaysdeaths1, recovered1, todaysrecovered1, active1, critical1);
                        list.add(model);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter = new Adapter(AffectedCountries.this,list);
                listView.setAdapter(adapter);


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(arrayRequest);

    }

    }
