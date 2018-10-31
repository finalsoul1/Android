package com.example.co.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button2;
    private final String TAG = "MainActivity";

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 로그를 출력할 때 Log 클래스가 지원하는 메소드를 사용합니다.

        Log.d(TAG,".onCreate()");
        // 화면정보를 가진 XML을 로드해서 화면을 구성한다
        // R.java 파일은 자동으로 생성/업데이트를 됩니다
        // 이 파일안에서 res폴더 밑에 존재하는 모든 자원을 관리한다

        setContentView(R.layout.activity_main);
        // setContentView() 메소드가 처리된 후에는 XML안에 정의한
        // 뷰를 객체로써 접근하여 사용할 수 있습니다.

        // id가 button2인 뷰 객체의 참조를 획득하여 해당 뷰에 이벤트 리스너를 장착합니다.
        button2 = findViewById(R.id.button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this, "2번 버튼 클릭",
                        Toast.LENGTH_LONG);
                toast.show();
                //
            }
        });

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void onClickButton1(View view) {
        Toast toast = Toast.makeText(this, "1번 버튼 클릭", Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,".onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,".onResume()");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,".onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,".onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,".onDestroy()");
    }


    public void onClickMeThod(View view) {
        // 다음화면 정보를 인테트 객체에 담습니다.
        // Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("key", 100);

        // 다음화면을 띄웁니다.
        // 액티비티는 안드로이드가 정한 액티비티 라이프 사이클 메소드가 기동해야만 화면이 처리되므로
        // 개발자가 직접 해당 객체를 만들어서 호출하는 것이 아니라 안드로이드에게 작업을 요청해야합니다.
        startActivity(intent);
    }


    // 화면이 넘어가서 스탑했다가 다시 화면을 띄울때 호출되는 메소드
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, ".onRestart");
    }
}
