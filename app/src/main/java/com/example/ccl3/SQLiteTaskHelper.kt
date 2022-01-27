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
import kotlin.collections.ArrayList

class SQLiteTaskHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "tasks.db"
        private const val TBL_TASK = "tbl_task"
        private const val ID = "id"
        private const val NAMETASK = "nameTask"
        private const val DESCRIPTION = "description"
        private const val LISTID = "listId"
        private const val DONE = "done"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblTask = ("CREATE TABLE " + TBL_TASK + "("
                + ID + " INTEGER PRIMARY KEY," + NAMETASK + " TEXT,"
                + DESCRIPTION + " TEXT," + LISTID + " INTEGER," + DONE + " BOOLEAN" + ")")
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
        contentValues.put(LISTID, std.listId)
        contentValues.put(DONE, std.done)

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
        var listId: Int
        var done: Boolean
        if (cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                nameTask = cursor.getString(cursor.getColumnIndex("nameTask"))
                description= cursor.getString(cursor.getColumnIndex("description"))
                listId = cursor.getInt(cursor.getColumnIndex("listId"))
                //get boolean here or what does this do lost help
                done = cursor.equals(cursor.getColumnIndex("done"))

                val std = TasksModelDB(id=id, nameTask= nameTask, description= description, listId= listId, done = done)
                stdTask.add(std)

            } while (cursor.moveToNext())
        }
        return stdTask
    }

     @SuppressLint("Range")
     fun getTasksfromList(listId: Int): ArrayList<TasksModelDB>{
         val listTasks: ArrayList<TasksModelDB> = ArrayList()
         val selectQuery = "SELECT * FROM $TBL_TASK WHERE $LISTID=${listId}"
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
         var nameTask: String
         var description: String
         var listId: Int
         var done: Boolean

         if (cursor.moveToFirst()){
             do {
                 id = cursor.getInt(cursor.getColumnIndex("id"))
                 nameTask = cursor.getString(cursor.getColumnIndex("nameTask"))
                 description= cursor.getString(cursor.getColumnIndex("description"))
                 listId = cursor.getInt(cursor.getColumnIndex("listId"))
                 //get boolean here or what does this do lost help
                 done = cursor.equals(cursor.getColumnIndex("done"))

                 val tasks = TasksModelDB(id=id, nameTask= nameTask, description= description, listId = listId, done = done)
                 listTasks.add(tasks)

             } while (cursor.moveToNext())
         }
         return listTasks
     }
}