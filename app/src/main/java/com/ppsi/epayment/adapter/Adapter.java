package com.ppsi.epayment.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ppsi.epayment.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Activity activity;
    private List<String> list;

    public Adapter(Activity activity, List<String> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String label_T = "Transaksi "+(position+1);
        final String value_T = list.get(position);
        holder.lbl_transaction.setText(label_T);
        holder.lbl_value.setText(value_T);
        if(value_T.contains("+")){
            holder.lbl_value.setTextColor(Color.parseColor("#000080"));
        }else{
            holder.lbl_value.setTextColor(Color.parseColor("#FF0000"));
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView lbl_transaction,lbl_value;
        LinearLayout lbl_background;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lbl_transaction = itemView.findViewById(R.id.lbl_InOut);
            lbl_value = itemView.findViewById(R.id.lbl_Transaction);
            lbl_background = itemView.findViewById(R.id.lbl_background);
        }
    }
}
