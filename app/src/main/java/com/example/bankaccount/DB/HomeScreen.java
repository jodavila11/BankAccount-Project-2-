package com.example.bankaccount.DB;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.bankaccount.R;



public class HomeScreen extends AppCompatActivity {

    TextView mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        mName = findViewById(R.id.name);
        String name = getIntent().getStringExtra("name");
        mName.setText(name);

    }
}