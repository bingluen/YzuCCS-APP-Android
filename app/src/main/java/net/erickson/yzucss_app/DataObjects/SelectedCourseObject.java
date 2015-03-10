package net.erickson.yzucss_app.DataObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erickson on 2015/1/3.
 */
public class SelectedCourseObject {
    private long listId;
    private CharSequence courseCode;
    private CharSequence courseYear;
    private ArrayList courseTime;

    public SelectedCourseObject(long listid, CourseObject course) {
        listId = listid;
        courseYear = course.getYear();
        courseCode = course.getCode();
        courseTime = new ArrayList();
        String[] times = course.getTime().toString().split(", *");
        for(int i = 0; i < times.length; i++)
        {
            courseTime.add(Integer.valueOf(times[i]));
        }

    }

    public SelectedCourseObject(SelectedCourseObject selectedCourseObject) {
        listId = selectedCourseObject.getListId();
        courseYear = selectedCourseObject.getCourseYear();
        courseCode = selectedCourseObject.getCourseCode();
        courseTime = new ArrayList<>(selectedCourseObject.getCourseTime());
    }

    public long getListId()
    {
        return listId;
    }

    public CharSequence getCourseCode()
    {
        return courseCode;
    }

    public List getCourseTime()
    {
        return courseTime;
    }

    public CharSequence getCourseYear()
    {
        return courseYear;
    }
}
