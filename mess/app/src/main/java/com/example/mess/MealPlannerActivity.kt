package com.example.mess
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.messimport.MealAddDialogFragment

class MealPlannerActivity : AppCompatActivity(), MealAddDialogFragment.MealAddListener{

    private var selectedDate = -1
    private val mealFragmentsByDate = mutableMapOf<Int, Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_planner)

        val dateButtonsLayout: LinearLayout = findViewById(R.id.dateButtonsLayout)
        for (i in 1..31) {
            val button = Button(this)
            button.text = i.toString()
            button.setOnClickListener {
                selectedDate = i
                findViewById<Button>(R.id.addButton).isEnabled = true
            }
            dateButtonsLayout.addView(button)
        }

        findViewById<Button>(R.id.addButton).setOnClickListener {
            val fragment = MealAddDialogFragment()
            fragment.setMealAddListener(this)
            fragment.show(supportFragmentManager, "mealAddDialog")
        }
    }

    override fun onMealAdded(category: String, title: String, description: String) {
        val fragmentContainerId = when (category) {
            "Breakfast" -> R.id.breakfastFragmentContainer
            "Lunch" -> R.id.lunchFragmentContainer
            "Dinner" -> R.id.dinnerFragmentContainer
            else -> throw IllegalArgumentException("Invalid category")
        }

        val existingFragment = supportFragmentManager.findFragmentById(fragmentContainerId)

        val fragment: Fragment = existingFragment ?: when (category) {
            "Breakfast" -> BreakfastFragment.newInstance(title, description)
            "Lunch" -> LunchFragment.newInstance(title, description)
            "Dinner" -> DinnerFragment.newInstance(title, description)
            else -> throw IllegalArgumentException("Invalid category")
        }

        supportFragmentManager.beginTransaction()
            .replace(fragmentContainerId, fragment)
            .commit() // Commit without allowing state loss


    }




}
