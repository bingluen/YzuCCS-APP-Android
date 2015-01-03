package net.erickson.yzucss_app.DataObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erickson on 2015/1/3.
 */
public class SelectedCourseObject {
    private long listId;
    private CharSequence courseCode;
    private List courseTime;

    public SelectedCourseObject(long listid, CourseObject course) {
        listId = listid;
        courseCode = course.getCode();
        String[] times = course.getTime().toString().split(",");
        for(int i = 0; i < times.length; i++)
        {
            courseTime.add(Integer.valueOf(times[i]));
        }
    }

    public SelectedCourseObject(SelectedCourseObject selectedCourseObject) {
        listId = selectedCourseObject.getListId();
        courseCode = selectedCourseObject.getCourseCode();
        courseTime = new ArrayList<>(selectedCourseObject.getCourseTime());
    }

    public long getListId()
    {
        return listId;
    }

    public String getCourseCode()
    {
        return courseCode.toString();
    }

    public List getCourseTime()
    {
        return courseTime;
    }
}
