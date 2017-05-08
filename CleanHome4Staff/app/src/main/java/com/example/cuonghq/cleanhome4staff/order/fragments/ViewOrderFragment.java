package com.example.cuonghq.cleanhome4staff.order.fragments;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.renderscript.Byte2;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.cuonghq.cleanhome4staff.R;
import com.example.cuonghq.cleanhome4staff.commons.utils.Constant;
import com.example.cuonghq.cleanhome4staff.commons.utils.Event;
import com.example.cuonghq.cleanhome4staff.commons.utils.Utils;
import com.example.cuonghq.cleanhome4staff.order.adapters.OrderReportAdapter;
import com.example.cuonghq.cleanhome4staff.order.networks.jsonmodels.OrderAPIResponse;
import com.example.cuonghq.cleanhome4staff.order.networks.services.OrderCompleteService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewOrderFragment extends Fragment {

    @BindView(R.id.rv_container)
    RecyclerView rvContainer;

    @BindView(R.id.sp_order_mode)
    Spinner spOrderMode;

    @BindView(R.id.layout_pick_date)
    LinearLayout layoutPickDate;

    OrderReportAdapter adapter;

    @BindView(R.id.start_date)
    TextView tvStartDate;

    @BindView(R.id.end_date)
    TextView tvEndDate;

    @BindView(R.id.btn_pick_start)
    ImageButton btnStart;

    @BindView(R.id.btn_end)
    ImageButton btnEnd;

    public ViewOrderFragment() {
        // Required empty public constructor
    }

    private int position;

    public void setAdapter(int position) {
        this.position = position;
        adapter = new OrderReportAdapter(position);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_order, container, false);
        ButterKnife.bind(this, view);
        rvContainer.setAdapter(adapter);
        rvContainer.setLayoutManager(new LinearLayoutManager(getContext()));
        setupUI();
        addListener();
        return view;
    }

    private void addListener() {
        spOrderMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        layoutPickDate.setVisibility(View.GONE);
                        break;
                    case 1:
                        layoutPickDate.setVisibility(View.GONE);
                        break;
                    case 2:
                        layoutPickDate.setVisibility(View.GONE);
                        break;
                    case 3:
                        layoutPickDate.setVisibility(View.GONE);
                        break;
                    case 4:
                        layoutPickDate.setVisibility(View.GONE);
                        break;
                    case 5: {
                        layoutPickDate.setVisibility(View.VISIBLE);

                        break;

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setupUI() {
        ArrayAdapter<String> arrayAdapter;

        String[] strings = {
                "Các đơn của ngày hôm nay",
                "Các đơn của ngày hôm qua",
                "Các đơn của tuần này",
                "Các đơn của tháng này",
                "Các đơn của năm nay",
                "Các đơn từ ngày... đến ngày..."
        };

        arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, strings);

        spOrderMode.setAdapter(arrayAdapter);
    }

    @OnClick(R.id.btn_pick_start)
    public void pickStartDate() {
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(getContext(), R.style.Theme_AppCompat_Light_Dialog, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                tvStartDate.setText(String.format("%02d-%02d-%04d", dayOfMonth, month + 1, year));
            }
        },  calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) , calendar.get(Calendar.DATE));

        dialog.show();
    }

    @OnClick(R.id.btn_end)
    public void pickEndDate() {
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(getContext(), R.style.Theme_AppCompat_Light_Dialog, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                tvEndDate.setText(String.format("%02d-%02d-%04d", dayOfMonth, month + 1, year));
            }
        },  calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) , calendar.get(Calendar.DATE));

        dialog.show();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().post(new Event.ChangeTitle("Đơn hàng"));
        EventBus.getDefault().register(this);

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Subscribe
    public void onCompleteEvent(final Event.CompleteOrderEvent event) {

        final ProgressDialog dialog = new ProgressDialog(getContext(), ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Đang hoàn thành");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        OrderCompleteService orderCompleteService = Utils.retrofit.create(OrderCompleteService.class);

        Call<String> call = orderCompleteService.completingOrder(event.getId(), String.format("Token %s", Constant.sharedPreferences.getString(Constant.AUTHORIZATION_TOKEN, "")));

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("onResponse", String.format("Code %d", response.code()));
                dialog.dismiss();
                Utils.showSuccessToast("Thành công", getContext());
                adapter.onDelete(event.getPosition(), event.getServiceId());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

}
