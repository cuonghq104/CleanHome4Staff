package com.example.cuonghq.cleanhome4staff.order.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.cuonghq.cleanhome4staff.R;
import com.example.cuonghq.cleanhome4staff.order.models.Order;
import com.example.cuonghq.cleanhome4staff.order.models.OrderList;
import com.example.cuonghq.cleanhome4staff.order.networks.jsonmodels.OrderAPIResponse;
import com.example.cuonghq.cleanhome4staff.order.viewholders.OrderReportViewHolder;

import java.util.ArrayList;

/**
 * Created by Cuonghq on 4/25/2017.
 */

public class OrderReportAdapter extends RecyclerView.Adapter<OrderReportViewHolder> {

    private int position;

    private Order[] orders = new Order[] {
            new Order(
                    3,
                    "hanoi",
                    "Cuong",
                    2,
                    "012131513",
                    1,
                    null
            ),
            new Order(
                    3,
                    "hoankiem",
                    "Quoc",
                    2,
                    "0125311",
                    1,
                    null
            ),
            new Order(
                    3,
                    "Điện Biên",
                    "Phủ",
                    2,
                    "22312131",
                    2,
                    null
            ),
            new Order(
                    3,
                    "Hồ Gươm",
                    "Cụ Rùa",
                    2,
                    "11313535131",
                    2,
                    null
            ),
            new Order(
                    3,
                    "Troia",
                    "Achilles",
                    2,
                    "0125311",
                    1,
                    null
            ),
    };

    private ArrayList<OrderAPIResponse> list;

    private String TAG = OrderReportAdapter.class.toString();


    public OrderReportAdapter(int position) {
        this.position = position;
        list = new ArrayList<>();
        list = OrderList.orderList.get(position);
    }

    @Override
    public OrderReportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.item_order, parent, false);

        OrderReportViewHolder viewHolder = new OrderReportViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(OrderReportViewHolder holder, final int position) {
        OrderAPIResponse order = list.get(position);

        holder.bind(position, order);

    }

    public void onText(int position, int serviceId) {
        if (list.get(0).getService().getId() == serviceId) {
            Log.d(TAG, String.format("Text : %s", list.get(position).toString()));
        }
    }

    public void onDelete(int position, int serviceId) {
        Log.d("onDelete", String.format("Position %d", position));
        if (list.size() != 0 ) {

            if (list.get(0).getService().getId() == serviceId) {
                list.remove(position);
                notifyDataSetChanged();
            }
        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}
