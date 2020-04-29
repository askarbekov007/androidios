package com.example.myvk4;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavouritesFragment extends Fragment {
    private RecyclerView recyclerView;
    FavouritesAdapter favouritesAdapter;
    private FavouritesAdapter.ItemClickListener listener=null;
    public static List<Post> items= new ArrayList<>();
    public static final String authorImageSaver="A";
    public static final String descSaver="B";
    public static final String dateSaver="C";
    public static final String authorNameSaver="D";
    public static final String postImageSaver="E";
    public static final String likeCountSaver="F";
    public static final String commentCountSaver="G";
    public static final String repostCountSaver="H";
    public static final String viewCountSaver="I";
    public FavouritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_favourites, container, false);
        listener = new FavouritesAdapter.ItemClickListener() {
            @Override
            public void itemClick(int position, Post item) {
                Intent intent = new Intent(getActivity(), DetailedPostActivity.class);
                intent.putExtra("post",item);
                intent.putExtra("clicked", favouritesAdapter.clicked);
                startActivity(intent);
            }
        };
        recyclerView=view.findViewById(R.id.favRecView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        favouritesAdapter =new FavouritesAdapter(postGenerator(),listener);
        recyclerView.setAdapter(favouritesAdapter);
        favouritesAdapter.notifyDataSetChanged();
        return view;
    }

    private List<Post> postGenerator() {
        return Adapter.favList;
    }

}
