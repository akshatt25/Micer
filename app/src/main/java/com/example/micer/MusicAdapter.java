package com.example.micer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder>
{
    //arraylist of audiomodel type named songlist
    ArrayList<AudioModel> SongsList;
    Context context;

    public MusicAdapter(ArrayList<AudioModel> songsList, Context context) {
        SongsList = songsList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        //here , return type is view holdrer therefore viewholder class has been created and it takes view as a parameter , so a view is craeated through
        //layoutInflater and passed in view holder
        View view = LayoutInflater.from(context).inflate(R.layout.mylayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        //setting the tittle for song file
        //to get title from a particular file in songlist we need a audiomodeltype obj which can hold it

        AudioModel SongData = SongsList.get(position);

        holder.textTitle.setText(SongData.getTitle());

//        int a = Integer.parseInt(SongData.getAlbumArt());
//        Uri albumArtUri = ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), SongData.getAlbumid());
//
//
//
//        // Use an image loading library such as Picasso or Glide to load and display the album art:
//
//
//        if(SongData.albumid == 0L) {
//           holder.img.setBackgroundResource(R.drawable.img_5);
//        }
//
//        else
//            Glide.with(this.context).load(albumArtUri).into(holder.img);
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(SongData.path);

        byte[] albumArt = retriever.getEmbeddedPicture();

        if (albumArt != null) {
            // Display the album art
            Bitmap bitmap = BitmapFactory.decodeByteArray(albumArt, 0, albumArt.length);
            String s = Integer.toString(albumArt.length);
            Log.d("hello", "onBindViewHolder: ");
            holder.img.setImageBitmap(bitmap);
        } else {
            // Display a default image
            holder.img.setImageResource(R.drawable.music);
        }



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyMediaPlayer.getInstance().reset();
                MyMediaPlayer.currentIndex=position;
                //intent
                Intent intent = new Intent(context ,player.class);
                intent.putExtra("LIST",SongsList);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
//        holder.fav.setOnClickListener(new View.OnClickListener() {
//            @Override
////            public void onClick(View v) {
////                holder.fav.
////            }
//        });
    }

    @Override
    public int getItemCount() {
        //song list is an array list which holds are data as audio model type
        return SongsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
         ImageView img;
         TextView textTitle;
         ImageButton fav;
         public ViewHolder(View itemView) {
             super(itemView);

             img = itemView.findViewById(R.id.imageView);

             textTitle = itemView.findViewById(R.id.textView);
             fav = itemView.findViewById(R.id.imageButton4);

         }
     }
}
