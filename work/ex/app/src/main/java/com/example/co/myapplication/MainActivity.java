package com.example.co.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* 화면정보를 가진 XML을 로드해서 화면을 구성한다. */
        // R.java 파일은 자동으로 생성/업데이트 됩니다.
        // 이 파일에서 res 폴더 밑에 존재하는 모든 자원을 관리한다.
        setContentView(R.layout.activity_main);
    }

}
