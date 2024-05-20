package com.example.mess
// DatabaseHelper.kt

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "students.db"
        private const val DATABASE_VERSION = 3
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Create the "students" table
        val createTableQuery = """
             CREATE TABLE ${DatabaseContract.UserEntry.TABLE_NAME} (
        ${DatabaseContract.UserEntry.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
        ${DatabaseContract.UserEntry.COLUMN_NAME} TEXT,
        ${DatabaseContract.UserEntry.COLUMN_CMSID} TEXT,
        ${DatabaseContract.UserEntry.COLUMN_PASSWORD} TEXT
    )
"""
        db?.execSQL(createTableQuery)

        val createMenuTableQuery = """
        CREATE TABLE ${DatabaseContract.MenuEntry.TABLE_NAME} (
            ${DatabaseContract.MenuEntry.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
            ${DatabaseContract.MenuEntry.COLUMN_NAME} TEXT,
            ${DatabaseContract.MenuEntry.COLUMN_PRICE} REAL,
            ${DatabaseContract.MenuEntry.COLUMN_CATEGORY} TEXT
        )
    """
        db?.execSQL(createMenuTableQuery)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Drop the table and recreate on upgrade
        val dropTableQuery = "DROP TABLE IF EXISTS ${DatabaseContract.UserEntry.TABLE_NAME}"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun addStudent(name: String, cmsId: String, password: String): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseContract.UserEntry.COLUMN_NAME, name)
            put(DatabaseContract.UserEntry.COLUMN_CMSID, cmsId)
            put(DatabaseContract.UserEntry.COLUMN_PASSWORD, password)
        }
        val id = db.insert(DatabaseContract.UserEntry.TABLE_NAME, null, values)
        db.close()
        return id
    }

    fun deleteStudent(name: String, cmsId: String): Int {
        val db = this.writableDatabase
        val whereClause = "${DatabaseContract.UserEntry.COLUMN_CMSID} = ?"
        val whereArgs = arrayOf(cmsId)
        val deletedRows = db.delete(DatabaseContract.UserEntry.TABLE_NAME, whereClause, whereArgs)
        db.close()
        return deletedRows
    }

    fun getAllStudents(): List<Student> {
        val students = mutableListOf<Student>()
        val db = this.readableDatabase
        val cursor: Cursor = db.query(
            DatabaseContract.UserEntry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )

        while (cursor.moveToNext()) {

            val id = cursor.getLong(cursor.getColumnIndex(DatabaseContract.UserEntry.COLUMN_ID))
            val name = cursor.getString(cursor.getColumnIndex(DatabaseContract.UserEntry.COLUMN_NAME))
            val cmsId = cursor.getString(cursor.getColumnIndex(DatabaseContract.UserEntry.COLUMN_CMSID))
            val password = cursor.getString(cursor.getColumnIndex(DatabaseContract.UserEntry.COLUMN_PASSWORD))

            students.add(Student(id, name, cmsId, password))
        }

        cursor.close()
        db.close()

        return students
    }
    // DatabaseHelper.kt
    fun isStudentLoginValid(name: String, password: String): Boolean {
        val db = this.readableDatabase
        val selection = "${DatabaseContract.UserEntry.COLUMN_NAME} = ? AND ${DatabaseContract.UserEntry.COLUMN_PASSWORD} = ?"
        val selectionArgs = arrayOf(name, password)
        val cursor: Cursor = db.query(
            DatabaseContract.UserEntry.TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        val isValid = cursor.moveToFirst()

        cursor.close()
        db.close()

        return isValid
    }

    // DatabaseHelper.kt
    fun addMenuItem(name: String, price: Double, category: String): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseContract.MenuEntry.COLUMN_NAME, name)
            put(DatabaseContract.MenuEntry.COLUMN_PRICE, price)
            put(DatabaseContract.MenuEntry.COLUMN_CATEGORY, category)
        }
        val id = db.insert(DatabaseContract.MenuEntry.TABLE_NAME, null, values)
        db.close()
        return id
    }

    fun deleteMenuItem(name: String): Int {
        val db = this.writableDatabase
        val whereClause = "${DatabaseContract.MenuEntry.COLUMN_NAME} = ?"
        val whereArgs = arrayOf(name)
        val deletedRows = db.delete(DatabaseContract.MenuEntry.TABLE_NAME, whereClause, whereArgs)
        db.close()
        return deletedRows
    }

    // DatabaseHelper.kt

    // Add a new method to get menu items with details
    fun getAllMenuItemsWithDetails(): List<MenuItem> {
        val menuItemsWithDetails = mutableListOf<MenuItem>()
        val db = this.readableDatabase
        val cursor = db.query(
            DatabaseContract.MenuEntry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )

        while (cursor.moveToNext()) {
            val name = cursor.getString(cursor.getColumnIndex(DatabaseContract.MenuEntry.COLUMN_NAME))
            val price = cursor.getDouble(cursor.getColumnIndex(DatabaseContract.MenuEntry.COLUMN_PRICE))
            val category = cursor.getString(cursor.getColumnIndex(DatabaseContract.MenuEntry.COLUMN_CATEGORY))
            val dayOfWeek = cursor.getString(cursor.getColumnIndex(DatabaseContract.MenuEntry.COLUMN_DAY_OF_WEEK))

            menuItemsWithDetails.add(MenuItem(name, price, category, dayOfWeek))
        }

        cursor.close()
        db.close()

        return menuItemsWithDetails
    }


}
