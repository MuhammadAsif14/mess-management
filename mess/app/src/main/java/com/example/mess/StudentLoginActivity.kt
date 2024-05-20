package com.example.mess
// StudentLoginActivity.kt
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class StudentLoginActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var nameEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_login)

        dbHelper = DatabaseHelper(this)
        nameEditText = findViewById(R.id.studentNameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)

        val loginButton: Button = findViewById(R.id.loginButton)
        loginButton.setOnClickListener {
            performStudentLogin()
        }
    }

    private fun performStudentLogin() {
        val studentName = nameEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (dbHelper.isStudentLoginValid(studentName, password)) {
            // Login successful, you can navigate to the student's home page or perform other actions
            showToast("Login successful")
        } else {
            showToast("Invalid credentials. Please try again.")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
