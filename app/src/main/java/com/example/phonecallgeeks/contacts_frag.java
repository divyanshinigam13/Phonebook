package com.example.phonecallgeeks;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class contacts_frag extends Fragment {
View view;
   RecyclerView recyclerView;
   contactAdapter contactAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       view=inflater.inflate(R.layout.fragment_contacts, container, false);
       recyclerView=view.findViewById(R.id.recycle);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager=linearLayoutManager;
        recyclerView.setLayoutManager(layoutManager);
        contactAdapter=new contactAdapter((Activity) getContext(),getContacts());
        recyclerView.setAdapter(contactAdapter);
        FloatingActionButton button=(FloatingActionButton) view.findViewById(R.id.frag);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), CreateNewContact.class);
                startActivity(intent);
            }
        });
        return view;
    }
    private ArrayList<contacts>getContacts(){
        ArrayList<contacts>arrayList=new ArrayList<>();
        Uri uri= ContactsContract.Contacts.CONTENT_URI;
        String sort=ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC";
        Cursor cursor=getContext().getContentResolver().query(uri,null,null,null,sort);

        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                String id=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                Uri uriPhone= ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                String selection=ContactsContract.CommonDataKinds.Phone.CONTACT_ID+" =?";
                Cursor phoneCursor=getContext().getContentResolver().query(uriPhone,null,selection,new String[]{id},null);
                if(phoneCursor.moveToNext()){
                    String number=phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contacts model=new contacts();
                    model.setName(name);
                    model.setPhone_no(number);
                    arrayList.add(model);
                    phoneCursor.close();
                }}
            cursor.close();

        }return arrayList;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_list,menu);
        MenuItem menuItem=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView)menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                contactAdapter.getFilter().filter(newText);
                return false;

            }
        });
    }
}