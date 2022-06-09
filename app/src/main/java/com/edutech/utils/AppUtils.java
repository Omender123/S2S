package com.edutech.utils;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.edutech.Api.ApiService;
import com.edutech.Api.S2SManager;
import com.edutech.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

public class AppUtils {
    public static final int PERMISSION_REQUEST_CODE = 200;


    public static ApiService S2SApi(Context context) {
        return S2SManager.getRetrofit(context).create(ApiService.class);
    }

    public static Dialog hideShowProgress(Context context) {
        Dialog dialog = new Dialog(context);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.progress_dialog, null);
        dialog.setContentView(dialogView);
        dialog.setCancelable(false);

        return dialog;
    }

    public static void shareApp(Context context, String myReferral_code) {

        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            String sAux = "Hey,\n \n" + "Its amazing install Klicked   \n Referral code : " + myReferral_code + "\n Download " + context.getResources().getString(R.string.app_name) + "\n" + "Available for Android & iPhone " + "\n";
            sAux = sAux + "For Android : https://play.google.com/store/apps/details?id=" + context.getPackageName() + "\n" /*+"For iPhone : https://apps.apple.com/be/app/imx/id1558636368"+"\n"*/;
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            context.startActivity(Intent.createChooser(i, "choose one"));
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public static boolean checkAndRequestPermissions(Activity context) {
        if (context != null) {

            int storagePermission = ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE);
            int storageWritePermission = ContextCompat.checkSelfPermission(context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);

            int permissionCamera = ContextCompat.checkSelfPermission(context,
                    Manifest.permission.CAMERA);

            int RecordAudioPermission = ContextCompat.checkSelfPermission(context,
                    Manifest.permission.RECORD_AUDIO);
            int WriteSettingPermission = ContextCompat.checkSelfPermission(context,
                    Manifest.permission.WRITE_SETTINGS);
            List<String> listPermissionsNeeded = new ArrayList<>();
            if (storagePermission != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }

            if (storageWritePermission != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }

            if (permissionCamera != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.CAMERA);
            }


            if (RecordAudioPermission != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.RECORD_AUDIO);
            }

            if (WriteSettingPermission != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.WRITE_SETTINGS);
            }

            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(context,
                        listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), PERMISSION_REQUEST_CODE);
                return false;
            }
        } else {
            return false;
        }

        return true;
    }


    public static void hideKeyboard(View view, Context context) {
        try {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception ignored) {
        }
    }

    public static void FullScreen(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }


    private WindowManager.LayoutParams getLayoutParams(@NonNull Dialog dialog) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        if (dialog.getWindow() != null) {
            layoutParams.copyFrom(dialog.getWindow().getAttributes());
        }
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        return layoutParams;
    }


    public static String getDateTime(String timeStamp) {
        String dateTime = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = sdf.parse(timeStamp);

            SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm a");
            sdf2.setTimeZone(TimeZone.getDefault());
            dateTime = sdf2.format(date);
            System.out.println("ssss" + dateTime);
            return dateTime;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("ssss" + dateTime);

        return dateTime;
    }

    public static String getDateTime1(String timeStamp, String pattern) {
        String dateTime = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = sdf.parse(timeStamp);

            SimpleDateFormat sdf2 = new SimpleDateFormat(pattern);
            sdf2.setTimeZone(TimeZone.getDefault());
            dateTime = sdf2.format(date);
            System.out.println("ssss" + dateTime);
            return dateTime;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("ssss" + dateTime);

        return dateTime;
    }


    public static String getCurrentDate(String parrten) {
        String date1 = "";
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat dateFormat = new SimpleDateFormat(parrten);
        date1 = dateFormat.format(calendar.getTime());

        return date1;
    }


    public static Boolean getCompareDate(String date) {
        Date Date1, Date2;
        long millse, mills;
        Boolean date1 = false;

        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        String currentDates = sdf.format(today);
        //String beforeDates = sdf.format(date);

        try {


            Date1 = sdf.parse(date);
            Date2 = sdf.parse(currentDates);


            millse = Date1.getTime() - Date2.getTime();
            mills = Math.abs(millse);


            long hh = (mills / (1000 * 60 * 60));
            long Mins = (int) (mills / (1000 * 60)) % 60;
            long Secs = (int) (mills / 1000) % 60;
            long timeDifDays = mills / (24 * 60 * 60 * 1000);

            if (hh >= 24) {

                date1 = true;
            }
        } catch (Exception e) {

        }


        return date1;
    }


    public static void showMessageOKCancel(String message, Activity activity, DialogInterface.OnClickListener okListener) {
        new android.app.AlertDialog.Builder(activity)
                .setMessage(message)
                .setPositiveButton("OKAY", okListener)
                .create()
                .show();
    }


}
