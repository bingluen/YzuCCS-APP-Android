package net.erickson.yzucss_app.Fragments;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.erickson.yzucss_app.R;

/**
 * Created by Erickson on 2015/1/12.
 */
public class UserTableFragment extends Fragment implements ActionBar.TabListener {
    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction)
    {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction)
    {
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction)
    {
    }


    public static class PlaceholderFragment extends Fragment
    {
        private static final String ARG_DAY_NUMBER = "day_number";

        public static PlaceholderFragment newInstance(int dayNumber)
        {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_DAY_NUMBER, dayNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment()
        {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {
            Bundle args = getArguments();
            View v = inflater.inflate(R.layout.user_table_column, container, false);
            return v;
        }
    }
}
