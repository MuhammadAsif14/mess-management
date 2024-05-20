package com.example.messimport

import android.app.AlertDialog
import com.example.mess.R
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment

class MealAddDialogFragment : DialogFragment() {

    interface MealAddListener {
        fun onMealAdded(category: String, title: String, description: String)
    }

    private var mealAddListener: MealAddListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater: LayoutInflater = requireActivity().layoutInflater
        val view: View = inflater.inflate(R.layout.dialog_add_meal, null)

        val radioGroup = view.findViewById<RadioGroup>(R.id.categoryRadioGroup)
        val titleEditText = view.findViewById<EditText>(R.id.titleEditText)
        val descriptionEditText = view.findViewById<EditText>(R.id.descriptionEditText)

        builder.setView(view)
            .setPositiveButton("Done") { dialogInterface: DialogInterface, i: Int ->
                val selectedCategoryId = radioGroup.checkedRadioButtonId
                val selectedRadioButton = view.findViewById<RadioButton>(selectedCategoryId)
                val selectedCategory = selectedRadioButton.text.toString()
                val mealTitle = titleEditText.text.toString()
                val mealDescription = descriptionEditText.text.toString()

                mealAddListener?.onMealAdded(selectedCategory, mealTitle, mealDescription)
                dismiss()
            }

        return builder.create()
    }


    fun setMealAddListener(listener: MealAddListener) {
        mealAddListener = listener
    }
}
