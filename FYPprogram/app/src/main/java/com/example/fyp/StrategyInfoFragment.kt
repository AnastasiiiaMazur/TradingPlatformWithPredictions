package com.example.fyp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class StrategyInfoFragment : Fragment() {

    var str_name: TextView? = null
    var str_info1: TextView? = null
    var str_info2: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_strategy_info, container, false)

        str_name = view?.findViewById(R.id.str_name)
        str_info1 = view?.findViewById(R.id.str_info1)
        str_info2 = view?.findViewById(R.id.str_info2)

//        val args = arguments
//        val name = args?.getString("str_name")
//        val info1 = args?.getString("str_info1")
//        val info2 = args?.getString("str_info2")
//
//        str_name?.text = name
//        str_info1?.text = info1
//        str_info2?.text = info2

        arguments?.let { args ->
            str_name?.text = args.getString("str_name")
            str_info1?.text = args.getString("str_info1")
            str_info2?.text = args.getString("str_info2")
        }

        return view
    }
}