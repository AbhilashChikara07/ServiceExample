package in.oku.serviceproject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class demo extends AppCompatActivity {

    Handler handler;

    @Override
    public void setContentView(View view) {
        super.setContentView(R.layout.activity_main);

        final Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Message message = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putString("JAAT", "JAAT");
                message.setData(bundle);
                handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);

                    }
                };
                Looper.loop();
            }
        });

        th.start();
    }


    class HandlerClass extends Handler {

        HandlerClass(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }

}
