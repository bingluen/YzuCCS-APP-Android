package net.erickson.yzucss_app.Services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.erickson.yzucss_app.DataObjects.CourseObject;
import net.erickson.yzucss_app.DataObjects.SelectedCourseObject;
import net.erickson.yzucss_app.DataObjects.UserTableObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erickson on 2015/1/3.
 */
public class AccessUserTableDatabase {
    // 表格名稱
    public static final String LIST_TABLE_NAME = "UserTableList";
    public static final String COURSE_TABLE_NAME = "UserCourseTable";
    public static final String OCCUPIED_TABLE_NAME = "UserSelectedTable";

    // 編號表格欄位名稱，固定不變
    public static final String KEY_ID = "id";

    // LIST Column name
    public static final String LIST_NAME_COLUMN = "name";
    public static final String LIST_COMMENT_COLUMN = "comment";

    // Course Column name
    public static final String COURSE_NAME_COLUMN = "name";
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
                    LIST_COMMENT_COLUMN+ " TEXT)" +
            "CREATE TABLE " + COURSE_TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COURSE_LIST_ID_COLUMN + " INTEGER, " +
                    COURSE_NAME_COLUMN + " TEXT, " +
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
        cv.put(COURSE_CODE_COLUMN, selectedCourseObject.getCourseCode());

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

    public boolean delete(long id)
    {
        String where = KEY_ID + "=" + id;
        String deleteCourseList = COURSE_LIST_ID_COLUMN + "=" + id;
        String deleteOccupied = OCCUPIED_LIST_ID_COLUMN + "=" + id;

        db.delete(COURSE_TABLE_NAME, deleteCourseList, null);
        db.delete(OCCUPIED_TABLE_NAME, deleteOccupied, null);
        return true;

    }

    // 讀取所有記事資料
    public List<UserTableObject> getAll() {
        List<UserTableObject> result = new ArrayList<>();
        Cursor cursor = db.query(
                LIST_TABLE_NAME, null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }

        cursor.close();
        return result;
    }

    // 取得指定編號的資料物件
    public UserTableObject get(long id) {
        // 準備回傳結果用的物件
        UserTableObject item = null;
        // 使用編號為查詢條件
        String where = KEY_ID + "=" + id;
        // 執行查詢
        Cursor result = db.query(
                , null, where, null, null, null, null, null);

        // 如果有查詢結果
        if (result.moveToFirst()) {
            // 讀取包裝一筆資料的物件
            item = getRecord(result);
        }

        // 關閉Cursor物件
        result.close();
        // 回傳結果
        return item;
    }

    // 把Cursor目前的資料包裝為物件
    public CourseObject getRecord(Cursor cursor) {
        // 準備回傳結果用的物件
        CourseObject result = new CourseObject();

        result.setId(cursor.getLong(0));
        result.setCode(cursor.getString(1));
        result.setName(cursor.getString(2));
        result.setYear(cursor.getString(3));
        result.setDegree(cursor.getString(4));
        result.setTeacher(cursor.getString(5));
        result.setTime(cursor.getString(6));

        // 回傳結果
        return result;
    }

    // 取得資料數量
    public int getCount() {
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);

        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }

        return result;
    }
}
