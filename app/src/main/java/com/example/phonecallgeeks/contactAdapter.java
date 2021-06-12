package com.example.phonecallgeeks;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class contactAdapter extends RecyclerView.Adapter<contactAdapter.ViewHolder> implements Filterable {
    Activity activity;
    ArrayList<contacts> arrayList;
    ArrayList<contacts> listArray;
    public contactAdapter(Activity activity,ArrayList<contacts>arrayList){
        this.activity=activity;
        this.arrayList=arrayList;
        this.listArray=new ArrayList<>(arrayList);
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        contacts model=arrayList.get(position);
        if(model.getName().length()>=18)
        {
            holder.tvName.setText(model.getName().substring(0,18)+"...");
        }
        else
        {
            holder.tvName.setText(model.getName());
        }

        holder.tvNumber.setText(model.getPhone_no());
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent=new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+model.getPhone_no()));
                if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
                {
                    requestPermission();
                }
                else
                {
                    activity.startActivity(callIntent);

                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity,details.class);
                intent.putExtra("keyname",model.getName());
                intent.putExtra("keynumber",model.getPhone_no());
                activity.startActivity(intent);
            }
        });
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.CALL_PHONE},1);
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public Filter getFilter() {

        return filter;
    }
   Filter filter=new Filter() {
       @Override
       protected FilterResults performFiltering(CharSequence constraint) {
           ArrayList<contacts>filterList=new ArrayList<>();
           if(constraint==null||constraint.length()==0) {
               filterList.addAll(listArray);
           }
           else{
               String filterPattern=constraint.toString().toLowerCase().trim();
               for(contacts contacts:listArray)
               {
                  if (contacts.getName().toLowerCase().contains(filterPattern)||contacts.getPhone_no().toLowerCase().contains(filterPattern) ) {
                      filterList.add(contacts);
                  }

               }

           }
           FilterResults results=new FilterResults();
           results.values=filterList;
           return results;
       }

       @Override
       protected void publishResults(CharSequence constraint, FilterResults results) {
           arrayList.clear();
           arrayList.addAll((ArrayList)results.values);
           notifyDataSetChanged();

       }
   };

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvNumber,call;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.name);
            tvNumber=itemView.findViewById(R.id.number);
            call=itemView.findViewById(R.id.call);

        }
    }
}

