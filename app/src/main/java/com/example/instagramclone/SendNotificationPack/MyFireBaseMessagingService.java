package com.example.instagramclone.SendNotificationPack;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.instagramclone.Fragments.NotificationFragment;
import com.example.instagramclone.MainActivity;
import com.example.instagramclone.MessageActivity;
import com.example.instagramclone.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MyFireBaseMessagingService extends FirebaseMessagingService {
    String title,message;
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        title = remoteMessage.getData().get("Title");
        message = remoteMessage.getData().get("Message");
        String CHANNEL_ID = "Follow";
        String CHANNEL_NAME = "Follow";

        getnotification(CHANNEL_ID,CHANNEL_NAME,title,message);





//            String sented = remoteMessage.getData().get("sented");
//            String user = remoteMessage.getData().get("user");
//            String icon = remoteMessage.getData().get("icon");
//            String title = remoteMessage.getData().get("title");
//            String body = remoteMessage.getData().get("body");
//
//            SharedPreferences preferences = getSharedPreferences("PREFS", MODE_PRIVATE);
//            String currentUser = preferences.getString("currentuser", "none");
//
//            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
////            if (sented.equals(firebaseUser.getUid())) {
//                if (!currentUser.equals(user)) {
//                    Intent intent = new Intent(this, MessageActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("userid", user);
//                    intent.putExtras(bundle);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    PendingIntent pendingIntent1 = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
//                    Notification notification1 = new Notification();
//                    NotificationCompat.Builder builder1;
//                    builder1 = new NotificationCompat.Builder(MyFireBaseMessagingService.this, "CHANNEL");
//                    //Notification notification = new NotificationCompat.Builder(MyFireBaseMessagingService.this,CHANNEL_ID)
//                    notification1 = builder1.setSmallIcon(Integer.parseInt(icon))
//                            .setContentIntent(pendingIntent1)
//                            .setDefaults(Notification.DEFAULT_VIBRATE)
//                            .setContentTitle(title)
//                            .setContentText(body)
//                            .setColor(0xFFFF0000)
//                            .setSound(soundUri)
//                            .setAutoCancel(true)
//                            .build();
//                    manager2.notify(getRandomNumber(), notification);
//                }
            //}
        }

    private void getnotification(String CHANNEL_ID,String CHANNEL_NAME,String title,String message) {

        NotificationManagerCompat manager = NotificationManagerCompat.from(getApplicationContext());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);

            Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            sound = Uri.parse("android.resource://" + getApplication().getPackageName() + "/" + R.raw.abe);
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build();

            channel.setSound(sound, audioAttributes);
            channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            manager.createNotificationChannel(channel);
            //}
            Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            soundUri = Uri.parse("android.resource://" + getApplication().getPackageName() + "/" + R.raw.abe);
            AudioManager managers = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
            managers.setStreamVolume(AudioManager.STREAM_MUSIC, 100, 0);


            Intent notificationIntent = new Intent(MyFireBaseMessagingService.this, MainActivity.class);
            notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(MyFireBaseMessagingService.this, 0, notificationIntent, 0);
            Notification notification = new Notification();
            NotificationCompat.Builder builder;
            builder = new NotificationCompat.Builder(MyFireBaseMessagingService.this, CHANNEL_ID);
            notification = builder.setSmallIcon(R.drawable.likes)
                    .setContentIntent(pendingIntent)
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setColor(0xFFFF0000)
                    .setSound(soundUri)
                    .setAutoCancel(true)
                    .build();
            manager.notify(getRandomNumber(), notification);

        }
    }
    private static int getRandomNumber() {
        Date dd= new Date();
        SimpleDateFormat ft =new SimpleDateFormat ("mmssSS");
        String s=ft.format(dd);
        return Integer.parseInt(s);
    }



//    private void sendNotification(RemoteMessage remoteMessage) {
//
//        String user = remoteMessage.getData().get("user");
//        String icon = remoteMessage.getData().get("icon");
//        String title = remoteMessage.getData().get("title");
//        String body = remoteMessage.getData().get("body");
//
//        RemoteMessage.Notification notification = remoteMessage.getNotification();
//        int j = Integer.parseInt(user.replaceAll("[\\D]", ""));
//        Intent intent = new Intent(this, MessageActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putString("userid", user);
//        intent.putExtras(bundle);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pending = PendingIntent.getActivity(this, j, intent, PendingIntent.FLAG_ONE_SHOT);
//
//        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
//                .setSmallIcon(Integer.parseInt(icon))
//                .setContentTitle(title)
//                .setContentText(body)
//                .setAutoCancel(true)
//                .setSound(defaultSound)
//                .setContentIntent(pending);
//        NotificationManager noti = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//
//        int i = 0;
//        if (j > 0){
//            i = j;
//        }
//
//        noti.notify(i, builder.build());
//    }
}
