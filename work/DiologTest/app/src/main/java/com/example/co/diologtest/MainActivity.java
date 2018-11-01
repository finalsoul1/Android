package com.example.co.diologtest;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button1;
    Button button2;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        textView = findViewById(R.id.textView3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog1(v);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog2(v);
            }
        });
    }

    private void showDialog1(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setIcon(R.mipmap.ic_launcher);
        builder.setIcon(android.R.drawable.star_big_on);
        builder.setTitle("상단 타이틀");

        builder.setMessage("중단 메세지");


        // 하단버튼들
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textView.setText("예이");
            }
        });
        builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textView.setText("취소");
            }
        });
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textView.setText("아니오");
            }
        });
//        AlertDialog dialog = builder.create();
//        dialog.show();

        builder.show();
    }

    private void showDialog2(View v) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("하나를 고르세요");

        // inflate: xml을 로드해서 화면으로 바꿈
        View view = View.inflate(this, R.layout.dialog, null);
        builder.setView(view);

        // 콜백함수는 언제 사용될지 모르는 함수인데 메소드코딩이 끝나면 지역변수는 사라짐
        // final로 선언하면 콜백함수 안에 자동으로 기입해줌
        // 다른 방법으로 이 변수를 멤버변수로 승급시키면 됨
        final AlertDialog alertDialog = builder.show();

        // 기본적으로 findViewById는 setContentView 에서 불러온 xml에서 찾음
        // 다른 xml껄 불러오려면 앞에 명시해 줘야함
        ImageView imageView = view.findViewById(R.id.imageView);
        ImageView imageView2 = view.findViewById(R.id.imageView2);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("보노보노");

                alertDialog.dismiss();
            }
        });

        // 그런데 여기서 alertDialog 변수를 조작하게 되면 콜백함수 안에서 사용하는 참조와
        // 다르게 되어 버그가 발생할 수 있게 되므로
        // 자바는 원천적으로 이런 변수 앞에 final 키워드를 붙여서 변수의 조작을 불허합니다.

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("너부리");

                alertDialog.dismiss();
            }
        });

    }
}
