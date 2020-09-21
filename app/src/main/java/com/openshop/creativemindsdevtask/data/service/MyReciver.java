package com.openshop.creativemindsdevtask.data.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.openshop.creativemindsdevtask.view.RepoListActivity;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


public class MyReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.medo.creative")) {
           Intent main = new Intent(context , RepoListActivity.class);
           main.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           context.startActivity(main);


        }
    }
}
