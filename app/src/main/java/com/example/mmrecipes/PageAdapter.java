package com.example.mmrecipes;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter {

    public int tabcount;

    public PageAdapter(@NonNull FragmentManager fm, int tabcount) {
        super(fm);
        this.tabcount = tabcount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0: BreakfastFragment breakfastFragment = new BreakfastFragment();
                return breakfastFragment;
            case 1: LunchFragment lunchFragment = new LunchFragment();
                return lunchFragment;
            case 2: DinnerFragment dinnerFragment = new DinnerFragment();
                return dinnerFragment;
            case 3: SnacksFragment snacksFragment = new SnacksFragment();
                return snacksFragment;
            case 4: DessertsFragment dessertsFragment = new DessertsFragment();
                return dessertsFragment;
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return tabcount;
    }
}