package com.example.mess

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var radioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroup = findViewById(R.id.radioGroup)
    }

    fun onRadioButtonClicked(view: View) {
        // No need to handle radio button click individually
        // The logic will be handled on the "Next" button click
    }

    fun onNextButtonClick(view: View) {
        val selectedRadioButtonId = radioGroup.checkedRadioButtonId

        if (selectedRadioButtonId != -1) {
            val isAdmin = selectedRadioButtonId == R.id.adminRadioButton

            val intent = if (isAdmin) {
                Intent(this, AdminLoginActivity::class.java)
            } else {
                Intent(this, StudentLoginActivity::class.java)
            }

            startActivity(intent)
        } else {
            showToast("Please select either Admin or Student")
            // No radio button is selected, you can show an error message or handle it accordingly
        }

    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        //Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
