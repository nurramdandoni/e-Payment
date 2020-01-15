package com.ppsi.epayment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText username = findViewById(R.id.str_username);
        final EditText password = findViewById(R.id.str_password);

        Button b_login = findViewById(R.id.btn_login);
        TextView signUp = findViewById(R.id.text_question);

        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().length()==0){
                    Toast.makeText(getApplicationContext(),"Username tidak boleh Kosong",Toast.LENGTH_LONG).show();
                }else if(password.getText().length()==0){
                    Toast.makeText(getApplicationContext(),"Password tidak boleh Kosong",Toast.LENGTH_LONG).show();
                }else if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin") ){
                    Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"Login Gagal",Toast.LENGTH_LONG).show();
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(j);
            }
        });
    }
}
