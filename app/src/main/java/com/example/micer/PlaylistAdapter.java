package com.example.micer;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.MyViewHolder> {



    private ArrayList<String> mData;

    public PlaylistAdapter(ArrayList<String> data , Context context) {

            mData=data;


    }



    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.playlistview, parent, false);
        return new MyViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position ) {
        // Get the item data for this position
        String item = mData.get(position);
        // Update the view holder's views to display the item data
        holder.textView.setText(item);

    }

    // Return the size of your data source (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // Provide a reference to the views for each item
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView22);
            img = itemView.findViewById(R.id.imageView22);


        }
    }
}

