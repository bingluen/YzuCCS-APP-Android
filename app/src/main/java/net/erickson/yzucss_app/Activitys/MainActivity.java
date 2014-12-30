package net.erickson.yzucss_app.Activitys;

import android.app.Activity;
import android.os.Bundle;

import net.erickson.yzucss_app.Fragments.MainFragment;
import net.erickson.yzucss_app.R;

public class MainActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainFragment mainFragment = new MainFragment();
        getFragmentManager().beginTransaction().replace(R.id.main_layout, mainFragment).commit();
    }


}
