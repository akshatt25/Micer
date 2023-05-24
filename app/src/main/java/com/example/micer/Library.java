package com.example.micer;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Library#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Library extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Library() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Library.
     */
    // TODO: Rename and change types and number of parameters
    public static Library newInstance(String param1, String param2) {
        Library fragment = new Library();
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
    LinearLayout lay1;
    ImageButton playlist;
    RecyclerView playlistsView;
    public static ArrayList<String> myList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_library, container, false);
        lay1 = view.findViewById(R.id.lay1);
        playlist = view.findViewById(R.id.imageButton2);
        playlistsView=view.findViewById(R.id.recyclerView);
//        View view2= inflater.inflate(R.layout.fragment_createplaylist, container, false);
//
//        Done= view2.findViewById(R.id.namebutton);
//        text = view2.findViewById(R.id.name);


        lay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list fragment = new list();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragcont, fragment);
                transaction.commit();

            }
        });

        playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createplaylist frag = new createplaylist();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragcont, frag);
                transaction.commit();
            }
        });


        playlistsView.setLayoutManager(new LinearLayoutManager(getContext()));

        PlaylistAdapter myAdapter = new PlaylistAdapter(MyDatabaseHelper.readItems(getActivity()), getActivity());
        playlistsView.setAdapter(myAdapter);
        return view;
    }
}