package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by sansriti on 05-08-2018.
 */

public class SimplePageAdapter extends FragmentPagerAdapter{
    public SimplePageAdapter(FragmentManager fm){super(fm);}

    @Override
    public int getCount() {
        return 4 ;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:return new NumbersFragment();
            case 1:return new FamilyFragment();
            case 2:return new PhrasesFragment();
            case 3:return new ColorsFragment();
            default: return null;
        }

    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:return("Numbers");
            case 1:return ("Family");
            case 3:return ("Phrases");
            case 4:return ("Colors");
            default:return null;
        }
    }
}
