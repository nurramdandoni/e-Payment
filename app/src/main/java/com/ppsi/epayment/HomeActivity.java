package com.ppsi.epayment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private final HomeFragment homeFragment = new HomeFragment();
    private final TransactionFragment transactionFragment = new TransactionFragment();
    private final HistoryFragment historyFragment = new HistoryFragment();
    private final AccountFragment accountFragment = new AccountFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();

        loadFragment(homeFragment);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_home:
                        loadFragment(homeFragment);
                        Toast.makeText(getApplicationContext(), "Ini Home", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.item_transaksi:
                        loadFragment(transactionFragment);
                        Toast.makeText(getApplicationContext(), "Ini Transaction", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.item_history:
                        loadFragment(historyFragment);
                        Toast.makeText(getApplicationContext(), "Ini Histori", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.item_account:
                        loadFragment(accountFragment);
                        Toast.makeText(getApplicationContext(), "Ini Account", Toast.LENGTH_LONG).show();
                        return true;
                }
                return false;
            }
        });
    }

    private void initView() {
        bottomNavigationView = findViewById(R.id.nav_bottom);
    }


    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layout_content, fragment);
        fragmentTransaction.commit();
    }
}
