package in.oku.serviceproject.ForeGroundService;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import android.widget.Toast;

import in.oku.serviceproject.R;


public class ForeGroundService extends Service {

    public final static String START_MUSIC_PLAYER = "START_MUSIC_PLAYER";
    public final static String STOP_MUSIC_PLAYER = "STOP_MUSIC_PLAYER";

    public static final String ACTION_PAUSE = "ACTION_PAUSE";
    public static final String ACTION_PLAY = "ACTION_PLAY";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /*
     * This method call once when you start service.
     * Can say that this the initial point of service.
     * */
    @Override
    public void onCreate() {
        super.onCreate();
    }


    /*
     * This method call always when you start service.
     * */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String ACTION = intent.getAction();
        switch (ACTION) {

            case START_MUSIC_PLAYER: {
                startService();
                break;
            }

            case STOP_MUSIC_PLAYER: {
                stopForeground(true);
                stopSelf();
                break;
            }

            case ACTION_PLAY: {
                Toast.makeText(getApplicationContext(),
                        "You click Play button.",
                        Toast.LENGTH_LONG).show();
                break;
            }

            case ACTION_PAUSE: {
                Toast.makeText(getApplicationContext(),
                        "You click Pause button.",
                        Toast.LENGTH_LONG).show();
                break;
            }

        }

        return START_STICKY;

    }

    private void startService() {

        Intent intent = new Intent();
        PendingIntent pendingIntent = PendingIntent
                .getActivity(this,
                        0,
                        intent,
                        0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat
                        .Builder(this,
                        "");

        NotificationCompat.BigTextStyle bigTextStyle =
                new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle("Music player implemented " +
                "by foreground service.");

        bigTextStyle.bigText("Android foreground service is a " +
                "android service which can run in foreground always, " +
                "it can be controlled by user via notification.");
        mBuilder.setStyle(bigTextStyle);
        mBuilder.setWhen(System.currentTimeMillis());
        mBuilder.setFullScreenIntent(pendingIntent, true);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setPriority(Notification.PRIORITY_MAX);


        /*
         * Add Play button intent in notification.
         * */
        Intent mPlayIntent = new Intent(this,
                ForeGroundService.class);
        mPlayIntent.setAction(ACTION_PLAY);
        PendingIntent mPlayPendingIntent = PendingIntent
                .getService(this,
                        0,
                        mPlayIntent,
                        0);
        NotificationCompat.Action mPlayAction =
                new NotificationCompat
                        .Action(android.R.drawable.ic_media_play,
                        "Play",
                        mPlayPendingIntent);
        mBuilder.addAction(mPlayAction);


        /*
         * Add Stop button intent in notification.
         * */
        Intent mStopIntent = new Intent(this,
                ForeGroundService.class);
        mStopIntent.setAction(STOP_MUSIC_PLAYER);
        PendingIntent mStopPendingIntent = PendingIntent
                .getService(this,
                        0,
                        mStopIntent,
                        0);
        NotificationCompat.Action mStopAction =
                new NotificationCompat
                        .Action(android.R.drawable.ic_media_play,
                        "Stop",
                        mStopPendingIntent);
        mBuilder.addAction(mStopAction);

        Notification mNotification = mBuilder.build();
        startForeground(1, mNotification);

    }

}
