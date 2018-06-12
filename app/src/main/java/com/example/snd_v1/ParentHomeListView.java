package com.example.snd_v1;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/*
Title: ParentHomeListView
Author: Jenny S
Date: Created on April 12, 2018
Description: Sets custom listview layout for the parent home,babysitter and parent search
 */

public class ParentHomeListView extends ArrayAdapter<String>{

    private String [] name;//parents/babysitters' name
    private String [] description;//parent/babysitters' bio
    private Integer[] imgid;//profile images
    private String [] rating;//babysitter's rating or parent child's information
    private String [] address;//users' address

    private Activity context;

    //constructor
    public ParentHomeListView(@NonNull Activity context, String[] name, String[] description, Integer[] imgid, String[] address, String[] rating) {
        super(context, R.layout.babysitter_profile_list, name);

        this.context = context;
            this.name = name;
            this.rating = rating;
            this.address = address;
            this.description = description;
            this.imgid = imgid;
    }

    //mutator methods
    public void setName(String n, int index){
        name[index]=n;
    }
    public void setDescription(String n, int index){
        if(n.length()>10) {
            description[index] = ""+ n.substring(0, 10) + "...";//if bio is too long, the string is cut
        }
        else{
            description[index] = n;
        }
    }
    public void setAddress(String n, int index){
        int pc = n.indexOf(", ")+2;//remove the actual address portion, only postal code portion of the address is kept
        address[index]=n.substring(pc);
    }
    public void setRating(String n, int index){
        rating[index]= n ;
        if(rating[index].contains("!!!")){//if it's a parent array storing child's information
            rating[index]= rating[index].substring(0, rating[index].indexOf("!!!"));
            if(rating[index].length()>10) {
                rating[index] = ""+ n.substring(0, 10) + "...";
            }
            else{
                rating[index] = n;
            }
        }
        else{//if it's a babysitter array storing the number of stars a babysitter has
            rating[index] +=" Stars";
        }

    }
    public void setImgid(int n, int index){
        imgid[index]=n;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View r = convertView;
        ViewHolder viewHolder = null;
        if(r==null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.babysitter_profile_list,null,true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);

        }
        else{
            viewHolder = (ViewHolder) r.getTag();
        }
        viewHolder.ivw.setImageResource(imgid[position]);
        viewHolder.tvw1.setText(name[position]);
        viewHolder.tvw2.setText(description[position]);
        viewHolder.tvw3.setText(rating[position]);
        viewHolder.tvw4.setText(address[position]);
        return r;
    }

    class ViewHolder{

        TextView tvw1;
        TextView tvw2;
        TextView tvw3;
        TextView tvw4;
        ImageView ivw;
        ViewHolder (View v){
            tvw1= (TextView) v.findViewById(R.id.name);
            tvw2= (TextView) v.findViewById(R.id.tStart);
            tvw3= (TextView) v.findViewById(R.id.date);
            tvw4= (TextView) v.findViewById(R.id.address);
            ivw= (ImageView) v.findViewById(R.id.imgid);
        }
    }
}
