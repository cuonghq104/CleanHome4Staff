package com.example.cuonghq.cleanhome4staff.order.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cuonghq.cleanhome4staff.R;
import com.example.cuonghq.cleanhome4staff.order.viewholders.ATMInfoViewHolder;

/**
 * Created by Cuonghq on 5/3/2017.
 */

public class ATMInfoAdapter extends RecyclerView.Adapter<ATMInfoViewHolder> {

    public String[] strings = {
            "17 ngõ Yên Thế",
            "75 Nguyễn Khuyến",
            "66 Văn Miếu",
            "55 Tôn Đức Thắng"
    };

    @Override
    public ATMInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item_atm, parent, false);

        ATMInfoViewHolder viewHolder = new ATMInfoViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ATMInfoViewHolder holder, int position) {
        holder.bind(strings[position], position);
    }

    @Override
    public int getItemCount() {
        return strings.length;
    }
}
