package net.erickson.yzucss_app.Services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.erickson.yzucss_app.DataObjects.CourseObject;
import net.erickson.yzucss_app.DataObjects.SelectedCourseObject;
import net.erickson.yzucss_app.DataObjects.UserTableListItem;
import net.erickson.yzucss_app.DataObjects.UserTableObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Erickson on 2015/1/3.
 */
public class AccessUserTableDatabase {
    // 表格名稱
    public static final String LIST_TABLE_NAME = "UserTableList";
    public static final String COURSE_TABLE_NAME = "UserCourseTable";
    public static final String OCCUPIED_TABLE_NAME = "UserOccupiedTable";

    // 編號表格欄位名稱，固定不變
    public static final String KEY_ID = "id";

    // LIST Column name
    public static final String LIST_NAME_COLUMN = "name";
    public static final String LIST_COMMENT_COLUMN = "comment";
    public static final String LIST_YEAR_COLUMN = "year";

    // Course Column name
    public static final String COURSE_LIST_ID_COLUMN = "list_id";
    public static final String COURSE_CODE_COLUMN = "code";

    // Occupied time Column name
    public static final String OCCUPIED_LIST_ID_COLUMN = "list_id";
    public static final String OCCUPIED_TIME_COLUMN = "time";

    // 使用上面宣告的變數建立表格的SQL指令
    public static final String CREATE_TABLE =
            "CREATE TABLE " + LIST_TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    LIST_NAME_COLUMN + " TEXT, " +
                    LIST_COMMENT_COLUMN+ " TEXT " +
                    LIST_YEAR_COLUMN + " TEXT, )" +
            "CREATE TABLE " + COURSE_TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COURSE_LIST_ID_COLUMN + " INTEGER, " +
                    COURSE_CODE_COLUMN + " TEXT)" +
            "CREATE TABLE " + OCCUPIED_TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    OCCUPIED_LIST_ID_COLUMN + " INTEGER, " +
                    OCCUPIED_TIME_COLUMN + " INTEGER)";

    // 資料庫物件
    private SQLiteDatabase db;

    // 建構子，一般的應用都不需要修改
    public AccessUserTableDatabase(Context context) {
        db = EJSQLHelper.getDatabase(context);
    }

    // 關閉資料庫，一般的應用都不需要修改
    public void close() {
        db.close();
    }

    public UserTableObject CreateUserTable(UserTableObject userTableObject)
    {
        ContentValues cv = new ContentValues();

        cv.put(LIST_NAME_COLUMN, userTableObject.getName().toString());
        cv.put(LIST_COMMENT_COLUMN, userTableObject.getComment().toString());

        long id = db.insert(LIST_TABLE_NAME, null, cv);

        userTableObject.setId(id);

        return userTableObject;
    }

    public boolean SelectCourse(SelectedCourseObject selectedCourseObject)
    {
        long id = InsertSelectedCourse(selectedCourseObject);
        for(int i = 0; i < selectedCourseObject.getCourseTime().size(); i++)
        {
            MarkOccupiedTime(Integer.valueOf(selectedCourseObject.getCourseTime().get(i).toString()));
        }

        return (id > 0);
    }

    private long InsertSelectedCourse(SelectedCourseObject selectedCourseObject)
    {
        ContentValues cv = new ContentValues();

        cv.put(COURSE_LIST_ID_COLUMN, selectedCourseObject.getListId());
        cv.put(COURSE_CODE_COLUMN, selectedCourseObject.getCourseCode().toString());

        return db.insert(COURSE_TABLE_NAME, null, cv);

    }

    private long MarkOccupiedTime(int occupiedTime)
    {
        ContentValues cv = new ContentValues();

        cv.put(OCCUPIED_TIME_COLUMN, occupiedTime);

        return db.insert(OCCUPIED_TABLE_NAME, null, cv);
    }

    // 刪除參數指定編號的資料
    public boolean dropCourse(SelectedCourseObject selectedCourseObject){
        // 設定條件為編號，格式為「欄位名稱=資料」
        String whereCourse = COURSE_LIST_ID_COLUMN + "=" + selectedCourseObject.getListId() + " AND " +
                COURSE_CODE_COLUMN + "=" + selectedCourseObject.getCourseCode();
        String whereOccupied = OCCUPIED_LIST_ID_COLUMN + "=" + selectedCourseObject.getListId() + " AND ( ";

        for(int i = 0; i < selectedCourseObject.getCourseTime().size(); i++)
        {
            whereOccupied += OCCUPIED_TIME_COLUMN + "=" + Integer.valueOf(selectedCourseObject.getCourseTime().get(i).toString());
            if(i < selectedCourseObject.getCourseTime().size() - 1)
                whereOccupied += " OR ";
        }
        whereCourse += ")";

        // 刪除指定編號資料並回傳刪除是否成功
        return (db.delete(COURSE_TABLE_NAME, whereCourse , null) > 0 && db.delete(OCCUPIED_TABLE_NAME, whereOccupied, null) > 0);
    }

