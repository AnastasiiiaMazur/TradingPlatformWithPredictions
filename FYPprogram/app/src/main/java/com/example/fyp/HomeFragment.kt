package com.example.fyp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment


class HomeFragment : Fragment() {

    var logout_button: Button? = null
    var greeting: TextView? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        logout_button = view?.findViewById<Button>(R.id.logout_button)
        greeting = view?.findViewById<TextView>(R.id.greeting)

        logout_button?.setOnClickListener {
            Log.d("Fragment", "button clicked")
            val intent = Intent(activity, MainActivity::class.java)
            //intent.putExtra("key", "value")
            startActivity(intent)
        }
        return view
    }
}