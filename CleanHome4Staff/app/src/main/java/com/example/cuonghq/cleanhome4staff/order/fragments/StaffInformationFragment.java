package com.example.cuonghq.cleanhome4staff.order.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cuonghq.cleanhome4staff.R;
import com.example.cuonghq.cleanhome4staff.commons.utils.Constant;
import com.example.cuonghq.cleanhome4staff.commons.utils.Event;
import com.example.cuonghq.cleanhome4staff.login.networks.jsonmodels.LoginResponseBody;
import com.example.cuonghq.cleanhome4staff.order.adapters.ATMInfoAdapter;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class StaffInformationFragment extends Fragment {

    @BindView(R.id.tv_username)
    TextView tvUsername;

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_phone)
    TextView tvPhone;

    @BindView(R.id.tv_area)
    TextView tvArea;

    @BindView(R.id.rv_atm)
    RecyclerView rvAtm;

    private ATMInfoAdapter adapter;


    public StaffInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_staff_information, container, false);
        ButterKnife.bind(this, view);
        setupUI();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().post(new Event.ChangeTitle("Thông tin tài khoản"));
    }

    private LoginResponseBody responseBody;

    private void setupUI() {
        responseBody = Constant.responseBody;
        tvUsername.setText(responseBody.getUsername());
        tvName.setText(responseBody.getFirstName() + " " + responseBody.getLastName());

        adapter = new ATMInfoAdapter();
        rvAtm.setAdapter(adapter);
        rvAtm.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}
