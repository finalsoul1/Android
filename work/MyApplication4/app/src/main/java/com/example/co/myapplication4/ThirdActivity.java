package com.example.co.myapplication4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }

    public void onClickBefore(View view) {
//        Intent intent = new Intent(this, SecondActivity.class);
//        startActivity(intent);

        finish();
    }

    public void onClickNext(View view) {
        // 암시적 인텐트로 MainActivity를 호출합니다.
        Intent intent = new Intent();
        intent.setAction("this.is.good.activity");
        intent.addCategory("android.intent.category.DEFAULT");
        startActivity(intent);
    }

    public void onClickAnother(View view) {
        // 인텐트 플래그
        Intent intent = new Intent(this, MainActivity.class);

        // 백스택에서 MainActivity 를 찾는다. MainActivity 부터 위에 존재하는 모든 액티비티를
        // 파괴한다. (완전히 비우는 것은 아니다)
        // MainActivity 를 새로 만들어서 백스택 최상단에 넣는다.
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
