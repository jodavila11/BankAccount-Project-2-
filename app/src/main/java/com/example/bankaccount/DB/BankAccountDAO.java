package com.example.bankaccount.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao

public interface BankAccountDAO {

    @Insert
    void insert(BankAccount...bankAccounts);

    @Update
    void update(BankAccount...bankAccounts);

    @Delete
    void delete(BankAccount bankAccount);



    @Query("SELECT * FROM " + AppDatabase.BANKACCOUNT_TABLE)
    List<BankAccount> getBankAccount();

    @Query("SELECT * FROM " + AppDatabase.BANKACCOUNT_TABLE + " WHERE mLogId = :logId")
    List<BankAccount> getLogById(int logId);




}
