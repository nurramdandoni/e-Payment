package com.ppsi.epayment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private final HomeFragment homeFragment = new HomeFragment();
    private final TransactionFragment transactionFragment = new TransactionFragment();
    private final HistoryFragment historyFragment = new HistoryFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent j = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(j);

                finish();
            }
        },2000);
    }


}
