package com.ppsi.epayment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

public class ConfirmActivity extends AppCompatActivity {

    String acc,value;
    private TextView txtSendMoney,txtToAccount,txtNameAccount;
    private Button btnConfirm;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
//    harus di loop untuk mencari child dari acc yang telah di scan untuk mendapatkan nama
    private DatabaseReference myRef = database.getReference("account").child("0");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        Intent inten = getIntent();
        acc = inten.getStringExtra("acc");
        value = inten.getStringExtra("value");

        txtToAccount = findViewById(R.id.txtToAccount);
        txtSendMoney = findViewById(R.id.txtSendMoney);
        txtNameAccount = findViewById(R.id.txtNameAccount);
        btnConfirm = findViewById(R.id.btnConfirm);
        txtToAccount.setText(acc);
        txtSendMoney.setText(value);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(i);
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nama_lengkap = dataSnapshot.child("nama_lengkap").getValue(String.class);
                txtNameAccount.setText(nama_lengkap);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}
