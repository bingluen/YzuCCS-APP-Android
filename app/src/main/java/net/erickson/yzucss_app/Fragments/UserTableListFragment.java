package net.erickson.yzucss_app.Fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import net.erickson.yzucss_app.Adapter.UserTableListAdapter;
import net.erickson.yzucss_app.DataObjects.UserTableListItem;
import net.erickson.yzucss_app.R;

import java.util.List;

/**
 * Created by Erickson on 2015/1/6.
 */
public class UserTableListFragment extends ListFragment {

    private List<UserTableListItem> userTableListItems;
    private UserTableListAdapter userTableListAdapter;

    public UserTableListFragment()
    {
        super();
    }

    public UserTableListFragment(List userTableListItems)
    {
        super();
        this.userTableListItems = userTableListItems;
    }

    public void setUserTableListItems(List userTableListItems)
    {
        this.userTableListItems = userTableListItems;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if(userTableListItems != null && userTableListItems.size() > 0)
        {
            userTableListAdapter = new UserTableListAdapter(getActivity().getLayoutInflater(), userTableListItems);
            setListAdapter(userTableListAdapter);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.list, container, false);
    }

    @Override
    public void onListItemClick(ListView parent, View v, int position, long id) {


    }
}
