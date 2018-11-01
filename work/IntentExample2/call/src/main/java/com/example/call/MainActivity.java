package com.example.call;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCall;
    private Button btnDialer;
    private EditText editText;
    private String phoneNumber;
    private String telNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCall = (Button) findViewById(R.id.callPhone);
        btnDialer = (Button) findViewById(R.id.callDialer);
        editText = (EditText) findViewById(R.id.editText);

        btnCall.setOnClickListener(this);
        btnDialer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        phoneNumber = editText.getText().toString();

        telNumber = "tel:" + phoneNumber;

        switch (v.getId()) {
            case R.id.callPhone:
                // 사용자의 OS 버전이 마시멜로우 이상인지 체크한다.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                  // 사용자 단말기의 권한 중 "전화걸기" 권한이 허용되어 있는지 확인한다.
                    int permissionResult = checkSelfPermission(Manifest.permission.CALL_PHONE);
                    /* 패키지는 안드로이드 어플리케이션의 아이디이다. 현재 어플리케이션이
                      CALL_PHONE에 대해 거부되어있는지 확인한다. */
                    if (permissionResult == PackageManager.PERMISSION_DENIED) {
                     /* 사용자가 CALL_PHONE 권한을 거부한 적이 있는지 확인한다. 거부한적이 있으면
                         True를 리턴하고 거부한적이 없으면 False를 리턴한다. */
                        if (shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                            dialog.setTitle("권한이 필요합니다.")
                                    .setMessage("이 기능을 사용하기 위해서는 단말기의 \"전화걸기\" 권한이 필요합니다. 계속 하시겠습니까?")
                                    .setPositiveButton("네", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // 새로운 인스턴스(onClickListener)를 생성했기 때문에 버전체크를 다시 해준다.
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                // CALL_PHONE 권한을 Android OS에 요청한다.
                                                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1000);
                                            }
                                        }
                                    })
                                    .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(MainActivity.this, "기능을 취소했습니다",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }).create().show();
                        }
                        // 최초로 권한을 요청할 때
                        else {
                            // CALL_PHONE 권한을 Android OS에 요청한다
                            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1000);
                        }
                    }
                    // CALL_PHONE의 권한이 있을 때
                    else {

                        startActivity(new Intent("android.intent.action.CALL", Uri.parse(telNumber)));
                    }

                }
                break;
            case R.id.callDialer:
                // 구글 개발자가 미리 만들어 놓은 전화거는 앱의 액티비티는
                // android.intent.action.DIAL 이라는 인텐트 필터가 설정되어 있다.
                // 따라서, 이 요청을 받은 안드로이드는 전화거는 앱을 연결해 줍니다.
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(telNumber)));
                break;
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1000) {
            // 요청한 권한을 사용자가 "허용" 했다면...
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(telNumber));
                /*
                    Intent.ACTION_CALL 인텐트 필터가 설정되어 있는 액티비티를 가진 앱에게 telNunber를
                    uri 객체로 감싸서 전달합니다. 이 요청을 받는 안드로이드는 모든 앱을 조사하여
                    Intent.ACTION_CALL 인텐트 필터가 설정되어 있는 앱이 하나라면 바로 연결해 주고,
                    다수라면 그 앱의 목록을 사용자에게 표시하여 사용자가 선택할 수 있게끔 합니다.
                    명시적 인텐트: 주민번호 950.... 해당 사람에게 전달해 주세요.
                    암시적 인텐트: 조건을 부여하고 그에 맞는 사람'들'에게 전달해 주세요.
                */


                // Add Check Permission
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    startActivity(intent);
                }
            } else {
                Toast.makeText(MainActivity.this, "권한요청을 거부했습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


