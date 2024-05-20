package com.example.mess
// StudentDetailsActivity.kt

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var nameEditText: EditText
    private lateinit var cmsIdEditText: EditText
    private lateinit var studentDetailsTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        dbHelper = DatabaseHelper(this)
        nameEditText = findViewById(R.id.editTextStudentName)
        cmsIdEditText = findViewById(R.id.editTextCMSId)
        studentDetailsTextView = findViewById(R.id.textViewStudentDetails)

        val addStudentButton: Button = findViewById(R.id.btnAddStudent)
        val deleteStudentButton: Button = findViewById(R.id.btnDeleteStudent)

        addStudentButton.setOnClickListener {
            addStudent()
        }

        deleteStudentButton.setOnClickListener {
            deleteStudent()
        }
        val viewAllDetailsButton: Button = findViewById(R.id.btnViewAllStudents)

        viewAllDetailsButton.setOnClickListener {
            val intent = Intent(this, ViewStudentsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addStudent() {
        val studentName = nameEditText.text.toString()
        val cmsId = cmsIdEditText.text.toString()
        val password = generateRandomPassword()

        if (studentName.isNotBlank() && cmsId.isNotBlank()) {
            if (isStudentAlreadyAdded(studentName, cmsId)) {
                showToast("Student already added.")
            }else{val studentId = dbHelper.addStudent(studentName, cmsId, password)

            if (studentId != -1L) {
                //Log.d("Database", "Student added successfully. Password: $password")
                showToast("Student added successfully. Password: $password")
                nameEditText.text.clear()
                cmsIdEditText.text.clear()
            }
            else {
                //Log.e("Database", "Failed to add student.")
                showToast("Failed to add student.")
            }}
        } else if(studentName.isNotBlank()){
            showToast("Please enter a valid cms id.")
        }
        else{
            showToast("Please enter a valid student name.")
        }
    }
    private fun isStudentAlreadyAdded(studentName: String, cmsId: String): Boolean {
        val allStudents = dbHelper.getAllStudents()
        return allStudents.any { it.name == studentName && it.cmsID == cmsId }
    }

    private fun deleteStudent() {
        val cmsId = cmsIdEditText.text.toString()
        val studentName = nameEditText.text.toString()

        if (studentName.isNotBlank() && cmsId.isNotBlank()) {
            val deletedRows = dbHelper.deleteStudent(studentName, cmsId)
            if (deletedRows > 0) {
                showToast("Student deleted successfully.")
                cmsIdEditText.text.clear()
                nameEditText.text.clear()
            } else {
                showToast("No student found with the given name.")
            }
        }
        else if(studentName.isNotBlank()){
            showToast("Please enter a valid cms id.")
        }
        else{
            showToast("Please enter a valid student name.")
        }
    }


    private fun generateRandomPassword(): String {
        val passwordLength = 8
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        return (1..passwordLength)
            .map { charset.random() }
            .joinToString("")
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
