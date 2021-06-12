package com.example.phonecallgeeks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tab;
    ViewPager view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        androidx.appcompat.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar));

        tab = findViewById(R.id.tab);
        view = findViewById(R.id.viewp);

        checkPermission();

    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS,Manifest.permission.READ_CALL_LOG}, 100);
        }
       else{
           createFragment();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {
       createFragment() ;

        }
           else{
           checkPermission();
        }


    }
    public void createFragment()
    {
        fragmentadapter adapter = new fragmentadapter(getSupportFragmentManager());
        adapter.addFragment(new calllogs_frag(), "call logs");
        adapter.addFragment(new contacts_frag(), "Contacts");

        view.setAdapter(adapter);
        tab.setupWithViewPager(view);
    }
}