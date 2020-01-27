package com.ppsi.epayment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class checkLoginActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String indexAccount;
    private String m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_login);

        sp = getSharedPreferences("Data_Login", Context.MODE_PRIVATE);
        editor = sp.edit();


        m = sp.getString("longged", null);
        if (m != null) {
            Intent j = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(j);
        }else{
            Intent j = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(j);
        }
    }
}
