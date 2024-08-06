package com.example.fyp

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController

class StrategyFragment : Fragment() {

    var button_strategy1: ImageButton? = null
    var button_strategy2: ImageButton? = null
    var button_strategy3: ImageButton? = null
    var button_strategy4: ImageButton? = null
    var button_strategy5: ImageButton? = null
    var button_strategy6: ImageButton? = null
    var button_strategy7: ImageButton? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_strategy, container, false)

        button_strategy1 = view?.findViewById<ImageButton>(R.id.button_strategy1)
        button_strategy2 = view?.findViewById<ImageButton>(R.id.button_strategy2)
        button_strategy3 = view?.findViewById<ImageButton>(R.id.button_strategy3)
        button_strategy4 = view?.findViewById<ImageButton>(R.id.button_strategy4)
        button_strategy5 = view?.findViewById<ImageButton>(R.id.button_strategy5)
        button_strategy6 = view?.findViewById<ImageButton>(R.id.button_strategy6)
        button_strategy7 = view?.findViewById<ImageButton>(R.id.button_strategy7)

        //val bundle = Bundle()

        val strInfo = StrategyInfoFragment()

        //StrInfo.arguments = bundle

        button_strategy1?.setOnClickListener {
            val bundle = Bundle().apply {
                putString("str_name", "News trading strategy")
                putString("str_info1", resources.getString(R.string.strategy_1))
                putString("str_info2", resources.getString(R.string.strategy_12))
            }
            strInfo.arguments = bundle
            //Navigation.findNavController(view).navigate(R.id.action_strategyFragment_to_strategyInfoFragment)
            findNavController().navigate(
                R.id.action_strategyFragment_to_strategyInfoFragment,
                bundle
            )
        }

        button_strategy2?.setOnClickListener {
            val bundle = Bundle().apply {
                putString("str_name", "EOD trading strategy")
                putString("str_info1", resources.getString(R.string.strategy_2))
                putString("str_info2", resources.getString(R.string.strategy_22))
            }
            strInfo.arguments = bundle
            //Navigation.findNavController(view).navigate(R.id.action_strategyFragment_to_strategyInfoFragment)
            findNavController().navigate(
                R.id.action_strategyFragment_to_strategyInfoFragment,
                bundle
            )
        }

        button_strategy3?.setOnClickListener {
            val bundle = Bundle().apply {
                putString("str_name", "Swing trading strategy")
                putString("str_info1", resources.getString(R.string.strategy_3))
                putString("str_info2", resources.getString(R.string.strategy_32))
            }
            strInfo.arguments = bundle
            //Navigation.findNavController(view).navigate(R.id.action_strategyFragment_to_strategyInfoFragment)
            findNavController().navigate(
                R.id.action_strategyFragment_to_strategyInfoFragment,
                bundle
            )
        }

        button_strategy4?.setOnClickListener {
            val bundle = Bundle().apply {
                putString("str_name", "Day trading strategy")
                putString("str_info1", resources.getString(R.string.strategy_4))
                putString("str_info2", resources.getString(R.string.strategy_42))
            }
            strInfo.arguments = bundle
            //Navigation.findNavController(view).navigate(R.id.action_strategyFragment_to_strategyInfoFragment)
            findNavController().navigate(
                R.id.action_strategyFragment_to_strategyInfoFragment,
                bundle
            )
        }

        button_strategy5?.setOnClickListener {
            val bundle = Bundle().apply {
                putString("str_name", "Trend trading strategy")
                putString("str_info1", resources.getString(R.string.strategy_5))
                putString("str_info2", resources.getString(R.string.strategy_52))
            }
            strInfo.arguments = bundle
            //Navigation.findNavController(view).navigate(R.id.action_strategyFragment_to_strategyInfoFragment)
            findNavController().navigate(
                R.id.action_strategyFragment_to_strategyInfoFragment,
                bundle
            )
        }

        button_strategy6?.setOnClickListener {
            val bundle = Bundle().apply {
                putString("str_name", "Scalping trading strategy")
                putString("str_info1", resources.getString(R.string.strategy_6))
                putString("str_info2", resources.getString(R.string.strategy_62))
            }
            strInfo.arguments = bundle
            //Navigation.findNavController(view).navigate(R.id.action_strategyFragment_to_strategyInfoFragment)
            findNavController().navigate(
                R.id.action_strategyFragment_to_strategyInfoFragment,
                bundle
            )
        }

        button_strategy7?.setOnClickListener {
            val bundle = Bundle().apply {
                putString("str_name", "Position trading strategy")
                putString("str_info1", resources.getString(R.string.strategy_7))
                putString("str_info2", resources.getString(R.string.strategy_72))
            }
            strInfo.arguments = bundle
            //Navigation.findNavController(view).navigate(R.id.action_strategyFragment_to_strategyInfoFragment)
            findNavController().navigate(
                R.id.action_strategyFragment_to_strategyInfoFragment,
                bundle
            )
        }

        return view
    }
}