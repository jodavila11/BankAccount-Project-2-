package com.example.bankaccount.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {UserEntity.class}, version = 1)

public abstract class UserDatabase extends RoomDatabase {

    private static final String mName = "user";
    private static UserDatabase userDatabase;

    public static  synchronized UserDatabase getUserDatabase(Context context){

        if(userDatabase == null){
            userDatabase = Room.databaseBuilder(context, UserDatabase.class,mName)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return userDatabase;

    }


    public abstract UserDAO mUserDAO();




}

