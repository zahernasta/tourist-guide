package com.example.touristguide.database.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.touristguide.components.Stay;

import static androidx.room.ForeignKey.CASCADE;


@Entity(foreignKeys = @ForeignKey(entity = Users.class,
                                            parentColumns = "id",
                                            childColumns = "userId",
                                            onDelete = CASCADE))
public class Stays implements Parcelable {

    @PrimaryKey(autoGenerate = true) @NonNull
    @ColumnInfo(name = "id")
    private long id;

    @NonNull
    @ColumnInfo(name = "stayName")
    private String stayName;

    @NonNull
    @ColumnInfo(name = "stayDescription")
    private String stayDescription;

    @NonNull
    @ColumnInfo(name = "stayPicture")
    private String stayPicture;

    @NonNull
    @ColumnInfo(name = "userId")
    private long userId;


    public Stays(long id, @NonNull String stayName, @NonNull String stayDescription,
                 @NonNull String stayPicture, long userId) {
        this.id = id;
        this.stayName = stayName;
        this.stayDescription = stayDescription;
        this.stayPicture = stayPicture;
        this.userId = userId;
    }

    public Stays() {}

    protected Stays(Parcel in) {
        id = in.readLong();
        stayName = in.readString();
        stayDescription = in.readString();
        stayPicture = in.readString();
        userId = in.readLong();
    }

    public static final Creator<Stays> CREATOR = new Creator<Stays>() {
        @Override
        public Stays createFromParcel(Parcel in) {
            return new Stays(in);
        }

        @Override
        public Stays[] newArray(int size) {
            return new Stays[size];
        }
    };


    public long getUserId() {
        return userId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setStayName(@NonNull String stayName) {
        this.stayName = stayName;
    }

    public void setStayDescription(@NonNull String stayDescription) {
        this.stayDescription = stayDescription;
    }

    public void setStayPicture(@NonNull String stayPicture) {
        this.stayPicture = stayPicture;
    }

    public long getId() {
        return id;
    }

    @NonNull
    public String getStayName() {
        return stayName;
    }

    @NonNull
    public String getStayDescription() {
        return stayDescription;
    }

    @NonNull
    public String getStayPicture() {
        return stayPicture;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(stayName);
        dest.writeString(stayDescription);
        dest.writeString(stayPicture);
    }
}
