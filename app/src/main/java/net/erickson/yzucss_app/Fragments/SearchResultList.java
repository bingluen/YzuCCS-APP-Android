package net.erickson.yzucss_app.Fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import net.erickson.yzucss_app.Activities.MainActivity;
import net.erickson.yzucss_app.Adapter.CourseAdapter;
import net.erickson.yzucss_app.DataObjects.CourseObject;
import net.erickson.yzucss_app.DataObjects.UserTableListItem;
import net.erickson.yzucss_app.R;
import net.erickson.yzucss_app.Services.AccessUserTableDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erickson on 2015/1/1.
 */
public class SearchResultList extends ListFragment {

    private List<CourseObject> searchResult;
    private CourseAdapter courseAdapter;

    public void setResult(List<CourseObject> result)
    {
        searchResult = result;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if(searchResult != null && searchResult.size() > 0)
        {
            setListAdapter(null);
            courseAdapter = new CourseAdapter(getActivity().getLayoutInflater(), searchResult);
            setListAdapter(courseAdapter);
        } else {
            //清空
            setListAdapter(null);
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
        Bundle args = new Bundle();
        args.putLong("courseId", id);
        AddCourseToUserTable dialog = new AddCourseToUserTable();
        dialog.show(getActivity().getFragmentManager(), (String) getResources().getText(R.string.addCourse_Title));
    }

}
