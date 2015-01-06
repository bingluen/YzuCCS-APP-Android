package net.erickson.yzucss_app.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.erickson.yzucss_app.DataObjects.UserTableListItem;
import net.erickson.yzucss_app.R;

import java.util.List;

/**
 * Created by Erickson on 2015/1/6.
 */
public class UserTableListAdapter extends BaseAdapter {
    private List<UserTableListItem> data;
    private LayoutInflater inflater;
    private ViewHolder holder;

    public UserTableListAdapter(LayoutInflater inflater, List<UserTableListItem> Data)
    {
        data = Data;
        this.inflater = inflater;
    }

    @Override
    public int getCount()
    {
        return data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.my_course_table_list_items, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.myCourseTableList_row_view_table_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(getItem(position).getName());


        return convertView;
    }

    @Override
    public UserTableListItem getItem(int position)
    {
        return data.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return data.get(position).getId();
    }

    static class ViewHolder
    {
        TextView name;

    }
}
