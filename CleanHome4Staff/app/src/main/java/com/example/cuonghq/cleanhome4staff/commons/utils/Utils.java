package com.example.cuonghq.cleanhome4staff.commons.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.cuonghq.cleanhome4staff.R;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import es.dmoral.toasty.Toasty;
import retrofit2.Retrofit;

/**
 * Created by Cuonghq on 4/25/2017.
 */

public class Utils {

    public static void showStyleableToast(String content, Context context) {
        StyleableToast toast = new StyleableToast(context, content, Toast.LENGTH_SHORT);
        toast.setBackgroundColor(Color.parseColor("#4BAE4F"));
        toast.setTextColor(Color.WHITE);
        toast.spinIcon();
        toast.setMaxAlpha();
        toast.show();
    }

    public static String formatTime(String time) {

        String dateTmp = time.substring(0, 10);
        String timeTmp = time.substring(11, 19);

        String year = dateTmp.substring(0, 4);
        String month = dateTmp.substring(5, 7);
        String date = dateTmp.substring(8, 10);

        String hour = timeTmp.substring(0, 2);
        String min = timeTmp.substring(3, 5);
        String sec = timeTmp.substring(6, 8);

        return String.format("%s:%s:%s - %s/%s/%s", hour, min, sec, date, month, year);
    }
    public static void showSuccessToast(String msg, Context context) {
        Toasty.success(context, msg, Toast.LENGTH_SHORT, true).show();
    }
    public static void showErrorToast(String msg, Context context) {
        Toasty.error(context, msg, Toast.LENGTH_SHORT, true).show();
    }

    public static void showInfoToast(String msg, Context context) {
        Toasty.info(context, msg, Toast.LENGTH_SHORT, true).show();
    }


    public static void changeFragment(AppCompatActivity activity, int viewId, Fragment fragment, boolean isAddToBackStack) {
        YoYo.with(Techniques.RotateInDownLeft)
                .duration(500)
                .playOn(activity.findViewById(viewId));

        if (isAddToBackStack)
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(viewId, fragment)
                    .addToBackStack(null)
                    .commit();
        else
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(viewId, fragment)
                    .commit();

    }

    public static Retrofit retrofit;



}
