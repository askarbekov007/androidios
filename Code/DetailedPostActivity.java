package com.example.myvk4;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailedPostActivity extends AppCompatActivity{
    private TextView date;
    private TextView author;
    private TextView desc;
    private TextView viewCounter;
    private TextView likeCounter;
    private TextView commentCounter;
    private TextView repostCounter;
    private ImageView like;
    private ImageView repost;
    private ImageView comment;
    private ImageView views;
    private ImageView authorAvatar;
    private ImageView postImage;
    private boolean click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_post);
        date=findViewById(R.id.date);
        author=findViewById(R.id.authorName);
        desc = findViewById(R.id.desc);
        viewCounter = findViewById(R.id.viewCounter);
        likeCounter = findViewById(R.id.likeCounter);
        commentCounter = findViewById(R.id.commentsCounter);
        repostCounter = findViewById(R.id.repostCounter);
        like = findViewById(R.id.like);
        click=getIntent().getExtras().getBoolean("clicked");
        if(click==true){
            like.setImageResource(R.drawable.red_like);
        }
        else{
            like.setImageResource(R.drawable.like);
        }
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(click==true){
                    like.setImageResource(R.drawable.like);
                    click=false;
                }
                else{
                    like.setImageResource(R.drawable.red_like);
                    click=true;
                }
            }
        });
        repost = findViewById(R.id.repost);
        comment = findViewById(R.id.comment);
        views = findViewById(R.id.views);
        authorAvatar = findViewById(R.id.authorAvatar);
        postImage = findViewById(R.id.postImage);
        Post post=getIntent().getParcelableExtra("post");
        date.setText(post.getDate());
        author.setText(post.getAuthor());
        desc.setText(post.getDesc());
        viewCounter.setText(post.getViewCount());
        likeCounter.setText(post.getLikeCount());
        commentCounter.setText(post.getCommentCount());
        repostCounter.setText(post.getRepostCount());
        postImage.setImageResource(post.getPostImage());
        authorAvatar.setImageResource(post.getAuthorImage());
    }

}
