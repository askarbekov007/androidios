package com.example.myvk4;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Post extends AppCompatActivity implements Parcelable{
    private String date;
    private String author;
    private String desc;
    private String likeCount;
    private String commentCount;
    private String viewCount;
    private String repostCount;
    private int postImage;
    private int authorImage;
    public boolean isLiked=false;
    public Post(String date,
                String author,
                String desc,
                String likeCount,
                String commentCount,
                String viewCount,
                String repostCount,
                int postImage,
                int authorImage){
        this.author = author;
        this.date = date;
        this.desc = desc;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.viewCount = viewCount;
        this.repostCount = repostCount;
        this.postImage = postImage;
        this.authorImage = authorImage;
    }

    public String getDate() {
        return date;
    }


    public String getAuthor() {
        return author;
    }


    public String getDesc() {
        return desc;
    }
    public String getLikeCount() {
        return likeCount;
    }


    public String getCommentCount() {
        return commentCount;
    }


    public String getViewCount() {
        return viewCount;
    }


    public String getRepostCount() {
        return repostCount;
    }



    public int getPostImage() {
        return postImage;
    }


    public int getAuthorImage() {
        return authorImage;
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
    protected Post(Parcel in) {
        this.authorImage = in.readInt();
        this.postImage = in.readInt();
        this.author = in.readString();
        this.repostCount=in.readString();
        this.viewCount=in.readString();
        this.commentCount =in.readString();
        this.likeCount=in.readString();
        this.desc =in.readString();
        this.date=in.readString();

    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.authorImage);
        parcel.writeInt(this.postImage);
        parcel.writeString(this.author);
        parcel.writeString(this.repostCount);
        parcel.writeString(this.viewCount);
        parcel.writeString(this.commentCount);
        parcel.writeString(this.likeCount);
        parcel.writeString(this.desc);
        parcel.writeString(this.date);
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}
