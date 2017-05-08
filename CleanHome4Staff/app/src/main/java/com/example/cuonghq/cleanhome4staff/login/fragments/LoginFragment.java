package com.example.cuonghq.cleanhome4staff.login.fragments;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.cuonghq.cleanhome4staff.R;
import com.example.cuonghq.cleanhome4staff.commons.utils.Constant;
import com.example.cuonghq.cleanhome4staff.commons.utils.Event;
import com.example.cuonghq.cleanhome4staff.commons.utils.Utils;
import com.example.cuonghq.cleanhome4staff.login.networks.jsonmodels.Account;
import com.example.cuonghq.cleanhome4staff.login.networks.jsonmodels.LoginResponseBody;
import com.example.cuonghq.cleanhome4staff.login.networks.services.LoginService;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.cuonghq.cleanhome4staff.commons.utils.Constant.responseBody;
import static com.example.cuonghq.cleanhome4staff.commons.utils.Constant.sharedPreferences;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    @BindView(R.id.et_user_name)
    EditText etUsername;

    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.btn_login)
    Button btnLogin;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.btn_login)
    public void login() {

        String name = etUsername.getText().toString();

        if (name.equals("")) {
            etUsername.setError("Không được để trống mục này");
            etUsername.requestFocus();
            return;
        }
        String password = etPassword.getText().toString();

        if (password.equals("")) {
            etUsername.setError("Không được để trống mục này");
            etPassword.requestFocus();
            return;
        }

        LoginService loginService = Utils.retrofit.create(LoginService.class);

        Account account = new Account(name, password);

        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), new Gson().toJson(account));

        Call<LoginResponseBody> call = loginService.login(requestBody);

        final ProgressDialog progressDialog = new ProgressDialog(getContext(), ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Vui lòng chờ");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        call.enqueue(new Callback<LoginResponseBody>() {
            @Override
            public void onResponse(Call<LoginResponseBody> call, Response<LoginResponseBody> response) {

                if (response.code() == 200) {

                    responseBody = response.body();

                    String token = response.body().getAuthenticationToken();

                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString(Constant.AUTHORIZATION_TOKEN, token);

                    editor.apply();

                    progressDialog.dismiss();

                    Utils.showSuccessToast("Đăng nhập thành công", getContext());


                    EventBus.getDefault().post(new Event.ChangeActivityEvent());
                }
                else {
                    progressDialog.dismiss();

                    Utils.showErrorToast("Đăng nhập thất bại", getContext());
                }
            }

            @Override
            public void onFailure(Call<LoginResponseBody> call, Throwable t) {
                EventBus.getDefault().post(new Event.ChangeActivityEvent());
            }
        });

    }
}
