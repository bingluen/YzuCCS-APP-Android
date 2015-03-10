package net.erickson.yzucss_app.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.erickson.yzucss_app.Adapter.TablePageAdapter;
import net.erickson.yzucss_app.DataObjects.UserTableObject;
import net.erickson.yzucss_app.R;

/**
 * Created by Erickson on 2015/2/17.
 * View of user table
 */
public class UserTableViewFragment extends Fragment {
    private TablePageAdapter tablePageAdapter;
    private ViewPager mViewPager;
    private UserTableObject tableData;

    public void setTableData(UserTableObject tableObject)
    {
        tableData = tableObject;
    }


    @Override
    public void onCreate(Bundle savedInstancedState)
    {
        super.onCreate(savedInstancedState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.user_table_view_fragment, container, false);
        tablePageAdapter = new TablePageAdapter(getFragmentManager(), getActivity());
        mViewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        mViewPager.setAdapter(tablePageAdapter);
        return rootView;
    }
}
