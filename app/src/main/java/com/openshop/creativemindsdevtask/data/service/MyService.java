package com.openshop.creativemindsdevtask.data.service;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.openshop.creativemindsdevtask.R;


public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "creative mind task", Toast.LENGTH_SHORT).show();

        notifyPlayer(getApplicationContext());
        Intent i = new Intent();
        i.setAction("com.medo.creative");
        sendBroadcast(i);


        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"alarm stopped" , Toast.LENGTH_SHORT).show();


    }
    public  void notifyPlayer(Context context )
    {
        int id = 0;


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources() , R.drawable.ic_launcher_background))
                .setContentTitle("check updates");

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            String channel_id = "Online_id";
            @SuppressLint("WrongConstant") NotificationChannel channel = new NotificationChannel(channel_id,
                    "channel human readable title"
                    , NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
            builder.setChannelId(channel_id);


        }
        manager.notify(id,builder.build());
        id++;

    }

}
