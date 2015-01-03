package net.erickson.yzucss_app.DataObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erickson on 2015/1/3.
 */
public class UserTableObject {
    private long id;
    private CharSequence name;
    private List CourseList;
    private CharSequence comment;
    private List occupiedTime;

    public UserTableObject(UserTableObject userTableObject)
    {

    }

    public void setId(long id)
    {
        this.id = id;
    }

    public void setName(CharSequence name)
    {
        this.name = name;
    }

    public void setCourseList(List CourseList)
    {
        this.CourseList = new ArrayList<>(CourseList);
    }

    public void setComment(CharSequence comment)
    {
        this.comment = comment;
    }

    public void setOccupiedTime(List timeOccupied)
    {
        this.occupiedTime = new ArrayList<>(timeOccupied);
    }

    public long getId()
    {
        return id;
    }

    public CharSequence getName()
    {
        return name;
    }

    public List getCourseList()
    {
        return CourseList;
    }

    public CharSequence getComment()
    {
        return comment;
    }

    public List getTimeOccupied()
    {
        return occupiedTime;
    }
}
