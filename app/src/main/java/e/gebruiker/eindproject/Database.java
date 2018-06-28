package e.gebruiker.eindproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static Database database;

    private static int dbVersion = 1;
    private static String dbName = "transactions.db";

    public static String columnId = "_id";
    public static String columnTag = "tag";
    public static String columnPrice = "price";
    public static String columnCategory = "category";
    public static String columnDate = "date";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS transactions( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tag TEXT, price TEXT, category TEXT, date DATETIME DEFAULT CURRENT_TIMESTAMP)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + dbName);
        onCreate(db);
    }

    // get cursor for transaction-data
    public Cursor selectAll() {
        Cursor cursor = database.getWritableDatabase().rawQuery("SELECT * FROM transactions", null );
        return cursor;
    }

    // insert data into sql-database
    public static void insert(TransactionEntry entry) {
        SQLiteDatabase db = database.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("tag", entry.getTag());
        contentValues.put("price", entry.getPrice().toString());
        contentValues.put("category", entry.getCategory());

        db.insert("transactions", null, contentValues);
    }

    public Database(Context context) {
        super(context, dbName, null, dbVersion);
    }

    // get data from sql-database
    public static Database getDatabase(Context context) {
        if (database == null) {
            database = new Database(context);
        }
        return database;
    }


}
