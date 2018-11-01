package com.example.co.intentexample2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        //MainActivity에서 넘기는 데이터를 받기 위해 인텐트 선언
//        Intent intent = new Intent(this.getIntent());
        Intent intent = getIntent();

        //MainActivity에서 넘기는 문자열 데이터를 받기위해 getStringExtra를 사용
        String str = intent.getStringExtra("sendText");

        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText(str);
    }
}
