package com.ppsi.epayment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ppsi.epayment.model.Account;

import static android.content.ContentValues.TAG;

public class AccountFragment extends Fragment {
    private TextView txtBalance,txtName,txtNoacc,txtEmail,txtTelp,txtType,txtVerify,txtDate;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("account").child("0");


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewsend = inflater.inflate(R.layout.fragment_account, container, false);

        txtBalance = viewsend.findViewById(R.id.balance_user);
        txtNoacc = viewsend.findViewById(R.id.no_account_user);
        txtName = viewsend.findViewById(R.id.nama_user);
        txtEmail = viewsend.findViewById(R.id.email_user);
        txtTelp = viewsend.findViewById(R.id.tel_user);
        txtType = viewsend.findViewById(R.id.type_user);
        txtVerify = viewsend.findViewById(R.id.verify_user);
        txtDate = viewsend.findViewById(R.id.reg_user);



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String balance = dataSnapshot.child("balance").getValue(String.class);
                String email = dataSnapshot.child("email").getValue(String.class);
                String nama_lengkap = dataSnapshot.child("nama_lengkap").getValue(String.class);
                String no_account = dataSnapshot.child("no_account").getValue(String.class);
                String no_telp = dataSnapshot.child("no_telp").getValue(String.class);
                String registered = dataSnapshot.child("registered").getValue(String.class);
                String type = dataSnapshot.child("type").getValue(String.class);
                String verify = dataSnapshot.child("verify").getValue(String.class);

                txtBalance.setText(balance);
                txtNoacc.setText(no_account);
                txtName.setText(nama_lengkap);
                txtEmail.setText(email);
                txtTelp.setText(no_telp);
                txtType.setText(type);
                txtVerify.setText(verify);
                txtDate.setText(registered);
                Log.d(TAG, "Value is: " + balance);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.w(TAG, "Failed to read value.", databaseError.toException());

            }
        });

        return viewsend;
    }

}
