package net.erickson.yzucss_app.Activitys;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;

import com.navdrawer.SimpleSideDrawer;

import net.erickson.yzucss_app.DataObjects.UserTableListItem;
import net.erickson.yzucss_app.Fragments.MainFragment;
import net.erickson.yzucss_app.R;
import net.erickson.yzucss_app.Services.AccessUserTableDatabase;

import java.util.List;

public class MainActivity extends FragmentActivity {


    private LinearLayout SlidingMenuContent;
    private Button button;

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
        List<UserTableListItem> tableList = tableListHelper.getTableList();

        //"new table" button
        button = new Button(this);
        button.setText(R.string.addTableButton);
        button.setBackground(null);
        button.setTextColor(getResources().getColor(R.color.menuButtonText));
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.myCourseTableListTextSize));
        button.setGravity(Gravity.LEFT);
        button.setGravity(Gravity.CENTER_VERTICAL);
        button.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_add_box_grey600_18dp, 0, 0, 0);
        v.addView(button);
    }


}
