package net.erickson.yzucss_app.doSomething;

import android.content.Context;

import net.erickson.yzucss_app.DataObjects.CourseObject;
import net.erickson.yzucss_app.DataObjects.SelectedCourseObject;
import net.erickson.yzucss_app.Services.AccessCourseDatabase;
import net.erickson.yzucss_app.Services.AccessUserTableDatabase;

import java.util.List;

/**
 * Created by Erickson on 2015/2/16.
 */
public class doAddCourseToUserTable {
    private List listId;
    private long courseId;
    private AccessUserTableDatabase tableHelper;
    private AccessCourseDatabase courseHelper;

    public doAddCourseToUserTable(Context context)
    {
        tableHelper = new AccessUserTableDatabase(context);
        courseHelper = new AccessCourseDatabase(context);
    }

    public void setListId(List listIDs) {
        listId = listIDs;
    }

    public void setCourse(long courseId)
    {
        this.courseId = courseId;
    }

    public boolean doing()
    {
        CourseObject course = courseHelper.get(courseId);
        for(int i = 0; i < listId.size(); i++)
        {
            SelectedCourseObject selectedCourseObject = new SelectedCourseObject((long)listId.get(i), course);
            if(!tableHelper.SelectCourse(selectedCourseObject))
                return false;
        }
        return true;
    }
}
