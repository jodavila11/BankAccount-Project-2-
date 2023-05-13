package com.example.bankaccount.DB;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = AppDatabase.BANKACCOUNT_TABLE)


public class BankAccount {


    @PrimaryKey(autoGenerate = true)
    private int mLogId;

    private double mBalance;

    private double mDeposit;

    private double mWithdrawal;

    private Date mDate;


    public BankAccount(double balance, double deposit, double withdrawal) {
        mBalance = balance;
        mDeposit = deposit;
        mWithdrawal = withdrawal;

        mDate = new Date();
    }

    @Override
    public String toString() {
        return "log # " + mLogId + "\n"+
                "Balance: " + mBalance + "\n"+
                "Deposit: " + mDeposit + "\n"+
                "Withdrawal: " + mWithdrawal + "\n"+
                "Date: " + mDate + "\n";
    }


    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public int getLogId() {
        return mLogId;
    }

    public void setLogId(int logId) {
        mLogId = logId;
    }

    public double getBalance() {
        return mBalance;
    }

    public void setBalance(double balance) {
        mBalance = balance;
    }

    public double getDeposit() {
        return mDeposit;
    }

    public void setDeposit(double deposit) {
        mDeposit = deposit;
    }

    public double getWithdrawal() {
        return mWithdrawal;
    }

    public void setWithdrawal(double withdrawal) {
        mWithdrawal = withdrawal;
    }


}
