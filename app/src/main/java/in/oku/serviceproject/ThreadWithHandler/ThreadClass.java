package in.oku.serviceproject.ThreadWithHandler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class ThreadClass implements Runnable {

    private HandlerClass mHandlerClass;

    @Override
    public void run() {

        if (Looper.myLooper() == null)
            Looper.prepare();

        mHandlerClass = new HandlerClass(Looper.myLooper());
        Message msg = mHandlerClass.obtainMessage();

        Bundle mBundle = new Bundle();
        mBundle.putString("STRING", "ABHILASH CHIKARA");
        msg.setData(mBundle);
        mHandlerClass.handleMessage(msg);

        Looper.loop();
    }


    class HandlerClass extends Handler {

        HandlerClass(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            Bundle mBundle = msg.getData();
            Log.e("STRING", mBundle.getString("STRING"));
        }

    }
}
