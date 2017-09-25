package br.com.dribble.wisley.mydribble.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Wisley on 24/09/17.
 */

public class Image implements Parcelable{

    private String teaser;
    private String normal;
    private String hidpi;

    protected Image(Parcel in) {
        teaser = in.readString();
        normal = in.readString();
        hidpi = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

    public String getNormal() {
        return normal;
    }

    public void setNormal(String normal) {
        this.normal = normal;
    }

    public String getHidpi() {
        return hidpi;
    }

    public void setHidpi(String hidpi) {
        this.hidpi = hidpi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(teaser);
        parcel.writeString(normal);
        parcel.writeString(hidpi);
    }
}
