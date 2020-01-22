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
    private TextView txtBalance;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("E895330802566");


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewsend = inflater.inflate(R.layout.fragment_account, container, false);

        txtBalance = viewsend.findViewById(R.id.balance_user);

        txtBalance.setText("-");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String balance = dataSnapshot.child("balance").getValue(String.class);
                txtBalance.setText(balance);
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
