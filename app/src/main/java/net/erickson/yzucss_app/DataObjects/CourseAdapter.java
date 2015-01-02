package net.erickson.yzucss_app.DataObjects;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.erickson.yzucss_app.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Erickson on 2015/1/2.
 */
public class CourseAdapter extends BaseAdapter {

    private List<CourseObject> data;
    private LayoutInflater inflater;
    private ViewHolder holder;

    public CourseAdapter(LayoutInflater inflater, List<CourseObject> Data)
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
        holder.degree.setText(getItem(position).getDegree());
        holder.name.setText(getItem(position).getName());
        holder.professor.setText(getItem(position).getTeacher());
        holder.time.setText(getItem(position).getTime());


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
