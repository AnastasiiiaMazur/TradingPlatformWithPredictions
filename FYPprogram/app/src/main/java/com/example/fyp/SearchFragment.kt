package com.example.fyp

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class SearchFragment : Fragment() {

    var button_test: Button? = null
    var question: EditText? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_search, container, false)

        button_test = view.findViewById(R.id.button_search)
        question = view.findViewById(R.id.search_field)

        button_test?.setOnClickListener {
            Log.d("Fragment Search", "button clicked")

            val builder = AlertDialog.Builder(requireContext())
            val q = question!!.text.toString().trim().lowercase()

            when (q) {
                "stock market" -> {
                    builder.setMessage(getString(R.string.stock_market))
                    builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id -> })
                    val dialog = builder.create()
                    dialog.show()
                }
                "positions" -> {
                    builder.setMessage(getString(R.string.p_information))
                    builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id -> })
                    val dialog = builder.create()
                    dialog.show()
                }
                "forex" -> {
                    builder.setMessage(getString(R.string.forex))
                    builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id -> })
                    val dialog = builder.create()
                    dialog.show()
                }
//                "buy", "sell" -> {
//                    builder.setMessage(getString(R.string.b_and_s))
//                    builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id ->
//                        // User clicked OK button
//                    })
//                    val dialog = builder.create()
//                    dialog.show()
//                }
                "trading" -> {
                    builder.setMessage(getString(R.string.trading))
                    builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id -> })
                    val dialog = builder.create()
                    dialog.show()
                }
                else -> {
                    builder.setMessage("Do not have answer to this question")
                    builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id -> })
                    val dialog = builder.create()
                    dialog.show()
                }
            }


        }
        return view
    }
}