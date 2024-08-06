package com.example.fyp

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

class PositionsFragment : Fragment() {

    var currency: TextView? = null
    var img: ImageButton? = null
    var price: TextView? = null
    var closed: TextView? = null
    var text_closed: TextView? = null
    var income: TextView? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_positions, container, false)

        val inflater = LayoutInflater.from(context)
        val parentView = view.findViewById<LinearLayout>(R.id.middle_layout)
        val apiValue = getPositionsInfo().size

        var position = getPositionsInfo().sortedByDescending { it["id"].toString().toDouble() }

        for (i in 0 until apiValue) {
            val childView = inflater.inflate(R.layout.positions_view, null)
            parentView.addView(childView)

            currency = childView?.findViewById(R.id.currency)
            img = childView?.findViewById(R.id.img)
            price = childView?.findViewById(R.id.price)
            closed = childView?.findViewById(R.id.closed)
            text_closed = childView?.findViewById(R.id.text_closed)
            income = childView?.findViewById(R.id.income)

            var pair = position.get(i).get("pair").toString()
            currency?.setText(pair)

            price?.setText(position.get(i).get("startPrice").toString())

            var pos = position[i].get("id").toString().toInt()

            if (position.get(i).get("endPrice").toString() == "") {
                text_closed?.setText("OPEN")
                closed?.setText("")
                income?.setText("")

                img?.setOnClickListener {
                    //val bundle = Bundle()
                    //val STMarketFragment = StockMarketFragment()
                    val builder = AlertDialog.Builder(requireActivity())
                    builder.setMessage("Do you want to close this position?")
                        .setPositiveButton("Yes") { _, _ ->
                            closeProcess(pos)
                            text_closed?.setText("CLOSED")
//                            var user = currentUser()
//                            var new_balance = user.balance
//                            bundle.putInt("new_balance", new_balance)
//                            STMarketFragment.arguments = bundle
                        }
                        .setNegativeButton("No", null)
                        .show()
                }

            } else {
                closed?.setText(position.get(i).get("endPrice").toString())
                income?.setText(position.get(i).get("result").toString())
            }

            if (pair == "EURUSD") {
                img?.setImageResource(R.drawable.eur_usd)
            } else if (pair == "EURGBP") {
                img?.setImageResource(R.drawable.eur_gbp)
            } else {
                img?.setImageResource(R.drawable.eur_chf)
            }
        }
        return view
    }
}


