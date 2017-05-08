package com.example.cuonghq.cleanhome4staff.order.viewholders;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.cuonghq.cleanhome4staff.R;
import com.example.cuonghq.cleanhome4staff.commons.utils.Constant;
import com.example.cuonghq.cleanhome4staff.commons.utils.Event;
import com.example.cuonghq.cleanhome4staff.commons.utils.Utils;
import com.example.cuonghq.cleanhome4staff.order.networks.jsonmodels.OrderAPIResponse;
import com.example.cuonghq.cleanhome4staff.order.networks.jsonmodels.StatOrder;
import com.example.cuonghq.cleanhome4staff.order.networks.services.StatOrderService;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.hoang8f.widget.FButton;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Cuonghq on 4/25/2017.
 */

public class OrderReportViewHolder extends ViewHolder {

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_phone)
    TextView tvPhone;

    @BindView(R.id.tv_service)
    TextView tvService;

    @BindView(R.id.tv_price)
    TextView tvPrice;

    @BindView(R.id.tv_address)
    TextView tvAddress;

    @BindView(R.id.btn_send_message_unmatch)
    Button btnSendMessage;

    @BindView(R.id.btn_complete)
    Button btnComplete;

    @BindView(R.id.tv_atm)
    TextView tvAtmAddress;

    @BindView(R.id.btn_done_receiving)
    Button btDoneReceiving;

    @BindView(R.id.tv_send_msg_unmatch_counter)
    TextView tvSendUnmatch;

    @BindView(R.id.btn_call)
    Button btCall;

    @BindView(R.id.tv_call_counter)
    TextView tvCallCounter;

    @BindView(R.id.tv_done_receiving_counter)
    TextView tvDoneReceiving;

    @BindView(R.id.tv_time)
    TextView tvTime;

    @BindView(R.id.tv_provider)
    TextView tvProvider;

    private int position;

    OrderAPIResponse order;

    public OrderReportViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(int position, OrderAPIResponse order) {
        if (order.isSendMessage()) {
            btnSendMessage.setEnabled(false);
        }
        this.position = position;
        this.order = order;
        tvName.setText(order.getOwnerName());

        String time = order.getCreated();

        tvTime.setText(Utils.formatTime(order.getCreated()));
//        tvProvider.setText(order.get);
        tvPhone.setText(order.getPhoneNumber());
        tvAtmAddress.setText(order.getAtm().getAddress());
        tvDoneReceiving.setText(String.format("Số lần %d", order.getMsgMatch()));
        tvCallCounter.setText(String.format("Số lần %d", order.getPhoneCall()));
        tvSendUnmatch.setText(String.format("Số lần %d", order.getMsgSend()));
        tvPrice.setText(String.format("%d %s", order.getPrice(), order.getPriceUnit()));
        if (order.getAddress().equals(""))
            tvAddress.setText("Tại ATM");
        else
            tvAddress.setText(order.getAddress());
//        switch (order.getService().getId()) {
//            case 1:
//                tvService.setText(order.getService().getName());
//                break;
//            case 2:
//                tvService.setText(order.getService().getName());
//        }
        tvService.setText(order.getService().getName());
    }

