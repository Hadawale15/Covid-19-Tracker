package com.example.covid19_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
     ImageView falg2;
     TextView countryName,population,cases,recovered,deaths,active,critical,todayCases,todayRecovered,todayDeaths,casePerPeople;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        falg2=findViewById(R.id.flag_id2);
        countryName=findViewById(R.id.country_id2);
        population=findViewById(R.id.population_id2);
        cases=findViewById(R.id.cases_id2);
        recovered=findViewById(R.id.recovered_id2);
        deaths=findViewById(R.id.deaths_id2);
        active=findViewById(R.id.active_id2);
        critical=findViewById(R.id.critical_id2);
        todayCases=findViewById(R.id.todaycases_id2);
        todayRecovered=findViewById(R.id.todaysrecov_id2);
        todayDeaths=findViewById(R.id.todaydeath_id2);
        casePerPeople=findViewById(R.id.onecaseper_id2);


        try {
            Intent intent=getIntent();
           String n=intent.getStringExtra("position");
           int a=Integer.parseInt(n);
           Model model=AffectedCountries.list.get(a);

            Glide.with(DetailsActivity.this).load(model.getFlag()).into(falg2);

            countryName.setText(model.getCountry());
            population.setText(model.getPopulation());
            cases.setText(model.getCases());
            recovered.setText(model.getRecovered());
            deaths.setText(model.getDeaths());
            active.setText(model.getActive());
            critical.setText(model.getCritical());
            todayCases.setText(model.getTodaycases());
            todayRecovered.setText(model.getTodaysrecovered());
            todayDeaths.setText(model.getTodaysdeaths());
            casePerPeople.setText(model.getCases());


                Toast.makeText(DetailsActivity.this, model.getCountry(), Toast.LENGTH_SHORT).show();


        }catch (Exception e)
        {
            Toast.makeText(DetailsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}