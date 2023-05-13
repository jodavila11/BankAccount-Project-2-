package com.example.bankaccount;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.bankaccount.DB.Admin_page;
import com.example.bankaccount.DB.AppDatabase;
import com.example.bankaccount.DB.BankAccount;
import com.example.bankaccount.DB.BankAccountDAO;
import com.example.bankaccount.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;


    TextView mMainDisplay;

    EditText mBalance;
    EditText mDeposit;
    EditText mWithdrawal;

    Button mUserDisplay;
    Button mConfirm;

    BankAccountDAO mBankAccountDAO;

    List<BankAccount> mBankAccList;

    Button mAdmin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mMainDisplay = binding.textViewMainDisplay;
        mBalance = binding.editTextBalance;
        mDeposit = binding.editTextDeposit;
        mWithdrawal = binding.editTextWithdrawl;
        mConfirm = binding.buttonConfirmButton;
        mUserDisplay = binding.switchUserButton;
        mAdmin = binding.adminButton;



        mBankAccountDAO = Room.databaseBuilder(this,AppDatabase.class, AppDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .BankAccountDAO();
        refreshDisplay();

        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmBankAccount();
                refreshDisplay();
            }
        });

        mUserDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginActivity();
            }
        });

        mAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAdminActivity();
            }
        });
    }

    public void openLoginActivity(){
        Intent intent = new Intent(this,loginActivityPage.class);
        startActivity(intent);

    }

    public void openAdminActivity(){
        Intent intent = new Intent(this, Admin_page.class);
    }


    private void confirmBankAccount(){
        double balance = Double.parseDouble(mBalance.getText().toString());
        double deposit = Double.parseDouble(mDeposit.getText().toString());
        double withdrawal = Double.parseDouble(mWithdrawal.getText().toString());

        BankAccount log = new BankAccount(balance,deposit,withdrawal);

        mBankAccountDAO.insert(log);

    }

    private void refreshDisplay(){
        mBankAccList = mBankAccountDAO.getBankAccount();
        if(!mBankAccList.isEmpty()){
            StringBuilder sb = new StringBuilder();
            for(BankAccount log : mBankAccList){
                sb.append(log.toString());
            }
            mMainDisplay.setText(sb.toString());
        }else{
            mMainDisplay.setText(R.string.no_logs_message);
        }
    }
}
