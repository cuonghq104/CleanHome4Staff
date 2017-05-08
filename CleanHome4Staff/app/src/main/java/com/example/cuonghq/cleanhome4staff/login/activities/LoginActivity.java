package com.example.cuonghq.cleanhome4staff.login.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cuonghq.cleanhome4staff.R;
import com.example.cuonghq.cleanhome4staff.commons.utils.Constant;
import com.example.cuonghq.cleanhome4staff.commons.utils.Event;
import com.example.cuonghq.cleanhome4staff.commons.utils.Utils;
import com.example.cuonghq.cleanhome4staff.login.fragments.LoginFragment;
import com.example.cuonghq.cleanhome4staff.order.activities.OrderReportActivity;
import com.example.cuonghq.cleanhome4staff.order.networks.jsonmodels.Term;
import com.example.cuonghq.cleanhome4staff.order.networks.services.GetTermService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.cuonghq.cleanhome4staff.commons.utils.Utils.retrofit;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        setTitle("Đăng nhập");
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void setupUI() {
        Utils.changeFragment(this, R.id.fl_container, new LoginFragment(), false);
    }

    @Subscribe
    public void changeActivity(Event.ChangeActivityEvent event) {

        final ProgressDialog dialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        GetTermService service = retrofit.create(GetTermService.class);

        Call<List<Term>> call = service.getTerm();

        call.enqueue(new Callback<List<Term>>() {
            @Override
            public void onResponse(Call<List<Term>> call, Response<List<Term>> response) {
                dialog.dismiss();
                if (response.body().size() != 0) {
                    Term term = response.body().get(0);
                    Constant.success_msg = term.getSuccessMsg();
                    Constant.match_msg = term.getNotMatchMsg();
                    Intent intent = new Intent(LoginActivity.this, OrderReportActivity.class);
                    startActivity(intent);
                } else {
                    Constant.success_msg = "";
                    Constant.match_msg = "";
                    Intent intent = new Intent(LoginActivity.this, OrderReportActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<List<Term>> call, Throwable t) {
                dialog.dismiss();
                Utils.showErrorToast("Lỗi", LoginActivity.this);
            }
        });
    }
}
