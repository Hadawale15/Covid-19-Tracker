package com.example.covid19_tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends ArrayAdapter<Model> {

   private List<Model> list;
    private List<Model> listfiltered;
   private Context context;


    public Adapter( Context context, List<Model> list) {
        super(context, R.layout.countriesviewlayout,list);
        this.list = list;
        this.context = context;
        this.listfiltered=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.countriesviewlayout,null,true);
        ImageView flag=view.findViewById(R.id.flag_id);
        TextView name=view.findViewById(R.id.countryname_id);

        Model model=listfiltered.get(position);

        Glide.with(context).load(model.getFlag()).into(flag);
        name.setText(model.getCountry());

        return  view;
    }
    @Override
    public int getCount() {
        return listfiltered.size();
    }

    @Nullable
    @Override
    public Model getItem(int position) {
        return listfiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if(constraint == null || constraint.length() == 0){
                    filterResults.count = list.size();
                    filterResults.values = list;

                }else{
                    List<Model> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for(Model itemsModel:list){
                        if(itemsModel.getCountry().toLowerCase().contains(searchStr)){
                            resultsModel.add(itemsModel);

                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }


                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                listfiltered= (List<Model>) results.values;
                AffectedCountries.list= (List<Model>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }
}
