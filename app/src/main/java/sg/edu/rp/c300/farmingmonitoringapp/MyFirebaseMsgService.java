package sg.edu.rp.c300.farmingmonitoringapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

//
// https://medium.com/innoctive-technologies/yet-another-tutorial-of-fcm-notifications-on-android-part-1-2d674ded7e1c
//

public final class MyFirebaseMsgService extends FirebaseMessagingService {
    private final String TAG = "FirebaseMsgService";

    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.d(TAG, "Refreshed FCM token: " + token);
        //sendTokenToServer(token);
    }

    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        int max = 1000000;
        int min = 0;
        int id = new Random().nextInt((max - min) + 1) + min;

        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Title: " + remoteMessage.getNotification().getTitle());
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            
            RemoteMessage.Notification givenNotification = remoteMessage.getNotification();
            String title = givenNotification.getTitle();
            String msg = givenNotification.getBody();
            Log.d(TAG, "onMessageReceived: count1: " + id);
            this.showNotification(title, msg, id);
        } else {
            String title = remoteMessage.getData().get("title");
            String msg = remoteMessage.getData().get("message");
            Log.d(TAG, "onMessageReceived: count2: " + id);
            this.showNotification(title, msg, id);
        }
    }

    private final void showNotification(String title, String body, Integer id) {
        setupNotificationChannels();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"default");
        builder.setContentTitle(title);
        builder.setContentText(body);
        builder.setSmallIcon(android.R.drawable.btn_star_big_off);
        builder.setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(id, builder.build());
    }

    private void setupNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default","Default Channel",NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("This is for default notification");

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
    }

}

