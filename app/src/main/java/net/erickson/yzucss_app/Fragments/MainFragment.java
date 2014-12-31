package net.erickson.yzucss_app.Fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.navdrawer.SimpleSideDrawer;

import net.erickson.yzucss_app.DataObjects.CourseObject;
import net.erickson.yzucss_app.R;
import net.erickson.yzucss_app.doSomething.doSearchCourse;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements View.OnClickListener, TextWatcher {
    private SimpleSideDrawer mSlidingMenu;

    private ImageButton slidingButton;
    private EditText searchKeyWord;
    FragmentTransaction transaction;

    public MainFragment()
    {

    }

    public MainFragment(SimpleSideDrawer SlidingMenu)
    {
        mSlidingMenu = SlidingMenu;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        slidingButton = (ImageButton) rootView.findViewById(R.id.sliding_menu_button);
        slidingButton.setOnClickListener(this);

        searchKeyWord = (EditText) rootView.findViewById(R.id.search_key_word);
        searchKeyWord.addTextChangedListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.sliding_menu_button:
                mSlidingMenu.toggleDrawer();
                break;
            default:
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(count > 0)
        {
            List<CourseObject> result = new ArrayList<>();
            doSearchCourse searchHelper = new doSearchCourse(getActivity());
            result = searchHelper.search(s.toString());

            SearchResultList displayResult = new SearchResultList(result);
            transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_Content, displayResult).commit();

        }

    }

    @Override
    public void afterTextChanged(Editable s) {

    }


}
