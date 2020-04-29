package com.example.myvk4;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    Adapter homeAdapter;
    public static List<Post> items = new ArrayList<>();
    public  List<Post> favItems = new ArrayList<>();
    private Adapter.ItemClickListener listener = null;
    private Adapter.LikeClickListener listener1;
    public static final String TAG="HomeFragment";
    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        listener = new Adapter.ItemClickListener() {
            @Override
            public void itemClick(int position, Post item) {
                Intent intent = new Intent(getActivity(), DetailedPostActivity.class);
                intent.putExtra("post",item);
                intent.putExtra("clicked", homeAdapter.clicked);
                startActivity(intent);
            }
        };

        recyclerView =view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeAdapter = new Adapter(postGenerator(), listener, listener1);
        recyclerView.setAdapter(homeAdapter);
        return view;
    }
    private List<Post> postGenerator(){

        int[] logos= {
                R.drawable.logo1,
                R.drawable.logo2,
                R.drawable.logo3,
                R.drawable.logo4,
                R.drawable.logo5,
                R.drawable.logo6,
                R.drawable.logo7,
                R.drawable.logo8,
                R.drawable.logo9,
                R.drawable.logo10};
        int[] postImages = {
                R.drawable.image1,
                R.drawable.image2,
                R.drawable.image3,
                R.drawable.image4,
                R.drawable.image5,
                R.drawable.image6,
                R.drawable.image7,
                R.drawable.image8,
                R.drawable.image9,
                R.drawable.image9
        };

        String[] authorName=getResources().getStringArray(R.array.authorNames);
        String[] views=getResources().getStringArray(R.array.views);
        String[] likes=getResources().getStringArray(R.array.likes);
        String[] comments=getResources().getStringArray(R.array.comments);
        String[] reposts=getResources().getStringArray(R.array.repost);
        String[] date=getResources().getStringArray(R.array.dates);
        String[] desc=getResources().getStringArray(R.array.desc);
        for(int i=0;i<10;i++){
            Post post =new Post(date[i], authorName[i], desc[i], likes[i], comments[i], views[i], reposts[i], postImages[i], logos[i]);
            items.add(post);
        }
        return items;
    }
}
