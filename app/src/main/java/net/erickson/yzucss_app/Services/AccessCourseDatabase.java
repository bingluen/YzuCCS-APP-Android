package net.erickson.yzucss_app.Services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.erickson.yzucss_app.DataObjects.CourseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erickson on 2014/12/22.
 */
public class AccessCourseDatabase {
    // 表格名稱
    public static final String TABLE_NAME = "CourseDatabase";

    // 編號表格欄位名稱，固定不變
    public static final String KEY_ID = "id";

    // 其它表格欄位名稱
    public static final String CODE_COLUMN = "code";
    public static final String NAME_COLUMN = "cname";
    public static final String YEAR_COLUMN = "year";
    public static final String DEGREE_COLUMN = "degree";
    public static final String TEACHER_COLUMN = "teacher";
    public static final String TIME_COLUMN = "time";

    // 使用上面宣告的變數建立表格的SQL指令
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CODE_COLUMN + " TEXT, " +
                    NAME_COLUMN + " TEXT, " +
                    YEAR_COLUMN + " TEXT, " +
                    DEGREE_COLUMN + " TEXT, " +
                    TEACHER_COLUMN + " TEXT, " +
                    TIME_COLUMN + " TEXT)";

    // 資料庫物件
    private SQLiteDatabase db;

    // 建構子，一般的應用都不需要修改
    public AccessCourseDatabase(Context context) {
        db = EJSQLHelper.getDatabase(context);
    }

    // 關閉資料庫，一般的應用都不需要修改
    public void close() {
        db.close();
    }

    // 新增參數指定的物件
    public CourseObject insert(CourseObject item) {
        // 建立準備新增資料的ContentValues物件
        ContentValues cv = new ContentValues();

        // 加入ContentValues物件包裝的新增資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(CODE_COLUMN, item.getCode().toString());
        cv.put(NAME_COLUMN, item.getName().toString());
        cv.put(YEAR_COLUMN, item.getYear().toString());
        cv.put(DEGREE_COLUMN, item.getDegree().toString());
        cv.put(TEACHER_COLUMN, item.getTeacher().toString());
        cv.put(TIME_COLUMN, item.getTime().toString());

        // 新增一筆資料並取得編號
        // 第一個參數是表格名稱
        // 第二個參數是沒有指定欄位值的預設值
        // 第三個參數是包裝新增資料的ContentValues物件
        long id = db.insert(TABLE_NAME, null, cv);

        // 設定編號
        item.setId(id);
        // 回傳結果
        return item;
    }

    // 修改參數指定的物件
    public boolean update(CourseObject item) {
        // 建立準備修改資料的ContentValues物件
        ContentValues cv = new ContentValues();

        // 加入ContentValues物件包裝的修改資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(CODE_COLUMN, item.getCode().toString());
        cv.put(NAME_COLUMN, item.getName().toString());
        cv.put(YEAR_COLUMN, item.getYear().toString());
        cv.put(DEGREE_COLUMN, item.getDegree().toString());
        cv.put(TEACHER_COLUMN, item.getTeacher().toString());
        cv.put(TIME_COLUMN, item.getTime().toString());

        // 設定修改資料的條件為編號
        // 格式為「欄位名稱＝資料」
        String where = KEY_ID + "=" + item.getId();

        // 執行修改資料並回傳修改的資料數量是否成功
        return db.update(TABLE_NAME, cv, where, null) > 0;
    }

    // 刪除參數指定編號的資料
    public boolean delete(long id){
        // 設定條件為編號，格式為「欄位名稱=資料」
        String where = KEY_ID + "=" + id;
        // 刪除指定編號資料並回傳刪除是否成功
        return db.delete(TABLE_NAME, where , null) > 0;
    }

    // 讀取所有記事資料
    public List<CourseObject> getAll() {
        List<CourseObject> result = new ArrayList<>();
        Cursor cursor = db.query(
                TABLE_NAME, null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }

        cursor.close();
        return result;
    }

    // 取得指定編號的資料物件
    public CourseObject get(long id) {
        // 準備回傳結果用的物件
        CourseObject item = null;
        // 使用編號為查詢條件
        String where = KEY_ID + "=" + id;
        // 執行查詢
        Cursor result = db.query(
                TABLE_NAME, null, where, null, null, null, null, null);

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

    //byTeacher
    public List<CourseObject> getByTeacher(String key, String year) {
        List<CourseObject> result = new ArrayList<>();
        Cursor cursor = db.query(
                TABLE_NAME, null, "teacher LIKE ? AND year = ?", new String[]{"%"+key+"%", year}, null, null, null);

        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }

        cursor.close();
        return result;
    }

    public List<CourseObject> getByCode(String key, String year) {
        List<CourseObject> result = new ArrayList<>();
        Cursor cursor = db.query(
                TABLE_NAME, null, "code LIKE ? AND year = ?", new String[]{"%"+key+"%", year}, null, null, null);

        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }

        cursor.close();
        return result;
    }

    public List<CourseObject> getByTime(String key, String year) {
        List<CourseObject> result = new ArrayList<>();
        Cursor cursor = db.query(
                TABLE_NAME, null, "time LIKE ? AND year = ?", new String[]{"%"+key+"%", year}, null, null, null);

        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }

        cursor.close();
        return result;
    }

    public List<CourseObject> getByName(String key, String year) {
        List<CourseObject> result = new ArrayList<>();
        Cursor cursor = db.query(
                TABLE_NAME, null, "cname LIKE ? AND year = ?", new String[]{"%"+key+"%", year}, null, null, null);

        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }

        cursor.close();
        return result;
    }

    public List<CourseObject> getByFullTextSearch(String key, String year) {
        List<CourseObject> result = new ArrayList<>();
        Cursor cursor = db.query(
                TABLE_NAME, null, "(cname LIKE ? OR teacher LIKE ? OR time LIKE ? OR code LIKE ?) AND year = ? LIMIT 50",
                new String[]{"%"+key+"%", "%"+key+"%", "%"+key+"%", "%"+key+"%", year}, null, null, null);

        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }

        cursor.close();
        return result;
    }
}
