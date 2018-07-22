package in.oku.serviceproject.ServiceClass;


/*
 * Service class always work on main thread.
 * So if you are going to do long running operation then it's very
 * necessary to create a separate thread. Else case is you application will get ANR.
 * You can create separate thread by Runnable interface or by Handler Thread.
 * onStartCommand() method return three different values like :- START_NOT_STICKY,
 * START_STICKY, START_REDELIVER_INTENT.
 * onCreate() method is call only first time when you start call.
 * If you start service many time then only one time onCreate() method is call
 * but onStartCommand() call always.
 * Handler thread is used for where you can perform some ui related work.
 * If you try to do ui related work in a thread it will give you exception.
 * Yes it is also true that you can create a separate handler and attached with a thread.
 * In both cases Looper play a very important role. Which used for keep thread alive.
 *
 * Looper is also used for get messages from message queue.
 * Normally thread cannot be reused once its job is completed.
 * But thread with Looper is kept alive until you call quit
 * method so you don’t need to create a new instance each time you
 * want to run a job in background.
 *
 * Any thread can have only one unique Looper
 * MessageQueue is a queue that has tasks called messages which should be processed.
 * Android maintains a MessageQueue on the main thread.
 * https://medium.com/@ankit.sinhal/messagequeue-and-looper-in-android-3a18c7fc9181
 * */

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;

public class ServiceClass extends Service {

    HandlerThreadClass mHandlerThreadClass;
    HandlerThread mHandlerThread;

    @Override
    public void onCreate() {
        super.onCreate();
        mHandlerThread = new HandlerThread("HANDLER THREAD",
                Process.THREAD_PRIORITY_DEFAULT);
        mHandlerThread.start();
        mHandlerThreadClass = new HandlerThreadClass(mHandlerThread.getLooper());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        /*
         * mHandlerThreadClass.obtainMessage() :- You can create a new message queue or
         * you can use this function to obtain a message queue.
         * */

        Message mMessageQueue = mHandlerThreadClass.obtainMessage();
        mMessageQueue.arg1 = startId;

        Bundle bundle = new Bundle();
        bundle.putString("NAME", "ABHILASH CHIKARA");

        mMessageQueue.setData(bundle);

        mHandlerThreadClass.sendMessage(mMessageQueue);

        return START_STICKY;
    }

    /*
     * We don't need this method.
     * If you want to bind a component with service for regularly update.
     * It this case IBinder only method which come in action.
     * */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    private class HandlerThreadClass extends Handler {

        public HandlerThreadClass(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            Log.e("NAME-", bundle.getString("NAME"));
            Log.e("Message", "" + msg.arg1);
            stopSelf(msg.arg1);
        }

    }

}
