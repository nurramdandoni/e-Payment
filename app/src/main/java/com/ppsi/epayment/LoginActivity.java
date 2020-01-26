package com.ppsi.epayment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("account");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText username = findViewById(R.id.str_username);
        final EditText password = findViewById(R.id.str_password);

        final String fusername,fpassword;

        Button b_login = findViewById(R.id.btn_login);
        TextView signUp = findViewById(R.id.text_question);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String dataAccount = String.valueOf(dataSnapshot.child("account").getChildrenCount());

                int jumlahAccount = Integer.valueOf(dataAccount);
                for (int i = 0; i < jumlahAccount; i++){
                    String child = i;

                    String u = String.valueOf(dataSnapshot.child("account").child(child).child("username").getValue());
                    String p = String.valueOf(dataSnapshot.child("account").child(child).child("password").getValue());

                    if(u.toString().equals(username.getText().toString() && p.toString().equals(password.getText().toString()))){
                        fusername = u;
                        fpassword = p;
                    }else{

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        })

        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(username.getText().length()==0){
                    Toast.makeText(getApplicationContext(),"Username tidak boleh Kosong",Toast.LENGTH_LONG).show();
                }else if(password.getText().length()==0){
                    Toast.makeText(getApplicationContext(),"Password tidak boleh Kosong",Toast.LENGTH_LONG).show();
                }else if(username.getText().toString().equals(fusername) && password.getText().toString().equals(fpassword) ){
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
