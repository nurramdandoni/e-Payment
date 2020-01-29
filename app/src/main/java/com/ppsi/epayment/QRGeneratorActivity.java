package com.ppsi.epayment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class QRGeneratorActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private ImageView qri;
    private String indexAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrgenerator);

        sp = getSharedPreferences("Data_Login", Context.MODE_PRIVATE);
        editor = sp.edit();


        indexAccount = sp.getString("index_acc_self", null);

        qri = findViewById(R.id.qrid);

        if(indexAccount.equals("0")){
            qri.setImageResource(R.drawable.user_0);
        }else{
            qri.setImageResource(R.drawable.user_1);
        }
    }
}
