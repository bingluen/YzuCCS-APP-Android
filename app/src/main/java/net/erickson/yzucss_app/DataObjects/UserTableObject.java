package net.erickson.yzucss_app.DataObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erickson on 2015/1/3.
 */
public class UserTableObject extends UserTableListItem {

    private List CourseList;
    private List occupiedTime;

    public UserTableObject()
    {

    }

    public UserTableObject(UserTableObject userTableObject)
    {
        super((UserTableListItem) userTableObject);
        setCourseList(userTableObject.getCourseList());
        setOccupiedTime(userTableObject.getTimeOccupied());
    }
    public void setCourseList(List CourseList)
    {
        this.CourseList = new ArrayList<>(CourseList);
    }


    public void setOccupiedTime(List timeOccupied)
    {
        this.occupiedTime = new ArrayList<>(timeOccupied);
    }


    public List getCourseList()
    {
        return CourseList;
    }

    public List getTimeOccupied()
    {
        return occupiedTime;
    }
}
