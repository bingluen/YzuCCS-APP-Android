package net.erickson.yzucss_app.doSomething;

import android.content.Context;

import net.erickson.yzucss_app.DataObjects.CourseObject;
import net.erickson.yzucss_app.Services.AccessDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erickson on 2014/12/22.
 */
public class doSearchCourse {
    private AccessDatabase DBHelper;
    public doSearchCourse(Context context)
    {
        DBHelper = new AccessDatabase(context);
    }
    public List<CourseObject> search(String keyword)
    {
        List<CourseObject> result = new ArrayList<>();
        result.addAll(DBHelper.getbyFullTextSearch(keyword));
        return result;
    }
}