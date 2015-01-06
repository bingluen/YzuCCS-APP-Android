package net.erickson.yzucss_app.Services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.erickson.yzucss_app.Resource.CourseRawData;

/**
 * Created by Erickson on 2014/12/22.
 */
public class EJSQLHelper extends SQLiteOpenHelper {
    // 資料庫名稱
    public static final String DATABASE_NAME = "CourseDatabase.db";
    // 資料庫版本，資料結構改變的時候要更改這個數字，通常是加一
    public static final int VERSION = 1;
    // 資料庫物件，固定的欄位變數
    private static SQLiteDatabase database;

    // 建構子，在一般的應用都不需要修改
    public EJSQLHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                      int version) {
        super(context, name, factory, version);
    }

    // 需要資料庫的元件呼叫這個方法，這個方法在一般的應用都不需要修改
    public static SQLiteDatabase getDatabase(Context context) {
        if (database == null || !database.isOpen()) {
            database = new EJSQLHelper(context, DATABASE_NAME,
                    null, VERSION).getWritableDatabase();
        }

        return database;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 建立應用程式需要的表格
        db.execSQL(AccessCourseDatabase.CREATE_TABLE);
        db.execSQL(AccessUserTableDatabase.CREATE_LIST_TABLE);
        db.execSQL(AccessUserTableDatabase.CREATE_COURSE_TABLE);
        db.execSQL(AccessUserTableDatabase.CREATE_OCCUPIED_TABLE);
        // 建立初始資料
        for(int i = 0; i < CourseRawData.RAW_DATA_SQL.length; i++)
            db.execSQL(CourseRawData.RAW_DATA_SQL[i]);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 刪除原有的表格
        // 待會再回來完成它
        db.execSQL("DROP TABLE IF EXISTS " + AccessCourseDatabase.TABLE_NAME);
        // 呼叫onCreate建立新版的表格
        onCreate(db);
    }

}
