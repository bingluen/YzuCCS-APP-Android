package net.erickson.yzucss_app.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import net.erickson.yzucss_app.Adapter.UserTableListAdapter;
import net.erickson.yzucss_app.DataObjects.UserTableListItem;
import net.erickson.yzucss_app.R;
import net.erickson.yzucss_app.Services.AccessUserTableDatabase;

import java.util.List;

/**
 * Created by Erickson on 2015/2/3.
 */
public class AddCourseToUserTable extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get table list
        AccessUserTableDatabase tableListHelper = new AccessUserTableDatabase(this);
        List<UserTableListItem> tableList = tableListHelper.getTableList();
        setContentView(R.layout.add_course_to_user_table);
        UserTableListAdapter userTableListAdapter = new UserTableListAdapter(this.getLayoutInflater(), tableList);

    }
}
