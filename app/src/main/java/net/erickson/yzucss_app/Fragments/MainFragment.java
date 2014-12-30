package net.erickson.yzucss_app.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.navdrawer.SimpleSideDrawer;

import net.erickson.yzucss_app.R;

public class MainFragment extends Fragment implements View.OnClickListener {
    private SimpleSideDrawer mSlidingMenu;

    private ImageButton slidingButton;



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


}