//    public void changeStateButtons() {
//        btCall.setBackgroundResource(R.drawable.border_curved_accent_color_bg);
//        btDoneReceiving.setBackgroundResource(R.drawable.border_curved_accent_color_bg);
//        btnSendMessage.setBackgroundResource(R.drawable.border_curved_accent_color_bg);
//    }

    @OnClick(R.id.btn_call)
    public void call() {
        final AlertDialog alertDialog = new AlertDialog.Builder(btCall.getContext(), R.style.Theme_AppCompat_Light_Dialog_Alert)
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setMessage("Gọi điện ?");
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
                StatOrderService service = Utils.retrofit.create(StatOrderService.class);
                StatOrder statOrder = new StatOrder(Constant.PHONE_CALL, order.getId());
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), new Gson().toJson(statOrder));
                Call<List<OrderAPIResponse>> listCall = service.action(String.format("Token %s", Constant.sharedPreferences.getString(Constant.AUTHORIZATION_TOKEN, "")), requestBody);
                listCall.enqueue(new Callback<List<OrderAPIResponse>>() {
                    @Override
                    public void onResponse(Call<List<OrderAPIResponse>> call, Response<List<OrderAPIResponse>> response) {
                        order = response.body().get(0);
                        tvCallCounter.setText(String.format("Số lần : %d", order.getPhoneCall()));
                        EventBus.getDefault().post(new Event.MakePhoneCallEvent(order));
                        alertDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<List<OrderAPIResponse>> call, Throwable t) {

                    }
                });
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();



    }
    @OnClick(R.id.btn_complete)
    public void completeOrder() {
        final AlertDialog alertDialog = new AlertDialog.Builder(btCall.getContext(), R.style.Theme_AppCompat_Light_Dialog_Alert)
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setMessage("Bạn có chắc đã hoàn thành không ?");
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
                EventBus.getDefault().post(new Event.CompleteOrderEvent(order.getId(), position, order.getService().getId()));

            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    @OnClick(R.id.btn_done_receiving)
    public void sendSuccessMessage() {
        final AlertDialog alertDialog = new AlertDialog.Builder(itemView.getContext(), R.style.Theme_AppCompat_Light_Dialog_Alert)
                .create();

        alertDialog.setMessage("Bạn có chắc chắn muốn gửi tin nhắn không ?");
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StatOrderService service = Utils.retrofit.create(StatOrderService.class);
                StatOrder statOrder = new StatOrder(Constant.MSG_MATCH, order.getId());
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), new Gson().toJson(statOrder));
                Call<List<OrderAPIResponse>> listCall = service.action(String.format("Token %s", Constant.sharedPreferences.getString(Constant.AUTHORIZATION_TOKEN, "")), requestBody);
                listCall.enqueue(new Callback<List<OrderAPIResponse>>() {
                    @Override
                    public void onResponse(Call<List<OrderAPIResponse>> call, Response<List<OrderAPIResponse>> response) {
                        order = response.body().get(0);
                        tvDoneReceiving.setText(String.format("Số lần : %d", order.getMsgMatch()));
                        EventBus.getDefault().post(new Event.SendMessageEvent(order, true));
                        alertDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<List<OrderAPIResponse>> call, Throwable t) {

                    }
                });
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    @OnClick(R.id.btn_send_message_unmatch)
    public void sendMismatchMessage() {
        final AlertDialog alertDialog = new AlertDialog.Builder(itemView.getContext(), R.style.Theme_AppCompat_Light_Dialog_Alert)
                .create();

        alertDialog.setMessage("Bạn có chắc chắn muốn gửi tin nhắn không ?");
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                alertDialog.dismiss();

                StatOrderService service = Utils.retrofit.create(StatOrderService.class);
                StatOrder statOrder = new StatOrder(Constant.MSG_SEND, order.getId());
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), new Gson().toJson(statOrder));
                Call<List<OrderAPIResponse>> listCall = service.action(String.format("Token %s", Constant.sharedPreferences.getString(Constant.AUTHORIZATION_TOKEN, "")), requestBody);
                listCall.enqueue(new Callback<List<OrderAPIResponse>>() {
                    @Override
                    public void onResponse(Call<List<OrderAPIResponse>> call, Response<List<OrderAPIResponse>> response) {
                        order = response.body().get(0);
                        tvSendUnmatch.setText(String.format("Số lần : %d", order.getMsgSend()));
                        EventBus.getDefault().post(new Event.SendMessageEvent(order, false));
                        alertDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<List<OrderAPIResponse>> call, Throwable t) {

                    }
                });
//                changeStateButtons();
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }



}
