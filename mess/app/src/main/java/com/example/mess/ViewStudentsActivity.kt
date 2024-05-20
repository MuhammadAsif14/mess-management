package com.example.mess

// ViewStudentsActivity.kt

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mess.DatabaseHelper
import com.example.mess.Student

class ViewStudentsActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var textViewDetails: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_students)

        dbHelper = DatabaseHelper(this)
        textViewDetails = findViewById(R.id.textViewDetails)

        viewAllStudents()
    }

    private fun viewAllStudents() {
        val allStudents = dbHelper.getAllStudents()
        if (allStudents.isNotEmpty()) {
            val studentDetails = allStudents.joinToString(separator = "\n\n") {
                "Name: ${it.name}\nCMS ID: ${it.cmsID}\nPassword: ${it.password}"
            }
            textViewDetails.text = studentDetails
        } else {
            textViewDetails.text = "No students found."
        }
    }
}
