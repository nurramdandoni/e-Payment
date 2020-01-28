package com.ppsi.epayment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

public class ConfirmActivity extends AppCompatActivity {

    private String acc,value,index_send,next_index_to,last_balance_to,next_index_from,last_balance_from;
    private TextView txtSendMoney,txtToAccount,txtNameAccount;
    private Button btnConfirm;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String indexAccount,sh;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        sp = getSharedPreferences("Data_Login", Context.MODE_PRIVATE);
        editor = sp.edit();


        indexAccount = sp.getString("index_acc_self", null);


        Intent inten = getIntent();

        acc = inten.getStringExtra("acc");
        value = inten.getStringExtra("value");
        index_send = inten.getStringExtra("index_send");

        sh = indexAccount+" to "+index_send;

        //    harus di loop untuk mencari child dari acc yang telah di scan untuk mendapatkan nama
        final DatabaseReference from = database.getReference("account").child(indexAccount);
        final DatabaseReference to = database.getReference("account").child(index_send);

        txtToAccount = findViewById(R.id.txtToAccount);
        txtSendMoney = findViewById(R.id.txtSendMoney);
        txtNameAccount = findViewById(R.id.txtNameAccount);
        btnConfirm = findViewById(R.id.btnConfirm);
        txtToAccount.setText(acc);
        txtSendMoney.setText(value);



        to.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nama_lengkap = dataSnapshot.child("nama_lengkap").getValue(String.class);
                txtNameAccount.setText(nama_lengkap);
                next_index_to = String.valueOf(dataSnapshot.child("transaction").getChildrenCount());
                last_balance_to = dataSnapshot.child("balance").getValue(String.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        from.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                next_index_from = String.valueOf(dataSnapshot.child("transaction").getChildrenCount());
                last_balance_from = dataSnapshot.child("balance").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getApplicationContext(),sh, Toast.LENGTH_SHORT).show();

                // awal last_balance_from - value;
                int from_balance_update = Integer.valueOf(last_balance_from)-Integer.valueOf(value);
                // insert transaksi -value
                from.child("transaction").child(next_index_from).setValue("-"+value);
                // update saldo pengirim
                from.child("balance").setValue(String.valueOf(from_balance_update));

                // lalu last_lanace-to + value;
                int to_balance_update = Integer.valueOf(last_balance_to)+Integer.valueOf(value);
                // insert transaksi +value
                to.child("transaction").child(next_index_to).setValue("+"+value);
                // update saldo penerima
                to.child("balance").setValue(String.valueOf(to_balance_update));

                Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(i);
            }
        });




    }
}
