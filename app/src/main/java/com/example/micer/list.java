package com.example.micer;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link list#newInstance} factory method to
 * create an instance of this fragment.
 */
public class list extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public list() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment list.
     */
    // TODO: Rename and change types and number of parameters
    public static list newInstance(String param1, String param2) {
        list fragment = new list();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
    RecyclerView recyclerView;
    TextView NoSongs;
    TextView text;
    ArrayList<AudioModel> SongList = new ArrayList<>();
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        NoSongs = view.findViewById(R.id.textView22);

        String[] projection ={MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.TITLE};



        String selection = MediaStore.Audio.Media.IS_MUSIC + "!=0";
        //cursor provides read and write acsses to result set returned by database query
        Cursor cursor = getActivity().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI , projection , selection , null , null
        );
        while(cursor.moveToNext())
        {
            AudioModel SongData = new AudioModel(cursor.getString(0), cursor.getString(2), cursor.getString(1));
            if(new File(SongData.getPath()).exists())
            {


                SongList.add(SongData);
            }

        }
        if(SongList.size()==0)
        {
            NoSongs.setVisibility(View.VISIBLE);
        }
        //setting RecyclerView with Adapter and Layout
        //creating MusicAdapter and MyLayout
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MusicAdapter(SongList , getActivity().getApplicationContext()));

        return view;
    }




}