package net.erickson.yzucss_app.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.erickson.yzucss_app.R;

import org.w3c.dom.Text;

/**
 * Created by Erickson on 2015/2/24.
 * UserTableDayFragment
 */
public class UserTableDayFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public UserTableDayFragment newInstance(int sectionNumber)
    {
        UserTableDayFragment fragment = new UserTableDayFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.user_table_day_fragment, container, false);
        LinearLayout timeColumn = (LinearLayout) rootView.findViewById(R.id.time_column);
        String[] timeLabel = getResources().getStringArray(R.array.text_class_time);
        String[] dayLabel = getResources().getStringArray(R.array.text_week_day);
        TextView day = (TextView) rootView.findViewById(R.id.section_label);
        day.setText(dayLabel[getArguments().getInt(ARG_SECTION_NUMBER, 0)]);
        for(int i = 0; i < timeLabel.length; i++)
        {
            View frame = inflater.inflate(R.layout.user_table_time_field, timeColumn, false);
            TextView text = (TextView) frame.findViewById(R.id.timeField);
            TextView session = (TextView) frame.findViewById(R.id.sessionTime);
            text.setText(timeLabel[i]);
            session.setText(Integer.toString(i+1));
            if(i%2 == 1)
            {
                frame.setBackgroundColor(getResources().getColor(R.color.mainBackground));
            } else {
                text.setTextColor(getResources().getColor(R.color.mainBackground));
                session.setTextColor(getResources().getColor(R.color.mainBackground));
            }
            timeColumn.addView(frame);
        }
        return rootView;
    }
}
