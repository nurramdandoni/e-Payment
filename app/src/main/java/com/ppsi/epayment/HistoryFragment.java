package com.ppsi.epayment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ppsi.epayment.adapter.Adapter;

import java.util.ArrayList;
import java.util.List;


public class HistoryFragment extends Fragment {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private List<String> listHistory = new ArrayList<>();
    private RecyclerView recyclerViewList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        sp = getActivity().getSharedPreferences("Data_Login", Context.MODE_PRIVATE);

        String index = sp.getString("index_acc_self", null);

        final DatabaseReference myRef = database.getReference("account").child(index).child("transaction");

        View viewsend = inflater.inflate(R.layout.fragment_history, container, false);
        recyclerViewList = viewsend.findViewById(R.id.list_transaction);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listHistory.clear();
                String dataHistory = String.valueOf(dataSnapshot.getChildrenCount());
                int jumlahAccount = Integer.valueOf(dataHistory);
                for (int i = 0; i < jumlahAccount; i++) {

//
                    listHistory.add(dataSnapshot.child(String.valueOf(i)).getValue().toString());

//                    Log.d("testing",dataSnapshot.child(String.valueOf(i)).getValue().toString());
                }

                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerViewList.setLayoutManager(layoutManager);

                Adapter listAdapter = new Adapter(getActivity(),listHistory);
                recyclerViewList.setAdapter(listAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return  viewsend;

    }

}
