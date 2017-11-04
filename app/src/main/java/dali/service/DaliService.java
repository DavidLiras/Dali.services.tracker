package dali.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;



public class DaliService extends Service {



    @Override
    public IBinder onBind(Intent intent) {
        Log.i(Utils.TAG, "Service onBind");
        return null;
    }

    @Override
    public void onCreate() {
        Log.i(Utils.TAG, "Service onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(Utils.TAG, "Service onStartCommand");

        //Creating new thread for my service
        //Always write your long running tasks in a separate thread, to avoid ANR
        new Thread(new Runnable() {
            @Override
            public void run() {
                //Your logic that service will perform will be placed here
                //In this example we are just looping and waits for 1000 milliseconds in each loop.
                while (true) {
                    try {
                        Thread.sleep(1000);
                        Log.i(Utils.TAG, "Service running in: " + Utils.getDeviceName());
                        Utils.Send2Dweet("lat=40.5466688&long=-3.652186,15");
                    } catch (Exception e) {
                        Log.e(Utils.TAG, e.getMessage());
                    }

                }
                //Stop service once it finishes its task
                //stopSelf();
            }
        }).start();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(Utils.TAG, "Service onDestroy");
    }
}
