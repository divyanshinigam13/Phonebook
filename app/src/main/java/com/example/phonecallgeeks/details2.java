package com.example.phonecallgeeks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class details2 extends AppCompatActivity {

    TextView dname,dno,dcall,dsms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details2);
        androidx.appcompat.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar));
        dname=findViewById(R.id.dname);
        dno=findViewById(R.id.dno);
        dcall=findViewById(R.id.dcall);
        dsms=findViewById(R.id.dsms);
        String contactname=getIntent().getStringExtra("keynamelog");
        String contactnumber=getIntent().getStringExtra("keynumberlog");
        dname.setText(contactname);
        dno.setText(contactnumber);
        dcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent=new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+contactnumber));
                if (ActivityCompat.checkSelfPermission(details2.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
                {
                    requestPermission();
                }
                else
                {
                    startActivity(callIntent);

                }
            }
        });
        dsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsintent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + contactnumber));

                if (ActivityCompat.checkSelfPermission(details2.this,
                        Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions1();
                }
                else{
                    startActivity(smsintent);
                }
            }
        });

    }
    private void requestPermission() {
        ActivityCompat.requestPermissions(details2.this,new String[]{Manifest.permission.CALL_PHONE},1);
    }

    private void requestPermissions1() {
        ActivityCompat.requestPermissions(details2.this,new String[]{Manifest.permission.SEND_SMS},1);
    }

}