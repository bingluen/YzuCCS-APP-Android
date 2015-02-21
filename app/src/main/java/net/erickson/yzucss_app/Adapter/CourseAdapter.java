package net.erickson.yzucss_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.erickson.yzucss_app.DataObjects.CourseObject;
import net.erickson.yzucss_app.R;

import java.util.List;

/**
 * Created by Erickson on 2015/1/2.
 */
public class CourseAdapter extends BaseAdapter {

    private List<CourseObject> data;
    private LayoutInflater inflater;
    private ViewHolder holder;
    private Context context;

    public CourseAdapter(Context context, LayoutInflater inflater, List<CourseObject> Data)
    {
        data = Data;
        this.inflater = inflater;
        this.context = context;
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
            convertView = inflater.inflate(R.layout.search_result_row, null);
            holder = new ViewHolder();
            holder.code = (TextView) convertView.findViewById(R.id.search_result_row_view_course_code);
            holder.degree = (TextView) convertView.findViewById(R.id.search_result_row_view_course_degree);
            holder.name = (TextView) convertView.findViewById(R.id.search_result_row_view_course_name);
            holder.professor = (TextView) convertView.findViewById(R.id.search_result_row_view_course_professor);
            holder.time = (TextView) convertView.findViewById(R.id.search_result_row_view_course_time);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.code.setText(getItem(position).getCode());
        holder.degree.setText(context.getResources().getText(R.string.searchResultColumn_degree).toString() + getItem(position).getDegree());
        holder.name.setText(getItem(position).getName());
        holder.professor.setText(context.getResources().getText(R.string.searchResultColumn_teacher).toString() + getItem(position).getTeacher());
        holder.time.setText(context.getResources().getText(R.string.searchResultColumn_time).toString() + getItem(position).getTime());


        return convertView;
    }

    @Override
    public CourseObject getItem(int position)
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
        TextView code;
        TextView name;
        TextView professor;
        TextView time;
        TextView degree;
    }
}
