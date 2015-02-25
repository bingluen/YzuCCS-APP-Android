package net.erickson.yzucss_app.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.erickson.yzucss_app.R;

/**
 * Created by Erickson on 2015/2/24.
 * UserTableDayFragment
 */
public class UserTableDayFragment extends Fragment {

    public UserTableDayFragment newInstance(int sectionNumber)
    {
        UserTableDayFragment fragment = new UserTableDayFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.user_table_day_fragment, container, false);
        LinearLayout timeColumn = (LinearLayout) rootView.findViewById(R.id.time_column);
        String[] timeLabel = getResources().getStringArray(R.array.text_class_time);
        for(int i = 0; i < timeLabel.length; i++)
        {
            View frame = inflater.inflate(R.layout.user_table_time_field, timeColumn, false);
            TextView text = (TextView) frame.findViewById(R.id.timeField);
            text.setText(timeLabel[i]);
        }
        return rootView;
}
}
