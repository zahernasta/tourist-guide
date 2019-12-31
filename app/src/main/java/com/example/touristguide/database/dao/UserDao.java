package com.example.touristguide.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.touristguide.database.models.Users;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertOneUser(Users user);
    @Insert
    void insertMultipleUsers(List<Users> userList);
    @Query("Select * from Users where id = :id")
    Users fetchOneUserById(int id);
    @Update
    void updateUser(Users user);
    @Delete
    void deleteUser(Users user);
}
