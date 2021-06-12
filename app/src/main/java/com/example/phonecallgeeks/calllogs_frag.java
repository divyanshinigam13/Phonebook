package com.example.phonecallgeeks;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.CallLog;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class calllogs_frag extends Fragment {

View v;
    RecyclerView logsrecycle;
    calllogsAdapter calllogsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       v=inflater.inflate(R.layout.fragment_calllogs, container, false);
        logsrecycle=v.findViewById(R.id.logsrecycle);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager=linearLayoutManager;
        logsrecycle.setLayoutManager(layoutManager);
        calllogsAdapter=new calllogsAdapter((Activity) getContext(),getCallogs());
        logsrecycle.setAdapter(calllogsAdapter);
        FloatingActionButton numpad=v.findViewById(R.id.numpad);
        numpad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),numpad.class)   ;
                getContext().startActivity(intent);
            }
        });

        return v;
    }

    private ArrayList<calllogs> getCallogs() {
        ArrayList<calllogs> callLogsArrayList=new ArrayList<>();
        Uri uri= CallLog.Calls.CONTENT_URI;
        String sort = CallLog.Calls.DEFAULT_SORT_ORDER;
        Cursor cursor = getContext().getContentResolver().query(uri, null, null, null,sort);
        cursor.moveToFirst();
        while(cursor.moveToNext()){


          String name=cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
            String number=cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
            String duration=cursor.getString(cursor.getColumnIndex(CallLog.Calls.DURATION));
            String type=cursor.getString(cursor.getColumnIndex(CallLog.Calls.TYPE));
String month,date,day;
            Date date1=new Date(Long.valueOf(cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE))));
         day = (String) DateFormat.format("dd",date1);
date=(String) DateFormat.format("EEEE",date1);
month=(String) DateFormat.format("MMMM",date1);
            SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm");
            int min=Integer.parseInt(duration)/60;
            int sec=Integer.parseInt(duration)%60;


String time=dateFormat.format(date1);
calllogs calllogs=new calllogs();
if (name==null){
    calllogs.setCname("Unknown");

}else{
    calllogs.setCname(name);

}


      calllogs.setCno(number);

      calllogs.setCdate(date+" "+day+" "+month);
      calllogs.setCtime(time);

      switch (type) {
          case "1":
              calllogs.setCtype("Incoming :");
              break;
          case "2":
              calllogs.setCtype("Outgoing :");
              break;
          case "5":
              calllogs.setCtype("Rejected :");
              break;
          case "3":
              calllogs.setCtype("Missed :");


      }
      if(sec==0){
         calllogs.setCdur("Not Connected");
      }
      else if(min==0){
          calllogs.setCdur(sec+"sec");
      }else {
          calllogs.setCdur( min+"min "+sec+"sec ");
      }
callLogsArrayList.add(calllogs);
        }
        return callLogsArrayList;

    }
}