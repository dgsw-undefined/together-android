package dgsw.hs.kr.gatchigachi.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "example.db", null, 4) {
    val TABLE = "logs"

    val DATABASE_CREATE = "CREATE TABLE aaa ( id INTEGER PRIMARY KEY autoincrement, timestamp INTEGER, text STRING)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DATABASE_CREATE)
    }

    override fun onUpgrade(p0: SQLiteDatabase, p1: Int, p2: Int) {
    }

}