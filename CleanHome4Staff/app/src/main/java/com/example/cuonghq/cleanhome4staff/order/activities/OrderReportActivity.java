package com.example.cuonghq.cleanhome4staff.order.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.cuonghq.cleanhome4staff.R;
import com.example.cuonghq.cleanhome4staff.commons.utils.Constant;
import com.example.cuonghq.cleanhome4staff.commons.utils.Event;
import com.example.cuonghq.cleanhome4staff.commons.utils.Utils;
import com.example.cuonghq.cleanhome4staff.login.activities.LoginActivity;
import com.example.cuonghq.cleanhome4staff.order.fragments.PagerFragment;
import com.example.cuonghq.cleanhome4staff.order.fragments.StaffInformationFragment;
import com.example.cuonghq.cleanhome4staff.order.fragments.StatisticFragment;
import com.example.cuonghq.cleanhome4staff.order.networks.jsonmodels.OrderAPIResponse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class OrderReportActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        Utils.changeFragment(this, R.id.fl_container, new PagerFragment(), false);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                finish();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Utils.showInfoToast("Ấn thêm lần nữa để thoát ứng dụng", OrderReportActivity.this);
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Utils.changeFragment(this, R.id.fl_container, new PagerFragment(), false);
        } else if (id == R.id.nav_gallery) {
            Utils.changeFragment(this, R.id.fl_container, new StatisticFragment(), false);
        } else if (id == R.id.nav_log_out) {
            Intent intent = new Intent(OrderReportActivity.this, LoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_info) {
            Utils.changeFragment(this, R.id.fl_container, new StaffInformationFragment(), false);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Subscribe
    public void changeTitle(Event.ChangeTitle event) {
        OrderReportActivity.this.setTitle(event.getTitle());
    }

    @Subscribe
    public void makePhoneCall(Event.MakePhoneCallEvent event) {
        OrderAPIResponse orderAPIResponse = event.getOrderAPIResponse();
        String uri = String.format("tel:%s", orderAPIResponse.getPhoneNumber());
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;

    String phoneNo;
    String message;

    public void sendMessage() {
        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> parts = smsManager.divideMessage(message);
        smsManager.sendMultipartTextMessage(phoneNo, null, parts, null, null);
    }
    @Subscribe
    protected void sendSMSMessage(Event.SendMessageEvent event) {
        phoneNo = event.getOrderAPIResponse().getPhoneNumber();

        if (event.isSuccess())
            message = Constant.success_msg;
        else
            message = Constant.match_msg;

        Log.d("sendMessage", String.format("message %s", message));

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        } else {
            try {
                sendMessage();
//                SmsManager smsManager = SmsManager.getDefault();
//                smsManager.sendTextMessage(phoneNo, null, message, null, null);
                Utils.showSuccessToast(getString(R.string.message_send), OrderReportActivity.this);
            } catch (Exception exception) {
                Utils.showErrorToast(getString(R.string.error_message), OrderReportActivity.this);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    sendMessage();
//                    SmsManager smsManager = SmsManager.getDefault();
//                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                    Utils.showSuccessToast(getString(R.string.message_send), OrderReportActivity.this);
                } else {
                    Utils.showErrorToast(getString(R.string.error_message), OrderReportActivity.this);
                    return;
                }
            }
        }

    }
}
