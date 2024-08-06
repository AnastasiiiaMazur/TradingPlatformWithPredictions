package com.example.fyp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import java.io.Serializable

class StockMarketFragment : Fragment() {

    var eur_usd: ImageButton? = null
    var eur_gbp: ImageButton? = null
    var eur_chf: ImageButton? = null
    var quotes = mutableListOf<String>()
    var price = mutableListOf<String>()
    var prediction = mutableListOf<Double>()
    var eur_usd_price_text: TextView? = null
    var eur_gbp_price_text: TextView? = null
    var eur_chf_price_text: TextView? = null
    var current_balance: TextView? = null

//    var quotes_eur_gbp = mutableListOf<String>()
//    var quotes_eur_chf = mutableListOf<String>()

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_stock_market, container, false)

        eur_usd = view?.findViewById(R.id.eur_usd)
        eur_gbp = view?.findViewById(R.id.eur_gbp)
        eur_chf = view?.findViewById(R.id.eur_chf)

        eur_usd_price_text = view?.findViewById(R.id.eur_usd_price_text)
        eur_gbp_price_text = view?.findViewById(R.id.eur_gbp_price_text)
        eur_chf_price_text = view?.findViewById(R.id.eur_chf_price_text)

        current_balance = view?.findViewById(R.id.current_balance)

        var user = currentUser()
        var balance = user.balance
        current_balance?.setText(balance.toString())

//        val args = arguments
//        var new_balance = args?.getInt("new_balance")
//        if (new_balance != null) {
//            current_balance?.setText(new_balance.toString())
//        } else {
//            current_balance?.setText(balance.toString())
//        }


        var eur_usd_price = getPrices("https://fx-rates-papi.us-e2.cloudhub.io/api/fxrates/EURUSD/latest")
        eur_usd_price_text?.setText(eur_usd_price[0])

        var eur_gbp_price = getPrices("https://fx-rates-papi.us-e2.cloudhub.io/api/fxrates/EURGBP/latest")
        eur_gbp_price_text?.setText(eur_gbp_price[0])

        var eur_chf_price = getPrices("https://fx-rates-papi.us-e2.cloudhub.io/api/fxrates/EURCHF/latest")
        eur_chf_price_text?.setText(eur_chf_price[0])

        val STMarketFragment = StockMarket2Fragment()
        //val bundle = Bundle()

        eur_usd?.setOnClickListener(View.OnClickListener {
            val bundle = Bundle()
            quotes = getValues("https://fx-rates-papi.us-e2.cloudhub.io/api/fxrates/EURUSD")
            price = getPrices("https://fx-rates-papi.us-e2.cloudhub.io/api/fxrates/EURUSD/latest")
            prediction = getPredcition("https://fx-rates-papi.us-e2.cloudhub.io/api/fxrates/EURUSD/predict")
            bundle.putSerializable("quotes", quotes as Serializable)
            bundle.putSerializable("prices", price as Serializable)
            bundle.putSerializable("predctions", prediction as Serializable)
            bundle.putString("image_name", "eur_usd")
            bundle.putString("currency", "EURUSD")
            STMarketFragment.arguments = bundle

            findNavController().navigate(
                R.id.action_stockMarketFragment_to_stockMarket2Fragment,
                bundle
            )
        })

        eur_gbp?.setOnClickListener(View.OnClickListener {
            val bundle = Bundle()
            quotes = getValues("https://fx-rates-papi.us-e2.cloudhub.io/api/fxrates/EURGBP")
            price = getPrices("https://fx-rates-papi.us-e2.cloudhub.io/api/fxrates/EURGBP/latest")
            prediction = getPredcition("https://fx-rates-papi.us-e2.cloudhub.io/api/fxrates/EURGBP/predict")
            bundle.putSerializable("quotes", quotes as Serializable)
            bundle.putSerializable("prices", price as Serializable)
            bundle.putSerializable("predctions", prediction as Serializable)
            bundle.putString("image_name", "eur_gbp")
            bundle.putString("currency", "EURGBP")
            STMarketFragment.arguments = bundle

            findNavController().navigate(
                R.id.action_stockMarketFragment_to_stockMarket2Fragment,
                bundle
            )
        })

        eur_chf?.setOnClickListener(View.OnClickListener {
            val bundle = Bundle()
            quotes = getValues("https://fx-rates-papi.us-e2.cloudhub.io/api/fxrates/EURCHF")
            price = getPrices("https://fx-rates-papi.us-e2.cloudhub.io/api/fxrates/EURCHF/latest")
            prediction = getPredcition("https://fx-rates-papi.us-e2.cloudhub.io/api/fxrates/EURCHF/predict")
            bundle.putSerializable("quotes", quotes as Serializable)
            bundle.putSerializable("prices", price as Serializable)
            bundle.putSerializable("predctions", prediction as Serializable)
            bundle.putString("image_name", "eur_chf")
            bundle.putString("currency", "EURCHF")
            STMarketFragment.arguments = bundle

            findNavController().navigate(
                R.id.action_stockMarketFragment_to_stockMarket2Fragment,
                bundle
            )
        })

        //Navigation.findNavController(view).navigate(R.id.action_stockMarketFragment_to_stockMarket2Fragment)
        return view
    }
}