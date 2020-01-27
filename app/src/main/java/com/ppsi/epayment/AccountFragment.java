package com.ppsi.epayment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.ContentValues.TAG;

public class AccountFragment extends Fragment {
    private TextView txtBalance,txtName,txtNoacc,txtEmail,txtTelp,txtType,txtVerify,txtDate,fjk;
    private CircleImageView foto;
    Button b_logout;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sp = getActivity().getSharedPreferences("Data_Login", Context.MODE_PRIVATE);
        String index = sp.getString("index_acc_self", null);
        final DatabaseReference myRef = database.getReference("account").child(index);

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
        b_logout = viewsend.findViewById(R.id.btn_logout);
        foto = viewsend.findViewById(R.id.fotoid);

        b_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),LogoutActivity.class);
                startActivity(i);
                Toast.makeText(getContext(),"Berhasil Logout!",Toast.LENGTH_SHORT).show();
            }
        });



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
                String jk = dataSnapshot.child("jk").getValue(String.class);
                if(jk.equals("l")){
                    foto.setImageResource(R.drawable.maleico);
                }else{
                    foto.setImageResource(R.drawable.femaleco);
                }

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
