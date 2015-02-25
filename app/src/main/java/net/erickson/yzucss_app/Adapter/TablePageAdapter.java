package net.erickson.yzucss_app.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import net.erickson.yzucss_app.Fragments.UserTableDayFragment;
import net.erickson.yzucss_app.R;

/**
 * Created by Erickson on 2015/2/24.
 * Course-table day change adapter
 */
public class TablePageAdapter extends FragmentPagerAdapter {

    private Context context;

    public TablePageAdapter(FragmentManager fm, Context context)
    {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int i)
    {
        return new UserTableDayFragment();
    }

    @Override
    public int getCount()
    {
        return 6;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        String[] day = context.getResources().getStringArray(R.array.text_week_day);
        String title;
        try {
            title = day[position];
        } catch (ArrayIndexOutOfBoundsException e) {
            title = "error position";

        }
        return title;
    }
}
