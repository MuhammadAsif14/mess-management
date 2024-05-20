package com.example.mess

// AdminHomeActivity.kt

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class AdminHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_home)
    }

    fun viewStudentDetails(view: View) {
        // Implement logic to view student details
        startActivity(Intent(this, StudentDetailsActivity::class.java))
    }

    fun viewMenu(view: View) {
        //    Implement logic to view menu
        startActivity(Intent(this, MealPlannerActivity::class.java))
        //}
    }
}
