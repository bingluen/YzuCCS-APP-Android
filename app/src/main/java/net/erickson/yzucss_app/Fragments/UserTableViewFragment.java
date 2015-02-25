package net.erickson.yzucss_app.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import net.erickson.yzucss_app.Adapter.TablePageAdapter;

/**
 * Created by Erickson on 2015/2/17.
 * View of user table
 */
public class UserTableViewFragment extends Fragment {
    private TablePageAdapter tablePageAdapter;
    private ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstancedState)
    {
        super.onCreate(savedInstancedState);

        tablePageAdapter = new TablePageAdapter(getFragmentManager(), getActivity());

    }
}
