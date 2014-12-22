package net.erickson.yzucss_app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import net.erickson.yzucss_app.R;

/**
 * Created by Erickson on 2014/12/22.
 */
public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        switch (sectionNumber)
        {
            case 1:
                args.putInt(ARG_SECTION_NUMBER, R.layout.course_table);
                break;
            case 2:
                args.putInt(ARG_SECTION_NUMBER, R.layout.course_search);
                break;
        }
        fragment.setArguments(args);
        return fragment;
    }

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Bundle args = getArguments();
        View rootView = inflater.inflate(args.getInt(ARG_SECTION_NUMBER), container, false);
        return rootView;
    }
}
