package in.oku.serviceproject.IntentService;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;


/*
 * Intent service is a sub class of service .
 * onHandleIntent(Intent intent) method is work on worker thread.
 * We can also implement or override service method. But we don't
 * need all these method escape onHandleIntent(Intent intent).
 * To start a intent service you always need a default constructor with super keyword.
 * */


public class IntentServiceClass extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    /*
     * You always need a default constructor to start service.
     * */
    public IntentServiceClass() {
        super("IntentServiceClass");
        Log.e("intent-service-name----", "default constructor" );
    }

    public IntentServiceClass(String name) {
        super(name);
        Log.e("intent-service-name----", "" + name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("onCreate", "onCreate");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.e("onStartCommand", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e("onHandleIntent", "onHandleIntent");
    }

}
