package com.ppsi.epayment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class LogoutActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        sp = getSharedPreferences("Data_Login", Context.MODE_PRIVATE);
        editor = sp.edit();

        editor.remove("index_acc_self");
        editor.remove("longged");
        editor.apply();

        Intent j = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(j);
    }
}
