package com.example.ccl3

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception
import java.util.ArrayList

class SQLiteHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "list.db"
        private const val TBL_LIST = "tbl_list"
        private const val ID = "id"
        private const val NAME = "name"
       // private const val DATE_FROM = "dateFrom"
       // private const val DATE_TO = "dateTo"
        private const val REWARD = "reward"
    }


    override fun onCreate(db: SQLiteDatabase?) {
        val createTblList = ("CREATE TABLE " + TBL_LIST + "("
                + ID + " INTEGER PRIMARY KEY," + NAME + " TEXT,"
                + REWARD + " TEXT" + ")")
        db?.execSQL(createTblList)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_LIST")
        onCreate(db)
    }

    fun insertList(std: ListModelDb): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(NAME, std.name)
       // contentValues.put(DATE_FROM, std.dateFrom)
       // contentValues.put(DATE_TO, std.dateTo)
        contentValues.put(REWARD, std.reward)

        val success = db.insert(TBL_LIST, null, contentValues)
        db.close()

        return success
    }

    @SuppressLint("Range")
    fun getAllList(): ArrayList<ListModelDb>{
        val stdList: ArrayList<ListModelDb> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_LIST"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var name:String
       // var dateFrom:Int
       // var dateTo:Int
        var reward:String

        if (cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
               name = cursor.getString(cursor.getColumnIndex("name"))
              // dateFrom = cursor.getInt(cursor.getColumnIndex("date_from"))
             //  dateTo= cursor.getInt(cursor.getColumnIndex("date_to"))
               reward= cursor.getString(cursor.getColumnIndex("reward"))

                val std = ListModelDb(id=id, name= name, reward= reward)
                stdList.add(std)

            } while (cursor.moveToNext())
        }
        return stdList
    }
}