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
    @Query("SELECT * FROM Users WHERE username = :username and password = :password")
    Users fetchOneUser(String username, String password);
    @Query("SELECT * FROM Users WHERE id =:id")
    Users fetchOneUserById(long id);
    @Query("Update Users set username = :username, email = :email where id = :id")
    void updateUserNameEmail(String username, String email, long id);
    @Update
    void updateUser(Users user);
    @Query("DELETE from Users where id = :id")
    void deleteUserById(long id);
    @Delete
    void deleteUser(Users user);
}
