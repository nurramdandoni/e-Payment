package com.ppsi.epayment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ConfirmActivity extends AppCompatActivity {

    String acc;
    private TextView txtSendMoney,txtToAccount,txtNameAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        Intent inten = getIntent();
        acc = inten.getStringExtra("acc");

        txtToAccount = findViewById(R.id.txtToAccount);
        txtToAccount.setText(acc);
    }
}
