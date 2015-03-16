package net.erickson.yzucss_app.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import net.erickson.yzucss_app.DataObjects.CourseObject;
import net.erickson.yzucss_app.Fragments.UserTableDayFragment;
import net.erickson.yzucss_app.Fragments.UserTableViewFragment;
import net.erickson.yzucss_app.R;

/**
 * Created by Erickson on 2015/2/24.
 * Course-table day change adapter
 */
public class TablePageAdapter extends FragmentPagerAdapter {

    private Context context;
    private static final UserTableDayFragment userTableDayFragment = new UserTableDayFragment();
    private CourseObject[][] dailyCourse;

    public TablePageAdapter(FragmentManager fm, Context context)
    {
        super(fm);
        this.context = context;
    }

    public void setDailyCourse(CourseObject[][] dailyCourse)
    {
        this.dailyCourse = dailyCourse;
    }

    @Override
    public Fragment getItem(int i)
    {
        return userTableDayFragment.newInstance(i, dailyCourse[i]);
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
