package com.example.myvk4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.Holder> {
    public boolean clicked=false;
    private List<Post> favPostList;
    ItemClickListener listener;
    public FavouritesAdapter(List<Post> favPostList, ItemClickListener listener){
        this.favPostList=favPostList;
        this.listener=listener;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post, null, false);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {
        final Post favPost = favPostList.get(position);
        holder.author.setText(favPost.getAuthor());
        holder.desc.setText(favPost.getDesc());
        holder.repostCounter.setText(favPost.getRepostCount());
        holder.likeCounter.setText(favPost.getLikeCount());
        holder.commentCounter.setText(favPost.getCommentCount());
        holder.viewCounter.setText(favPost.getViewCount());
        holder.postImage.setImageResource(favPost.getPostImage());
        holder.authorAvatar.setImageResource(favPost.getAuthorImage());
        holder.comment.setImageResource(R.drawable.comments);
        holder.repost.setImageResource(R.drawable.ic_reply_black_24dp);
        holder.views.setImageResource(R.drawable.views);
        if(favPost.isLiked==false){
            holder.like.setImageResource(R.drawable.like);
        }
        else{
            holder.like.setImageResource(R.drawable.red_like);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.itemClick(position, favPost);
                }
            }
        });
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clicked==false) {
                    if(Adapter.favList.contains(favPost)){
                        holder.like.setImageResource(R.drawable.red_like);
                        notifyDataSetChanged();
                        clicked = true;
                        Adapter.favList.add(favPost);
                    }
                }
                else {
                    Adapter.favList.remove(favPost);
                    notifyDataSetChanged();
                    holder.like.setImageResource(R.drawable.like);
                    clicked = false;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return favPostList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
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
            desc=itemView.findViewById(R.id.desc);
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
}
