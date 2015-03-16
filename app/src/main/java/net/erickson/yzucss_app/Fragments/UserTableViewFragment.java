package net.erickson.yzucss_app.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.erickson.yzucss_app.Adapter.TablePageAdapter;
import net.erickson.yzucss_app.DataObjects.CourseObject;
import net.erickson.yzucss_app.DataObjects.UserTableObject;
import net.erickson.yzucss_app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erickson on 2015/2/17.
 * View of user table
 */
public class UserTableViewFragment extends Fragment {
    private TablePageAdapter tablePageAdapter;
    private ViewPager mViewPager;
    //private UserTableObject tableData;
    private CourseObject[][] dailyCourse;

    public void setTableData(UserTableObject tableObject)
    {
        //tableData = tableObject;

        dailyCourse = new CourseObject[6][13];
        ArrayList<CourseObject> courseObject = (ArrayList<CourseObject>)tableObject.getCourseList();
        for(int i = 0; i < courseObject.size(); i++)
        {
            String[] times = courseObject.get(i).getTime().toString().split(", *");
            for(int j = 0; j < times.length; j++)
            {
                int day = Integer.getInteger(times[j]) / 100 - 1;
                int session = Integer.getInteger(times[j]) % 100 - 1;
                dailyCourse[day][session] = new CourseObject(courseObject.get(i));
            }
        }
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
        tablePageAdapter.setDailyCourse(dailyCourse);
        mViewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        mViewPager.setAdapter(tablePageAdapter);
        return rootView;
    }
}
