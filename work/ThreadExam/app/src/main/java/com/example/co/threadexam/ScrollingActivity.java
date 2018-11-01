package com.example.co.threadexam;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ScrollingActivity extends AppCompatActivity {

    private TextView textView;

    // 메인스레드의 루퍼가 가진 Queue에 메세지를 전당하는 객체입니다.
    // getMainLooper()
    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        textView = findViewById(R.id.textView);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // 시간이 오래 걸리는 로직을 메인스레드가 처리하면 안됩니다.
        // 안드로이드는 네트워크 관련 처리는 시간이 오래걸린다고 판단하여
        // 네트워크 로직은 반드시 개별적인 스레드에서 처리해야만 인정합니다.

        // count();

        // #1
        new MyThread().start();
        // #2
//        new Thread(new MyRunnable()).start();
    }

    int i;
    private void count() {
        for (i = 1; i <= 20; i++){
            /*
                메인스레드만 뷰를 건드릴 수 있다.
             */
            // 해결책 : 메인이 아닌 다른 스레드가 직접 화면을 제어하지 못하므로 핸들러를 통해서
            // 메인 스레드에게 전달
            handler.post(new MyRunnable(){
                @Override
                public void run() {
                    textView.setText("" + i);
                }
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            count();
        }
    }

    class MyRunnable implements Runnable{
        @Override
        public void run() {
            count();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
