package com.example.cuonghq.cleanhome4staff.order.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.cuonghq.cleanhome4staff.R;
import com.example.cuonghq.cleanhome4staff.commons.utils.Constant;
import com.example.cuonghq.cleanhome4staff.commons.utils.Utils;
import com.example.cuonghq.cleanhome4staff.order.adapters.OrderPagerAdapter;
import com.example.cuonghq.cleanhome4staff.order.models.OrderList;
import com.example.cuonghq.cleanhome4staff.order.networks.jsonmodels.OrderAPIResponse;
import com.example.cuonghq.cleanhome4staff.order.networks.jsonmodels.Service;
import com.example.cuonghq.cleanhome4staff.order.networks.services.GetOrdersService;
import com.example.cuonghq.cleanhome4staff.order.networks.services.GetService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.cuonghq.cleanhome4staff.commons.utils.Utils.retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class PagerFragment extends Fragment {

    @BindView(R.id.vp_container)
    ViewPager vpContainer;

    @BindView(R.id.tl_order)
    TabLayout tlOrder;

    int numOfTabs;

    OrderPagerAdapter adapter;

    public PagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pager, container, false);
        ButterKnife.bind(this, view);
//        getOrderList();
        getServiceList();
        OrderList.orderList.clear();
        return view;
    }

    private void setupUI() {
        adapter = new OrderPagerAdapter(getActivity().getSupportFragmentManager(), OrderList.orderList.size());
//        tlOrder.addTab(tlOrder.newTab().setText("GIẶT LÀ"));
//        tlOrder.addTab(tlOrder.newTab().setText("TỪ THIỆN"));
//        for (List<OrderAPIResponse> list : OrderList.orderList) {
//            if (list.size() != 0) {
        tlOrder.removeAllTabs();
        for (Service service : list) {
            tlOrder.addTab(tlOrder.newTab().setText(service.getName()));
        }
//            }
//        }
        vpContainer.setAdapter(adapter);
        vpContainer.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlOrder));
        tlOrder.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpContainer.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private List<Service> list;
    private int current;

    public void getServiceList() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext(), ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Đang lấy danh sách service");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        GetService getService = retrofit.create(GetService.class);

        final Call<List<Service>> listCall = getService.getServicesList(String.format("Token %s", Constant.sharedPreferences.getString(Constant.AUTHORIZATION_TOKEN, "")));

        listCall.enqueue(new Callback<List<Service>>() {
            @Override
            public void onResponse(Call<List<Service>> call, Response<List<Service>> response) {
                list = response.body();
                progressDialog.dismiss();
                int current = 0;
                if (list.size() != 0)
                    getOrderList(list.get(0));
            }

            @Override
            public void onFailure(Call<List<Service>> call, Throwable t) {
                Utils.showErrorToast("Lỗi", getContext());
                getActivity().onBackPressed();
            }
        });

    }
    private void getOrderList(Service service) {

        final ProgressDialog progressDialog = new ProgressDialog(getContext(), ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(String.format("Đang lấy order %s", service.getName()));
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        final GetOrdersService getOrdersService = retrofit.create(GetOrdersService.class);

        Call<List<OrderAPIResponse>> call = getOrdersService.getList(service.getId(), String.format("Token %s", Constant.sharedPreferences.getString(Constant.AUTHORIZATION_TOKEN, "")));

        call.enqueue(new Callback<List<OrderAPIResponse>>() {
            @Override
            public void onResponse(Call<List<OrderAPIResponse>> call, Response<List<OrderAPIResponse>> response) {
//                for (OrderAPIResponse order : response.body()) {
//                    boolean found = false;
//
//                    for (ArrayList<OrderAPIResponse> al : OrderList.orderList) {
//
//                        if (al.size() != 0) {
//                            if (al.get(0).getService().getId() == order.getService().getId()) {
//                                al.add(order);
//                                found = true;
//                            }
//                        }
//                    }
//
//                    if (!found) {
//                        ArrayList<OrderAPIResponse> list = new ArrayList<OrderAPIResponse>();
//                        list.add(order);
//                        OrderList.orderList.add(list);
//                    }
//                }
                ArrayList<OrderAPIResponse> apiResponses = new ArrayList<OrderAPIResponse>();
                for (OrderAPIResponse orderAPIResponse: response.body()) {
                    orderAPIResponse.setSendMessage(false);
                    apiResponses.add(orderAPIResponse);
                }
                OrderList.orderList.add(apiResponses);
                progressDialog.dismiss();
                current++;
                if (current == list.size()) {
                    setupUI();
                } else {
                    getOrderList(list.get(current));
                }
            }

            @Override
            public void onFailure(Call<List<OrderAPIResponse>> call, Throwable t) {
                progressDialog.dismiss();
                Utils.showErrorToast("Lỗi", getContext());
                getActivity().onBackPressed();
            }
        });
    }

}
