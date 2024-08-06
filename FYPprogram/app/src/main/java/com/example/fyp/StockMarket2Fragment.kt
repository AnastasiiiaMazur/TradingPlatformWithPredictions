package com.example.fyp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlin.math.roundToInt


class StockMarket2Fragment : Fragment() {

    var buy_button: Button? = null
    var sell_button: Button? = null
    var predict: Button? = null
    var buy_price: TextView? = null
    var sell_price: TextView? = null
    var img: ImageView? = null
    var seekBar: SeekBar? = null
    var progressTextView: TextView? = null
    var buyAction: LinearLayout? = null
    var amount = 0
    var buy_sell: Button? = null
    var actionMessage: TextView? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_stock_market2, container, false)

        var lineChart = view?.findViewById<LineChart>(R.id.lineChart)

        buy_button = view?.findViewById(R.id.buy_button)
        sell_button = view?.findViewById(R.id.sell_button)
        predict = view?.findViewById(R.id.predict)
        buy_price = view?.findViewById(R.id.buy_price)
        sell_price = view?.findViewById(R.id.sell_price)
        img = view?.findViewById(R.id.img)
        seekBar = view?.findViewById(R.id.seekBar)
        progressTextView = view?.findViewById(R.id.progressTextView)
        buyAction = view?.findViewById(R.id.buyAction)
        buy_sell = view?.findViewById(R.id.buy_sell)
        actionMessage = view?.findViewById(R.id.actionMessage)

        val entries = ArrayList<Entry>()
        val entries_prediction = ArrayList<Entry>()

        val args = arguments

        val currency = args?.getString("currency")

        val image_name = args?.getString("image_name")
        val imageResourceId = resources.getIdentifier(image_name, "drawable", context?.packageName)
        img?.setImageResource(imageResourceId)


        val quotes = (args?.getSerializable("quotes") as? MutableList<String>)!!
        if (quotes != null) {
            for (i in 0..quotes.size - 1) {
                var first_value = i.toFloat() + 1
                var second_value = quotes.get(i).toFloat()
                entries.add(Entry(first_value, second_value))
            }
        }

        val lastEntry = entries[entries.size - 1]
        entries_prediction.add(Entry(lastEntry.x, lastEntry.y))
        val predictions = (args?.getSerializable("predctions") as? MutableList<Double>)!!
        if (predictions != null) {
            for (i in 0..predictions.size - 1) {
                var first_value = i.toFloat() + lastEntry.x + 1 // add lastEntry.x to continue from end of first line
                var second_value = predictions.get(i).toFloat()
                entries_prediction.add(Entry(first_value, second_value))
            }
        }

        val prices = (args?.getSerializable("prices") as? MutableList<String>)!!
        buy_price?.setText(prices[0])
        sell_price?.setText(prices[1])

        buy_button?.setOnClickListener{
            buyAction?.visibility = View.VISIBLE
            buy_sell?.setText("buy")

            var currentUser = currentUser()
            var formula = ((currentUser.balance.toFloat() - currentUser.holdingBalance.toFloat())) / prices[0].toFloat()

            seekBar?.max = formula.roundToInt()
            seekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    // Update UI with the current custom value
                    //val customValue = quotes[progress]
                    // ...
                    progressTextView?.text = progress.toString()
                    amount = progress
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            })

            buy_sell?.setOnClickListener{
                buyProcess(amount, currency.toString())
                actionMessage?.setText("Success")
            }
        }

        sell_button?.setOnClickListener{
            buyAction?.visibility = View.VISIBLE
            buy_sell?.setText("sell")

            var currentUser = currentUser()
            var formula = ((currentUser.balance.toFloat() - currentUser.holdingBalance.toFloat())) / prices[0].toFloat()

            seekBar?.max = formula.roundToInt()
            seekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    // Update UI with the current custom value
                    //val customValue = quotes[progress]
                    // ...
                    progressTextView?.text = progress.toString()
                    amount = progress
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            })

            buy_sell?.setOnClickListener{
                sellProcess(amount, currency.toString())
                actionMessage?.setText("Success")
            }
        }

        val vl = LineDataSet(entries, "Stock Market")
        vl.setDrawValues(false)
        vl.setDrawFilled(true)
        vl.lineWidth = 3f
        vl.fillColor = R.color.bg_color
        vl.fillAlpha = R.color.red

        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(vl)

        predict?.setOnClickListener {
            val v2 = LineDataSet(entries_prediction, "Prediction")
            v2.setDrawValues(false)
            v2.setDrawFilled(true)
            v2.lineWidth = 3f
            v2.fillColor = R.color.white
            v2.fillAlpha = R.color.black
            v2.color = ContextCompat.getColor(requireContext(), R.color.red)

            // add a new entry with the desired x-value
            val lastEntry = vl.getEntryForIndex(vl.entryCount - 1)
            val lastEntryXValue = lastEntry.x
            v2.addEntry(Entry(lastEntryXValue + 1f, entries_prediction[0].y.toFloat()))

            dataSets.add(v2)
            lineChart!!.data.notifyDataChanged()
            lineChart!!.notifyDataSetChanged()
            lineChart!!.invalidate()
        }

        lineChart!!.xAxis.labelRotationAngle = 0f
        lineChart!!.data = LineData(dataSets)
        lineChart!!.setTouchEnabled(true)
        lineChart!!.setPinchZoom(true)
        lineChart!!.description.text = "Days"
        lineChart!!.setNoDataText("No forex yet!")
        lineChart!!.animateX(1800, Easing.EaseInExpo)
        val markerView = CustomMarker(requireContext(), R.layout.marker_view)
        lineChart?.marker = markerView


////Part7
////        lineChart!!.axisRight.isEnabled = false
////        var j = 0
////        lineChart!!.xAxis.axisMaximum = j+0.1f


        return view
    }
}