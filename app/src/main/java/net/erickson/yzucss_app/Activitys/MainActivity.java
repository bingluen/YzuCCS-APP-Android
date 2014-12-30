package net.erickson.yzucss_app.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.navdrawer.SimpleSideDrawer;

import net.erickson.yzucss_app.Fragments.MainFragment;
import net.erickson.yzucss_app.R;

public class MainActivity extends Activity{

    private LinearLayout SlidingMenuContent;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //準備側邊選單
        SimpleSideDrawer mSlidingMenu = new SimpleSideDrawer(this);
        mSlidingMenu.setLeftBehindContentView( R.layout.sliding_menu );

        SlidingMenuContent = (LinearLayout) this.findViewById(R.id.sliding_menu);
        loadList(SlidingMenuContent);

        //填充Fragment
        MainFragment mainFragment = new MainFragment(mSlidingMenu);
        getFragmentManager().beginTransaction().replace(R.id.main_layout, mainFragment).commit();


    }


    private void loadList(LinearLayout v)
    {
        Button button = new Button(this);
        button.setText(R.string.addTableButton);
        button.setBackground(null);
        button.setTextColor(getResources().getColor(R.color.menuButtonText));
        v.addView(button);
    }


}
