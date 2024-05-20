package com.example.mess
/// AdminLoginActivity.kt

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AdminLoginActivity : AppCompatActivity() {

    private lateinit var adminUsernameEditText: EditText
    private lateinit var adminPasswordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)

        adminUsernameEditText = findViewById(R.id.usernameEditText)
        adminPasswordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            // Check admin credentials (replace with your authentication logic)
            val adminUsername = adminUsernameEditText.text.toString()
            val adminPassword = adminPasswordEditText.text.toString()

            if (isAdminAuthenticated(adminUsername, adminPassword)) {
                // If credentials are valid, navigate to AdminHomeActivity
                val intent = Intent(this, AdminHomeActivity::class.java)
                startActivity(intent)
                finish() // Optional: finish the current activity
            } else {
                // Display an error message or handle invalid credentials
                showToast("Invalid admin credentials")
            }
        }
    }

    private fun isAdminAuthenticated(username: String, password: String): Boolean {
        // Implement your authentication logic for admin
        // For example, check if username is "admin" and password is "admin123"
        return username == "admin" && password == "admin123"
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}