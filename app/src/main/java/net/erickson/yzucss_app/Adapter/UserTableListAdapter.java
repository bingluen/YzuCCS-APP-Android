package net.erickson.yzucss_app.Adapter;

import android.content.Context;
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
    private Context context;

    public UserTableListAdapter(Context context, LayoutInflater inflater, List<UserTableListItem> Data)
    {
        data = Data;
        this.context = context;
        this.inflater = inflater;
    }

    public void deleteTable(long id)
    {
        for(int i = 0; i < data.size(); i++)
        {
            if(data.get(i).getId() == id)
            {
                data.remove(i);
            }
        }
        notifyDataSetChanged();
    }

    public void addTable(UserTableListItem item)
    {
        data.add(data.size()-1, item);
        notifyDataSetChanged();
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
        if(getItemId(position) == -1)
        {
            //holder.name.setCompoundDrawables();
            holder.name.setCompoundDrawablesWithIntrinsicBounds(context.getResources().getDrawable(R.drawable.ic_add_box_grey600_18dp), null, null, null);
        } else {
            holder.name.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }


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
