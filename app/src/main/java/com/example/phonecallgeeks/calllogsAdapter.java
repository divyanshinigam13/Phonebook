package com.example.phonecallgeeks;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class calllogsAdapter extends RecyclerView.Adapter<calllogsAdapter.ViewHolder> {
    Context context;
    ArrayList<calllogs>calllogsArrayList;

    public calllogsAdapter(Context context, ArrayList<calllogs> calllogsArrayList) {
        this.context = context;
        this.calllogsArrayList = calllogsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.logslist,parent,false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
calllogs logs=calllogsArrayList.get(position);

if(logs.getCname().length()<14){
    holder.name.setText(logs.getCname());
}else{
    holder.name.setText(logs.getCname().substring(0,13)+"...");
}

holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent callIntent=new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+logs.getCno().toString()));
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
        {
            requestPermission();
        }
        else
        {
            context.startActivity(callIntent);

        }
    }
});
holder.no.setText(logs.getCno());
holder.dura.setText(logs.getCdur());
holder.type.setText(logs.getCtype());
holder.time.setText(logs.getCtime());
holder.date.setText(logs.getCdate());

holder.logperson.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(context,details2.class);
        intent.putExtra("keynamelog",logs.getCname());
        intent.putExtra("keynumberlog",logs.getCno());
        context.startActivity(intent);
    }
});

    }
    private void requestPermission() {
        ActivityCompat.requestPermissions((Activity) context,new String[]{Manifest.permission.CALL_PHONE},1);
    }

    @Override
    public int getItemCount() {
        return calllogsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,no,dura,logperson,date,time,type;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            logperson=itemView.findViewById(R.id.logperson);
            name=itemView.findViewById(R.id.div);
            no=itemView.findViewById(R.id.no);
            dura=itemView.findViewById(R.id.dura);
            date=itemView.findViewById(R.id.date);
            time=itemView.findViewById(R.id.time);
            type=itemView.findViewById(R.id.type);



        }
    }
}
