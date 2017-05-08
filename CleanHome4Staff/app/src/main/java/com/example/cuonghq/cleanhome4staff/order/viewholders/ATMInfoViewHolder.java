package com.example.cuonghq.cleanhome4staff.order.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.cuonghq.cleanhome4staff.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Cuonghq on 5/3/2017.
 */

public class ATMInfoViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.tv_address)
    TextView tvAtm;

    public ATMInfoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(String string, int position) {
        tvAtm.setText(String.format("%d/%s", position + 1, string));
    }
}
