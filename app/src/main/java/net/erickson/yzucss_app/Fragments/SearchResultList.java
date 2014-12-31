package net.erickson.yzucss_app.Fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import net.erickson.yzucss_app.R;

import java.util.List;

/**
 * Created by Erickson on 2015/1/1.
 */
public class SearchResultList extends ListFragment {

    private List searchResult;

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

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View View = inflater.inflate(R.layout.search_result_list, container, false);
        return View;
    }

    @Override
    public void onListItemClick(ListView parent, View v, int position, long id) {


    }
}
