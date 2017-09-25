package br.com.dribble.wisley.mydribble.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Wisley on 24/09/17.
 */

public class ShotBO implements Parcelable{
    private int id;
    private String title;
    private String description;
    private Image images;
    private int views_count;
    private int  likes_count;
    private String created_at;


    protected ShotBO(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        images = in.readParcelable(Image.class.getClassLoader());
        views_count = in.readInt();
        likes_count = in.readInt();
        created_at = in.readString();
    }

    public static final Creator<ShotBO> CREATOR = new Creator<ShotBO>() {
        @Override
        public ShotBO createFromParcel(Parcel in) {
            return new ShotBO(in);
        }

        @Override
        public ShotBO[] newArray(int size) {
            return new ShotBO[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getImages() {
        return images;
    }

    public void setImages(Image images) {
        this.images = images;
    }

    public int getViews_count() {
        return views_count;
    }

    public void setViews_count(int views_count) {
        this.views_count = views_count;
    }

    public int getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeParcelable(images, i);
        parcel.writeInt(views_count);
        parcel.writeInt(likes_count);
        parcel.writeString(created_at);
    }
}
