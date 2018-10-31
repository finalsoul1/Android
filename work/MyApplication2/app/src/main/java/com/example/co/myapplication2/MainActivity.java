package com.example.co.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_LONG).show();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        // 메뉴를 XML에 해도 되지만 프로그램적으로 다이나믹하게 메뉴를 추가하거나 조작하고 싶을 때
        // 사용하는 메소드
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
        } else if(id == R.id.action_say) {
            Toast.makeText(MainActivity.this, "action_say 메뉴 선택",
                    Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickNext(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        // 다음 액티비티에게 응답을 요청합니다.
        startActivityForResult(intent, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 10){
            if(resultCode == 400){
                String color = data.getStringExtra("color");
                // 메인 액티비티가 가진 텍스트뷰에 두번째 액티비티가 전달한 문자열을 표시합니다.

                textView.setText(color);
            }
        }
    }
}
