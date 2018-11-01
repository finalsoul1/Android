package com.example.co.serviceapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static String LOG_TAG = "MainActivity";

    Button btnStart1, btnStart2, btnStart3, btnStart4, btnPrint, btnStop1, btnStop2, btnStop3, btnStop4, btnNext;
    TextView textView;

    //BoundService에서 이용, MainActivity가 MyBoundService에 바인딩된지 여부
    boolean isBoundService = false;
    MyBoundService mService;

    // 멤버변수
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        // MyBoundService.onBind() 메소드의 리턴값이 service라는 이름의 파라미터로
        // 전달됩니다.
        // 안드로이드 프레임워크를 거쳐서 서비스를 이용하기위한 방법
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBoundService.MyBinder myBinder = (MyBoundService.MyBinder)
                    service;
            mService = myBinder.getService();
            isBoundService = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBoundService = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView_TimeStamp);

        // Audio Service, startService() 이용
        btnStart1 = (Button) findViewById(R.id.btnStart1);
        btnStop1 = (Button) findViewById(R.id.btnStop1);

        // 서비스 로직이 계속 수행중인 상태에서 액티비티가 사용자의 요청을 접수하여
        // 다른 행동을 수행할 수 있습니다.
        btnNext = (Button) findViewById(R.id.btnNext);

        // Bound Service, bindService() 이용
        btnStart2 = (Button) findViewById(R.id.btnStart2);
        btnStop2 = (Button) findViewById(R.id.btnStop2);
        btnPrint = (Button) findViewById(R.id.btnPrint);

        // Thread Service, startService() 이용
        btnStart3 = (Button) findViewById(R.id.btnStart3);
        btnStop3 = (Button) findViewById(R.id.btnStop3);

        //Audio Service, startService
        btnStart1.setOnClickListener(this);
        btnStop1.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        //Bound Service, bindService
        btnStart2.setOnClickListener(this);
        btnStop2.setOnClickListener(this);
        btnPrint.setOnClickListener(this);

        //Thread Service, startService
        btnStart3.setOnClickListener(this);
        btnStop3.setOnClickListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBoundService) {
            unbindService(mServiceConnection);
            isBoundService = false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //Audio Service, startService이용
            case R.id.btnStart1:
                Log.i(LOG_TAG, "Audio Service Start...");
                startService(new Intent(this, MyAudioService.class));

                break;
            case R.id.btnStop1:
                stopService(new Intent(this, MyAudioService.class));
                Log.i(LOG_TAG, "Audio Service Stop...");
                break;
            case R.id.btnNext:
                startActivity(new Intent(this, NextActivity.class));
                //Bound Service, bindService 이용

            case R.id.btnStart2:
                Intent intent = new Intent(this, MyBoundService.class);
                // 먼저 서비스 객체를 생성하고
//                startService(intent);
                // 그 다음 서비스 객체의 연결을 시도한다.
                bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
                Log.i(LOG_TAG, "Bound Service Start...");
                break;
            case R.id.btnStop2:
                if (isBoundService) {
                    unbindService(mServiceConnection);
                    isBoundService = false;
                    Log.i(LOG_TAG, "Bound Service Unbind...");
                }
                break;
            case R.id.btnPrint:
                if (isBoundService) {
                    textView.setText(mService.getTimStamp());
                }
                break;
            //Thread Service, startService이용
            case R.id.btnStart3:
                Intent i = new Intent(this, MyThreadService.class);
                i.putExtra("data","love ever!");
                startService(i);
                Log.i(LOG_TAG, "Thread Service Start...");
                break;
            case R.id.btnStop3:
                stopService(new Intent(this, MyThreadService.class));
                Log.i(LOG_TAG, "Thread Service Stop...");
                break;
        }
    }
}
