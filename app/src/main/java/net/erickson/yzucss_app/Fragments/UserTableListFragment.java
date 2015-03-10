package net.erickson.yzucss_app.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.navdrawer.SimpleSideDrawer;

import net.erickson.yzucss_app.Adapter.UserTableListAdapter;
import net.erickson.yzucss_app.DataObjects.UserTableListItem;
import net.erickson.yzucss_app.Dialog.AddUserTableDialog;
import net.erickson.yzucss_app.Dialog.DeleteUserTableDialog;
import net.erickson.yzucss_app.R;
import net.erickson.yzucss_app.Services.AccessUserTableDatabase;

import java.util.List;

/**
 * Created by Erickson on 2015/1/6.
 */
public class UserTableListFragment extends ListFragment {

    private List<UserTableListItem> userTableListItems;
    private UserTableListAdapter userTableListAdapter;
    private UserTableViewFragment userTableViewFragment;
    AccessUserTableDatabase UTDHelper;
    SimpleSideDrawer mSlidingMenu;
    FragmentTransaction transaction;

    public UserTableListFragment()
    {
        super();
    }

    public void setSlidingMenu(SimpleSideDrawer slidingMenu)
    {
        mSlidingMenu = slidingMenu;
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
            userTableListAdapter = new UserTableListAdapter(getActivity(), getActivity().getLayoutInflater(), userTableListItems);
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

        if(userTableListItems.get(position).getId() == -1)
        {
            AddUserTableDialog addUserTableDialog = new AddUserTableDialog();
            addUserTableDialog.setUserTableListAdapter(userTableListAdapter);
            addUserTableDialog.show(getActivity().getFragmentManager(),
                    (String) getResources().getText(R.string.addCourseTable_dialog_title));

        } else {
            if(mSlidingMenu != null)
            {
                mSlidingMenu.toggleDrawer();
            }
            UTDHelper = new AccessUserTableDatabase(getActivity());
            userTableViewFragment = new UserTableViewFragment();
            userTableViewFragment.setTableData(UTDHelper.getTable(userTableListItems.get(position).getId()));
            transaction = getFragmentManager().beginTransaction();
            transaction.addToBackStack("before_view_table");
            transaction.replace(R.id.fragment_Content, userTableViewFragment).commit();
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        AdapterView.OnItemLongClickListener listener = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle args = new Bundle();
                args.putLong("deleteListId", userTableListItems.get(position).getId());
                args.putCharSequence("deleteListName", userTableListItems.get(position).getName());
                DeleteUserTableDialog deleteUserTableDialog = new DeleteUserTableDialog();
                deleteUserTableDialog.setUserTableListAdapter(userTableListAdapter);
                deleteUserTableDialog.setArguments(args);
                deleteUserTableDialog.show(getActivity().getFragmentManager(), "deleteCourseDialog");
                return false;
            }
        };

        getListView().setOnItemLongClickListener(listener);
    }
}
