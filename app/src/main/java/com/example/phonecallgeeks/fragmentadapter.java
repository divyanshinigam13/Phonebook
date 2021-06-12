package com.example.phonecallgeeks;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class fragmentadapter extends FragmentPagerAdapter {
    ArrayList<Fragment>fragmentArrayList=new ArrayList<>();
    ArrayList<String>StringList=new ArrayList<>();

    public fragmentadapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public fragmentadapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }
    public void addFragment(Fragment fragment,String s)
    {
       fragmentArrayList.add(fragment) ;
       StringList.add(s);

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return StringList.get(position);
    }
}

