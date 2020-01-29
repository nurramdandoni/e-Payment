package com.ppsi.epayment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class TransactionFragment extends Fragment {

    private RelativeLayout btnSend;
    private RelativeLayout btnReceive;
    private RelativeLayout btnDeposit;
    private RelativeLayout btnWithdrawal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);

        btnSend = view.findViewById(R.id.btnSend);
        btnReceive = view.findViewById(R.id.btnReceive);
        btnDeposit = view.findViewById(R.id.btnDeposit);
        btnWithdrawal = view.findViewById(R.id.btnWithdrawal);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Menu Send Clicked",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(),ScanActivity.class);
                startActivity(i);
            }
        });

        btnReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Menu Receive Clicked",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(),QRGeneratorActivity.class);
                startActivity(i);
            }
        });
        btnDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Menu Deposit Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        btnWithdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Menu Withdrawal Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
