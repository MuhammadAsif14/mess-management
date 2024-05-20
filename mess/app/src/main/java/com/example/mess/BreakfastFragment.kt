package com.example.mess

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class BreakfastFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_breakfast, container, false)
        // Retrieve meal details from arguments and populate the layout
        // Retrieve arguments passed from newInstance
        val title = arguments?.getString(ARG_TITLE)
        val description = arguments?.getString(ARG_DESCRIPTION)
        // Find the TextViews in the layout
        val titleTextView = view.findViewById<TextView>(R.id.breakfasttitle)
        val descriptionTextView = view.findViewById<TextView>(R.id.breakfastdescription)

        // Set the text for title and description TextViews
        titleTextView.text = "Title: $title"
        descriptionTextView.text = "Description: $description"

        // Use the title and description as needed within the fragment
        // For example, update TextViews with meal details

        return view
    }


    companion object {
        private const val ARG_TITLE = "title"
        private const val ARG_DESCRIPTION = "description"

        @JvmStatic
        fun newInstance(title: String, description: String): BreakfastFragment {
            val fragment = BreakfastFragment()
            val args = Bundle()
            args.putString(ARG_TITLE, title)
            args.putString(ARG_DESCRIPTION, description)
            fragment.arguments = args
            return fragment
        }
    }
}

// Implement LunchFragment and DinnerFragment similarly
