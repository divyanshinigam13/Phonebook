package com.example.phonecallgeeks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class numpad extends AppCompatActivity {
Button zero,one,two,three,four,five,six,seven,eight,nine,hash,cross,star;
FloatingActionButton numcall;
TextView input;
String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_numpad);
        androidx.appcompat.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar));
        zero=findViewById(R.id.zero);
        one=findViewById(R.id.one);

        two=findViewById(R.id.two);
        three=findViewById(R.id.three);
        four=findViewById(R.id.four);
        five=findViewById(R.id.five);
        six=findViewById(R.id.six);
        seven=findViewById(R.id.seven);
        eight=findViewById(R.id.eight);
        nine=findViewById(R.id.nine);
        hash=findViewById(R.id.hash);
        cross=findViewById(R.id.cross);
        numcall=findViewById(R.id.numcall);
        input=findViewById(R.id.input);
        star=findViewById(R.id.star);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data=input.getText().toString();
                input.setText(data+"1");


            }

        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data=input.getText().toString();
                input.setText(data+"2");

            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data=input.getText().toString();
                input.setText(data+"3");

            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data=input.getText().toString();
                input.setText(data+"4");

            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data=input.getText().toString();
                input.setText(data+"5");

            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data=input.getText().toString();
                input.setText(data+"6");

            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data=input.getText().toString();
                input.setText(data+"7");

            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data=input.getText().toString();
                input.setText(data+"8");

            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data=input.getText().toString();
                input.setText(data+"9");

            }
        });
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data=input.getText().toString();
                input.setText(data+"0");

            }
        });
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data=input.getText().toString();
                input.setText(data+"*");

            }
        });
        hash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data=input.getText().toString();
                input.setText(data+"#");

            }
        });
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data=input.getText().toString();
                if(data.length()==0)
                {
                    input.setText("");
                }else {
                    String srt = data.substring(0, data.length() - 1);
                    input.setText(srt);
                }
            }
        });
        numcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent=new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+input.getText().toString()));
                if (ActivityCompat.checkSelfPermission(numpad.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
                {
                    requestPermission();
                }
                else
                {
                   startActivity(callIntent);

                }
            }
        });

    }
    private void requestPermission() {
        ActivityCompat.requestPermissions(numpad.this,new String[]{Manifest.permission.CALL_PHONE},1);
    }
}