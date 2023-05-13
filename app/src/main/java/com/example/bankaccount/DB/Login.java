package com.example.bankaccount.DB;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bankaccount.R;

public class Login extends AppCompatActivity {

    EditText userId;
    EditText password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        userId = findViewById(R.id.userId);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userIdText = userId.getText().toString();
                String passwordtext = password.getText().toString();
                if(userIdText.isEmpty() || passwordtext.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                }else{
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserDAO userDAO = userDatabase.mUserDAO();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                           UserEntity userEntity = userDAO.login(userIdText,passwordtext);
                           if(userEntity == null){
                               runOnUiThread(new Runnable() {
                                   @Override
                                   public void run() {
                                       Toast.makeText(getApplicationContext(), "Information Incorrect", Toast.LENGTH_SHORT).show();
                                   }
                               });
                           }else{
                               String name = userEntity.name;
                               startActivity(new Intent(Login.this,HomeScreen.class).putExtra("name",name));
                           }
                        }
                    }).start();
                }
            }
        });
    }


}
