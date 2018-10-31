package com.example.co.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity
    implements View.OnClickListener {

    private TextView textViewRed;
    private TextView textViewBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textViewRed = findViewById(R.id.textViewRed);
        textViewBlue = findViewById(R.id.textViewBlue);

        // 자신객체를 OnClickListener인터페이스의 구현객체로 만들어서 this가 가능
        textViewRed.setOnClickListener(this);
        textViewBlue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent();
        // 어떤 텍스트뷰가 클릭했는지 구분하는 코드가 필요합니다.
        switch (v.getId()){
            case R.id.textViewRed:
                i = new Intent();
                i.putExtra("color", "Red");
                break;

            case R.id.textViewBlue:
                i = new Intent();
                i.putExtra("color", "Blue");

                break;
        }
        // 데이터를 요구한 액티비티에게 응답합니다.
        setResult(400, i);

        // 액티비티를 코드적으로 파괴시키는 행동입니다.
        finish();
    }
}
