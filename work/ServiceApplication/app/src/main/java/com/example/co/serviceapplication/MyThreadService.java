package com.example.co.serviceapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyThreadService extends Service {
    private static final String MESSAGE = "Hello Thread Service";
    private boolean isRun = false;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(MESSAGE, "Service Running...");
        isRun = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(MESSAGE, "Service Onstart...");
        Log.i(MESSAGE, "data = " + intent.getStringExtra("data"));
        //Thread를 이용하여 서비스 생성
        //길게 수행되는 서비스인 경우 분리된 쓰레드를 이용할 것
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (isRun) {
                        Log.i(MESSAGE, "Thread Service Running.........");
                    }
                }

                stopSelf(); //태스크가 끝나면 서비스 종료
            }
        }).start();

        // START_STICKY: 서비스가 일하고 있는데 사용자/안드로이드에 의해서 스마트폰이 재 시작하는 경우,
        // 안드로이드가 직접 이 서비스를 다시 객체로 만들어야 한다.
        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(MESSAGE, "Service onBind...");
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isRun = false;
        Log.i(MESSAGE, "Service onDestroy...");
    }
}
