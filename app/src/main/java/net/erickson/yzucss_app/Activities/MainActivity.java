package net.erickson.yzucss_app.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.navdrawer.SimpleSideDrawer;

import net.erickson.yzucss_app.DataObjects.UserTableListItem;
import net.erickson.yzucss_app.Fragments.AddUserTableFragment;
import net.erickson.yzucss_app.Fragments.MainFragment;
import net.erickson.yzucss_app.Fragments.UserTableListFragment;
import net.erickson.yzucss_app.R;
import net.erickson.yzucss_app.Services.AccessUserTableDatabase;

import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {


    private LinearLayout SlidingMenuContent;
    private Button button;
    private UserTableListFragment userTableListFragment;
    private AddUserTableFragment addUserTableFragment;
    private SimpleSideDrawer mSlidingMenu;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //準備側邊選單
        mSlidingMenu = new SimpleSideDrawer(this);
        mSlidingMenu.setLeftBehindContentView( R.layout.sliding_menu );

        SlidingMenuContent = (LinearLayout) this.findViewById(R.id.sliding_menu);
        loadList(SlidingMenuContent);

        //填充Fragment
        MainFragment mainFragment = new MainFragment(mSlidingMenu);
        /*
        * 為了支援4.X的裝置要改用這個方式來取得FragmentManager(配合FragmentActivity)
        * 若純5.0app則可直接使用getFragmentManager + Activity
        */
        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, mainFragment).commit();

    }

    private void loadList(LinearLayout v)
    {

        /*
        *　這裡應該要用 Adapter 做(ヽ´ω`)....
        * 要先寫專用的 Adapter 再來填List ^_< (e04...
        */
        //get table list
        AccessUserTableDatabase tableListHelper = new AccessUserTableDatabase(this);
        if(tableListHelper.getUserTableCount() > 0)
        {
            List<UserTableListItem> tableList = tableListHelper.getTableList();
            userTableListFragment = new UserTableListFragment();
            userTableListFragment.setUserTableListItems(tableList);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_myCourseTableListItems, userTableListFragment).commit();
        }

        //"new table" button
        button = (Button) findViewById(R.id.addTable);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.addTable:
                addUserTableFragment = new AddUserTableFragment();
                getSupportFragmentManager().beginTransaction().addToBackStack(null);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_Content, addUserTableFragment);
                getSupportFragmentManager().beginTransaction().commit();
                mSlidingMenu.toggleDrawer();
                break;
        }
    }


}
