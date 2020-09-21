package com.openshop.creativemindsdevtask.data.helper;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Html;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class HelperMethod {
    public static ProgressDialog progressDialog;
    public static AlertDialog alertDialog;
    public static Animation bottontop_anim;
    private static Animation fade_anim;
    public static String image_url;
    private static Animation ltr_anim;
    private static Animation rtl_anim;

    public static void replaceFragment(FragmentManager getChildFragmentManager, int id, Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager.beginTransaction();
        transaction.replace(id, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
//
//    public static void showCalender(Context context, String title, final TextView text_view_data, final DateTxt data1) {
//        DatePickerDialog mDatePicker = new DatePickerDialog(context, AlertDialog.THEME_HOLO_DARK, new DatePickerDialog.OnDateSetListener() {
//            public void onDateSet(DatePicker datepicker, int selectedYear, int selectedMonth, int selectedDay) {
//                DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
//                DecimalFormat mFormat = new DecimalFormat("00", symbols);
//                String data = selectedYear + "-" + mFormat.format(Double.valueOf((selectedMonth + 1))) + "-" + mFormat.format(Double.valueOf(selectedDay));
//                data1.setDate_txt(data);
//                data1.setDay(mFormat.format(Double.valueOf(selectedDay)));
//                data1.setMonth(mFormat.format(Double.valueOf(selectedMonth + 1)));
//                data1.setYear(String.valueOf(selectedYear));
//                text_view_data.setText(data);
//            }
//        }, Integer.parseInt(data1.getYear()), Integer.parseInt(data1.getMonth()) - 1, Integer.parseInt(data1.getDay()));
//        mDatePicker.setTitle(title);
//        mDatePicker.show();
//    }

    public static Date convertDateString(String date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            Date parse = format.parse(date);

            return parse;

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

//    public static DateTxt convertStringToDateTxtModel(String date) {
//        try {
//            Date date1 = convertDateString(date);
//            String day = (String) DateFormat.format("dd", date1); // 20
//            String monthNumber = (String) DateFormat.format("MM", date1); // 06
//            String year = (String) DateFormat.format("yyyy", date1); // 2013
//
//            return new DateTxt(day, monthNumber, year, date);
//
//        } catch (Exception e) {
//            return null;
//        }
//    }



    public static void setInitRecyclerViewAsLinearLayoutManager(Context context, RecyclerView recyclerView, LinearLayoutManager linearLayoutManager) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public static void setInitRecyclerViewAsGridLayoutManager(Activity activity, RecyclerView recyclerView, GridLayoutManager gridLayoutManager, int numberOfColumns) {
        gridLayoutManager = new GridLayoutManager(activity, numberOfColumns);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    public static void showProgressDialog(Activity activity, String title) {
        try {

            progressDialog = new ProgressDialog(activity);
            progressDialog.setMessage(title);
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);

            progressDialog.show();

        } catch (Exception e) {

        }
    }

    public static void dismissProgressDialog() {
        try {
            progressDialog.dismiss();
        } catch (Exception e) {
            progressDialog.dismiss();
        }
    }

    public static void disappearKeypad(Activity activity, View v) {
        try {
            if (v != null) {
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        } catch (Exception e) {

        }
    }

    public static void changeLang(Context context, String lang) {
        Resources res = context.getResources();
        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(lang)); // API 17+ only.
        // Use conf.locale = new Locale(...) if targeting lower versions
        res.updateConfiguration(conf, dm);
    }

    public static void htmlReader(TextView textView, String s) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml(s, Html.FROM_HTML_MODE_COMPACT));
        } else {
            textView.setText(Html.fromHtml(s));
        }
    }


    public static void onPermission(Activity activity) {
        String[] perms = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.CALL_PHONE};

        ActivityCompat.requestPermissions(activity,
                perms,
                100);

    }

    public static boolean checkWriteExternalPermission(Context context) {
        boolean check = false;
        String permission1 = Manifest.permission.ACCESS_FINE_LOCATION;
        int res1 = context.checkCallingOrSelfPermission(permission1);
        if (res1 != PackageManager.PERMISSION_GRANTED) {
            check = true;
        }
//
//

        String permission3 = Manifest.permission.READ_EXTERNAL_STORAGE;
        int res3 = context.checkCallingOrSelfPermission(permission3);
        if (res3 != PackageManager.PERMISSION_GRANTED) {
            check = true;
        }

        String permission4 = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        int res4 = context.checkCallingOrSelfPermission(permission4);
        if (res4 != PackageManager.PERMISSION_GRANTED) {
            check = true;
        }

        String permission6 = Manifest.permission.CALL_PHONE;
        int res6 = context.checkCallingOrSelfPermission(permission6);
        if (res6 != PackageManager.PERMISSION_GRANTED) {
            check = true;
        }

        return check;
    }


//    public static void changeLang(Context context) {
//        Resources res = context.getResources();
//        // Change locale settings in the app.
//        DisplayMetrics dm = res.getDisplayMetrics();
//        android.content.res.Configuration conf = res.getConfiguration();
//        conf.setLocale(new Locale(LoadStringData((Activity) context, USER_LANG))); // API 17+ only.
//        // Use conf.locale = new Locale(...) if targeting lower versions
//        res.updateConfiguration(conf, dm);
//    }

    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    //TODO animation methods
//    public static void startBottomTopAnimation(View view , Context context)
//    {
//        view.setVisibility(View.VISIBLE);
//        bottontop_anim= AnimationUtils.loadAnimation(context, R.anim.anim_translate_from_bottom);
//        view.startAnimation(bottontop_anim);
//
//    }
//    public static void startFadenimation(View view , Context context)
//    {
//        view.setVisibility(View.VISIBLE);
//        fade_anim= AnimationUtils.loadAnimation(context, R.anim.anim_fade);
//        view.startAnimation(fade_anim);
//
//    }
//    public static void startLTRanimation(View view , Context context)
//    {
//        view.setVisibility(View.VISIBLE);
//        ltr_anim = AnimationUtils.loadAnimation(context, R.anim.anim_translate_from_start);
//        view.startAnimation(ltr_anim);
//
//    }
//    public static void startRTLanimation(View view , Context context)
//    {
//        view.setVisibility(View.VISIBLE);
//        rtl_anim = AnimationUtils.loadAnimation(context, R.anim.anim_translate_from_end);
//        view.startAnimation(rtl_anim);
//
//    }
    //TODO multipart methods
    public static MultipartBody.Part convertFileToMultipart(String pathImageFile, String Key) {
        if (pathImageFile != null) {
            File file = new File(pathImageFile);

            RequestBody reqFileselect = RequestBody.create(MediaType.parse("image/*"), file);

            MultipartBody.Part Imagebody = MultipartBody.Part.createFormData(Key, file.getName(), reqFileselect);

            return Imagebody;
        } else {
            return null;
        }
    }

    public static RequestBody convertToRequestBody(String part) {
        try {
            if (!part.equals("")) {
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), part);
                return requestBody;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    //TODO share
//    public static  void  shareDialoge(Context context)
//    {
//        Intent sendIntent = new Intent();
//        sendIntent.setAction(Intent.ACTION_SEND);
//        sendIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.download_the_app);
//        sendIntent.putExtra(Intent.EXTRA_TEXT, "http://play.google.com/store/apps/details?id="+BuildConfig.APPLICATION_ID);
//        sendIntent.setType("img/plain");
//     context.startActivity(Intent.createChooser(sendIntent, "اختار التطبيق الذي مشاركة النص معه :"));
//
//
//    }


    }






