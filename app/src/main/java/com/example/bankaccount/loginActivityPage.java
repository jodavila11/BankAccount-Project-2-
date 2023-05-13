package com.example.bankaccount;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bankaccount.DB.BankAccount;
import com.example.bankaccount.DB.Login;
import com.example.bankaccount.DB.UserDAO;
import com.example.bankaccount.DB.UserDatabase;
import com.example.bankaccount.DB.UserEntity;


public class loginActivityPage extends AppCompatActivity {

    EditText userId;
    EditText password;
    EditText name;
    Button createAccount;

    Button login;

    @Override
    protected void  onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        userId = findViewById(R.id.userId);
        password = findViewById(R.id.password);

        createAccount = findViewById(R.id.CreateAccount);
        login = findViewById(R.id.login);


        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserEntity userEntity = new UserEntity();
                userEntity.setUserId(userId.getText().toString());
                userEntity.setPassword(password.getText().toString());
                userEntity.setName(name.getText().toString());

                if(confirmLogin(userEntity)){

                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserDAO userDAO = userDatabase.mUserDAO();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            userDAO.registerUSer(userEntity);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),"Successful Login",Toast.LENGTH_SHORT
                                    ).show();
                                }
                            });

                        }
                    }).start();
                }else{
                    Toast.makeText(getApplicationContext(), "Please Fill in Empty parts"
                    , Toast.LENGTH_SHORT).show();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginActivityPage.this, Login.class));
            }
        });
    }

    private Boolean confirmLogin(UserEntity userEntity){
        if(userEntity.getName().isEmpty() ||
        userEntity.getPassword().isEmpty() ||
        userEntity.getName().isEmpty()){
            return false;
        }
        return true;
    }

}
