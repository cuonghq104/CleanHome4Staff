package com.example.cuonghq.cleanhome4staff.order.fragments;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.cuonghq.cleanhome4staff.R;
import com.example.cuonghq.cleanhome4staff.commons.utils.Constant;
import com.example.cuonghq.cleanhome4staff.commons.utils.Event;
import com.example.cuonghq.cleanhome4staff.commons.utils.Utils;
import com.example.cuonghq.cleanhome4staff.order.networks.jsonmodels.Statistic;
import com.example.cuonghq.cleanhome4staff.order.networks.services.GetStatisticService;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticFragment extends Fragment {

    @BindView(R.id.spn_statistic)
    Spinner spnStatistic;

    @BindView(R.id.tv_total_order)
    TextView tvTotalOrder;

    @BindView(R.id.tv_total_revenue)
    TextView tvTotalRevenue;

    @BindView(R.id.tv_success_order)
    TextView tvSuccessOrder;

    @BindView(R.id.tv_success_revenue)
    TextView tvSuccessRevenue;

    @BindView(R.id.tv_cancel_order)
    TextView tvCancelOrder;

    @BindView(R.id.tv_revenue_cancel)
    TextView tvCancelRevenue;

    @BindView(R.id.start_date)
    TextView tvStartDate;

    @BindView(R.id.end_date)
    TextView tvEndDate;

    @BindView(R.id.btn_pick_start)
    ImageButton btnStart;

    @BindView(R.id.btn_end)
    ImageButton btnEnd;

    @BindView(R.id.layout_pick_date)
    LinearLayout layoutPickDate;

    ArrayAdapter<String> arrayAdapter;

    public StatisticFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_statitic, container, false);
        ButterKnife.bind(this, view);
        setupUI();
        getStatistic(0);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().post(new Event.ChangeTitle("Thống kê"));
    }

    private void setupUI() {
        layoutPickDate.setVisibility(View.GONE);
        String[] choice = new String[] {
                "Thống kê trong ngày hôm nay",
                "Thống kê trong ngày hôm qua",
                "Thống kê theo tuần",
                "Thống kê theo tháng",
                "Thống kê theo năm",
                "Thống kê từ... đến..."
        };

        arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, choice);

        spnStatistic.setAdapter(arrayAdapter);

        spnStatistic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 5)
                    layoutPickDate.setVisibility(View.GONE);
                else
                    layoutPickDate.setVisibility(View.VISIBLE);
                getStatistic(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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

    public void getStatistic(int choice) {

        GetStatisticService service = Utils.retrofit.create(GetStatisticService.class);

        String unit = "";

        if (choice > 1 && choice < 4 || choice == 0) {
            final ProgressDialog progressDialog = new ProgressDialog(getContext(), ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Đang lấy thống kê");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            switch (choice) {
                case 0:
                    unit = "day";
                    break;
                case 2:
                    unit = "week";
                    break;
                case 3:
                    unit = "month";
                    break;
            }
            Call<List<Statistic>> call = service.getStatistic(unit,
                    String.format("Token %s", Constant.sharedPreferences.getString(Constant.AUTHORIZATION_TOKEN, "")));
            call.enqueue(new Callback<List<Statistic>>() {
                @Override
                public void onResponse(Call<List<Statistic>> call, Response<List<Statistic>> response) {
                    progressDialog.dismiss();
                    if (response.body() != null) {
                        Statistic statistic = response.body().get(0);

                        tvTotalOrder.setText(String.format("%d", statistic.getTotalOrder()));
                        tvTotalRevenue.setText(String.format("%d", statistic.getTotalRevenue()));
                        tvSuccessOrder.setText(String.format("%d", statistic.getTotalSuccessOrder()));
                        tvSuccessRevenue.setText(String.format("%d", statistic.getTotalSuccessRevenue()));
                        tvCancelOrder.setText(String.format("%d", statistic.getTotalCancelOrder()));
                        tvCancelRevenue.setText(String.format("%d", statistic.getTotalCancelRevenue()));
                    }
                }

                @Override
                public void onFailure(Call<List<Statistic>> call, Throwable t) {
                    progressDialog.dismiss();
                    final AlertDialog alertDialog = new AlertDialog.Builder(getContext(), R.style.Theme_AppCompat_Light_Dialog_Alert)
                            .create();

                    alertDialog.setMessage("Lỗi không xác định");
                    alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            alertDialog.dismiss();
                        }
                    });
                    alertDialog.show();

                }
            });
        }
    }

}
