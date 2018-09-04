package id.cathode.android.sqlitedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Users",MODE_PRIVATE, null);

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");

        sqLiteDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Yusuf', 20)");

        sqLiteDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Bowo', 21)");

        //sqLiteDatabase.execSQL("DELETE FROM Users WHERE NAME = 'Yusuf'");
        //sqLiteDatabase.execSQL("DELETE FROM Users WHERE NAME = 'Bowo' LIMIT 6");
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM users", null);

        int nameIndex = cursor.getColumnIndex("name");
        int ageIndex = cursor.getColumnIndex("age");

        cursor.moveToFirst();

        while (cursor != null){
            Log.i("UserResults-name", cursor.getString(nameIndex));
            Log.i("UserResults-age", Integer.toString(cursor.getInt(ageIndex)));

            cursor.moveToNext();
        }
    }
}