    public boolean deleteList(long id)
    {
        String where = KEY_ID + "=" + id;
        String deleteCourseList = COURSE_LIST_ID_COLUMN + "=" + id;
        String deleteOccupied = OCCUPIED_LIST_ID_COLUMN + "=" + id;

        if(getUserCourseListCount(id) > 0 && !(db.delete(COURSE_TABLE_NAME, deleteCourseList, null) > 0))
            return false;

        if(getUserOccupiedListCount(id) > 0 && !(db.delete(OCCUPIED_TABLE_NAME, deleteOccupied, null) > 0))
            return false;

        if(db.delete(LIST_TABLE_NAME, where, null) > 0)
            return true;

        return false;

    }

    public List<UserTableListItem> getTableList()
    {
        List<UserTableListItem> result = new ArrayList<>();

        Cursor cursor = db.query(LIST_TABLE_NAME, null, null, null, null, null, null, null);

        while (cursor.moveToFirst())
        {
            result.add(getTableListRecord(cursor));
        }

        cursor.close();

        return result;
    }

    public UserTableListItem getTableListRecord(Cursor cursor)
    {
        UserTableListItem result = new UserTableListItem();

        result.setId(cursor.getLong(0));
        result.setName(cursor.getString(1));
        result.setComment(cursor.getString(2));
        result.setYear(cursor.getString(3));

        return result;
    }

    public  UserTableObject getTable(long id)
    {
        UserTableObject result = new UserTableObject();

        Cursor cursor = db.rawQuery("SELECT * FROM " + LIST_TABLE_NAME + " WHERE " + KEY_ID + "= ?", new String[]{Long.toString(id)});

        result.setId(id);
        result.setName(cursor.getString(1));
        result.setComment(cursor.getString(2));
        result.setYear(cursor.getString(3));

        //get course detail and add to result
        cursor = db.rawQuery(
                "SELECT * FROM "+ AccessCourseDatabase.TABLE_NAME +
                        " WHERE " + AccessCourseDatabase.CODE_COLUMN +
                        " IN " +
                        "(SELECT "+ COURSE_CODE_COLUMN + " FROM " + COURSE_TABLE_NAME + " WHERE " + COURSE_LIST_ID_COLUMN + "= ?)" +
                        " AND " + AccessCourseDatabase.YEAR_COLUMN + "= ?"
                , new String[] { Long.toString(id), result.getYear().toString() });

        result.setCourseList(new ArrayList<>());

        while (cursor.moveToFirst())
        {
            CourseObject course = new CourseObject();

            course.setId(cursor.getLong(0));
            course.setCode(cursor.getString(1));
            course.setName(cursor.getString(2));
            course.setYear(cursor.getString(3));
            course.setDegree(cursor.getString(4));
            course.setTeacher(cursor.getString(5));
            course.setTime(cursor.getString(6));

            result.getCourseList().add(course);
        }

        //get table occupied
        cursor = db.rawQuery(
                "SELECT "+ OCCUPIED_TIME_COLUMN + " FROM " + OCCUPIED_TABLE_NAME +
                        " WHERE " + OCCUPIED_LIST_ID_COLUMN + "= ?"
                , new String[] { Long.toString(id) });

        result.setOccupiedTime(new ArrayList<>());

        while (cursor.moveToFirst())
        {
            result.getTimeOccupied().add(cursor.getString(0));
        }

        return result;

    }

    // 取得List數量
    public int getUserTableListCount() {
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + LIST_TABLE_NAME, null);

        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }

        return result;
    }

    // 取得CourseList資料數量
    public int getUserCourseListCount() {
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + COURSE_TABLE_NAME, null);

        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }

        return result;
    }

    public int getUserCourseListCount(long listId) {
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + COURSE_TABLE_NAME + " WHERE " + COURSE_LIST_ID_COLUMN + "= ?", new String[]{ Long.toString(listId) });

        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }

        return result;
    }


    // 取得OccupiedList資料數量
    public int getUserOccupiedListCount() {
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + OCCUPIED_TABLE_NAME, null);

        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }

        return result;
    }

    public int getUserOccupiedListCount(long listId) {
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + OCCUPIED_TABLE_NAME + " WHERE " +  OCCUPIED_LIST_ID_COLUMN + "= ?", new String[]{ Long.toString(listId) });

        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }

        return result;
    }
}
