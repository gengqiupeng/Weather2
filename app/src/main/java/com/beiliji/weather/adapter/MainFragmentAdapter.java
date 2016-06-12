package com.beiliji.weather.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

public class MainFragmentAdapter extends FragmentPagerAdapter
{
  public List<Fragment> lists;

    public MainFragmentAdapter(FragmentManager fm,List<Fragment> lists) {
        super(fm);
        this.lists=lists;
    }


    public int getCount()
  {
    return lists.size();
  }

  public Fragment getItem(int paramInt)
  {
    return lists.get(paramInt);
  }
}