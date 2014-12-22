package net.erickson.yzucss_app.DataObjects;

import java.util.ArrayList;

/**
 * Created by Erickson on 2014/12/22.
 */
public class CourseList {
    private ArrayList<CourseObject> courseList;
    public void setCourseList(ArrayList<CourseObject> List)
    {
        courseList = List;
    }

    public void addCourseList(CourseObject CourseObject)
    {
        courseList.add(CourseObject);
    }

    public ArrayList<CourseObject> getCourseList()
    {
        return courseList;
    }
}
