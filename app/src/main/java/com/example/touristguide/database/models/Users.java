package com.example.touristguide.database.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Users")
public class Users implements Parcelable {
    @NonNull @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    @NonNull
    @ColumnInfo(name = "username")
    private String username;
    @NonNull
    @ColumnInfo(name = "email")
    private String email;
    @NonNull
    @ColumnInfo(name = "password")
    private String password;

    public Users() {}

    public Users(long id, @NonNull String username, @NonNull String email, @NonNull String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }


    @NonNull
    public long getId() {
        return id;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(email);
        dest.writeString(password);
    }

    protected Users(Parcel in) {
        id = in.readLong();
        username = in.readString();
        email = in.readString();
        password = in.readString();
    }

    public static final Creator<Users> CREATOR = new Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel in) {
            return new Users(in);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };
}
