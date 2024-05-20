package com.example.mess

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MenuItemsActivity : AppCompatActivity() {

    private lateinit var textViewMenuItems: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_items)

        textViewMenuItems = findViewById(R.id.textViewMenuItems)
        val menuItems = intent.getSerializableExtra("menuItems") as? Array<MenuItem>

        if (!menuItems.isNullOrEmpty()) {
            val itemsDetails = menuItems.joinToString(separator = "\n\n") {
                "Name: ${it.name}\nPrice: ${it.price}\nCategory: ${it.category}"
            }
            textViewMenuItems.text = itemsDetails
        } else {
            textViewMenuItems.text = "No menu items found."
        }
    }
}
