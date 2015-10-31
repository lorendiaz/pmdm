package com.fpmislata.practicas.aplicacion28;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class MiFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 6;
    private String tabTitles[] =
            new String[] { "Tab Uno", "Tab Dos", "Tab Tres", "Tab Cuatro", "Tab Cinco", "Tab Seis"};

    public MiFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment f = null;

        switch(position) {
            case 0:
            case 2:
            case 4:
                f = new Fragment1();
                break;
            case 1:
            case 3:
            case 5:
                f = new Fragment2();
                break;
        }

        return f;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}