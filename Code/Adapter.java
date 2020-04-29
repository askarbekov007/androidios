package com.example.myvk4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder>  {
    public List<Post> postList;
    ItemClickListener listener;
    LikeClickListener likeClickListener;
    public static List<Post> favList = new ArrayList<>();
    public boolean clicked=false;
    public Adapter(List<Post> postList){
        this.postList=postList;
    }
    public Adapter(List<Post> postList, ItemClickListener listener, LikeClickListener likeClickListener ){
        this.postList = postList;
        this.listener = listener;
        this.likeClickListener= likeClickListener;
    }
    @NonNull
    @Override
    public Adapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post, null, false);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Adapter.Holder holder, final int position) {
        final Post post = postList.get(position);
        holder.author.setText(post.getAuthor());
        holder.date.setText(post.getDate());
        holder.desc.setText(post.getDesc());
        holder.repostCounter.setText(post.getRepostCount());
        holder.likeCounter.setText(post.getLikeCount());
        holder.commentCounter.setText(post.getCommentCount());
        holder.viewCounter.setText(post.getViewCount());
        holder.postImage.setImageResource(post.getPostImage());
        holder.authorAvatar.setImageResource(post.getAuthorImage());
        holder.comment.setImageResource(R.drawable.comments);
        holder.repost.setImageResource(R.drawable.ic_reply_black_24dp);
        holder.views.setImageResource(R.drawable.views);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.itemClick(position,post);
                }
            }
        });
        if(post.isLiked==false){
            holder.like.setImageResource(R.drawable.like);
        }
        else{
            holder.like.setImageResource(R.drawable.red_like);
        }
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(post.isLiked==false) {
                    holder.like.setImageResource(R.drawable.red_like);
                    if(favList.contains(post)) {
                        return;
                    }
                    else{
                        favList.add(post);

                        post.isLiked= true;
                    }
                    if(likeClickListener!=null){
                        likeClickListener.likeClick(position, post);
                    }
                }
                else {
                    holder.like.setImageResource(R.drawable.like);
                    post.isLiked = false;
                    favList.remove(post);
                    notifyDataSetChanged();
                    if(likeClickListener!=null){
                        likeClickListener.likeClick(position, post);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }


    public class Holder extends RecyclerView.ViewHolder{
        TextView date;
        TextView author;
        TextView desc;
        TextView viewCounter;
        TextView likeCounter;
        TextView commentCounter;
        TextView repostCounter;
        ImageView repost;
        ImageView comment;
        ImageView like;
        ImageView views;
        ImageView authorAvatar;
        ImageView postImage;
        ImageView threeDots;
        public Holder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            author = itemView.findViewById(R.id.authorName);
            desc = itemView.findViewById(R.id.desc);
            viewCounter = itemView.findViewById(R.id.viewCounter);
            likeCounter = itemView.findViewById(R.id.likeCounter);
            commentCounter = itemView.findViewById(R.id.commentsCounter);
            repostCounter = itemView.findViewById(R.id.repostCounter);
            repost=itemView.findViewById(R.id.repost);
            comment=itemView.findViewById(R.id.comment);
            views=itemView.findViewById(R.id.views);
            authorAvatar=itemView.findViewById(R.id.authorAvatar);
            postImage=itemView.findViewById(R.id.postImage);
            like=itemView.findViewById(R.id.like);
            threeDots=itemView.findViewById(R.id.threeDots);
        }
    }
    interface ItemClickListener{
        void itemClick(int position, Post item);
    }
    interface LikeClickListener{
        void likeClick(int position, Post item);
    }

}
