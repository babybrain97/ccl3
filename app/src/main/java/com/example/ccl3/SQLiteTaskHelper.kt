package com.example.ccl3

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.*

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

 class SQLiteTaskHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "tasks.db"
        private const val TBL_TASK = "tbl_task"
        private const val ID = "id"
        private const val NAMETASK = "nameTask"
        private const val DESCRIPTION = "description"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblTask = ("CREATE TABLE " + TBL_TASK + "("
                + ID + " INTEGER PRIMARY KEY," + NAMETASK + " TEXT,"
                + DESCRIPTION + " TEXT" + ")")
        db?.execSQL(createTblTask)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_TASK")
        onCreate(db)
    }

    fun insertList(std: TasksModelDB): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(NAMETASK, std.nameTask)
        contentValues.put(DESCRIPTION, std.description)

        val success = db.insert(TBL_TASK, null, contentValues)
        db.close()

        return success
    }

    @SuppressLint("Range")
    fun getAllTask(): ArrayList<TasksModelDB>{
        val stdTask: ArrayList<TasksModelDB> = ArrayList()
        val selectQuery = "SELECT * FROM ${SQLiteTaskHelper.TBL_TASK}"
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
        var nameTask:String
        var description:String
        if (cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                nameTask = cursor.getString(cursor.getColumnIndex("nameTask"))
                description= cursor.getString(cursor.getColumnIndex("description"))

                val std = TasksModelDB(id=id, nameTask= nameTask, description= description)
                stdTask.add(std)

            } while (cursor.moveToNext())
        }
        return stdTask

    }
}