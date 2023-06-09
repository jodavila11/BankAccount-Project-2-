package com.example.bankaccount.DB;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDAO {

    @Insert
    void registerUSer(UserEntity userEntity);

    @Query("SELECT * FROM users where userId=(:userId)  and password=(:password)")
    UserEntity login(String userId, String password);

}
