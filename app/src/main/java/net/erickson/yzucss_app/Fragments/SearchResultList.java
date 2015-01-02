package net.erickson.yzucss_app.Fragments;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import net.erickson.yzucss_app.DataObjects.CourseAdapter;
import net.erickson.yzucss_app.DataObjects.CourseObject;
import net.erickson.yzucss_app.R;

import java.util.List;

/**
 * Created by Erickson on 2015/1/1.
 */
public class SearchResultList extends ListFragment {

    private List<CourseObject> searchResult;
    private CourseAdapter courseAdapter;

    public SearchResultList(List result)
    {
        searchResult = result;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if(searchResult.size() > 0)
        {
            courseAdapter = new CourseAdapter(getActivity().getLayoutInflater(), searchResult);
            setListAdapter(courseAdapter);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View View = inflater.inflate(R.layout.list, container, false);
        return View;
    }

    @Override
    public void onListItemClick(ListView parent, View v, int position, long id) {


    }
}
