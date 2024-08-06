package com.example.fyp


import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.security.cert.X509Certificate
import javax.net.ssl.*

//var url: String? = null
var currentJson: JSONObject? = null
val quotes = mutableListOf<String>()
var predictions = mutableListOf<Double>()
val timestamps = mutableListOf<String>()
val prices = mutableListOf<String>()

fun connection () {
    //url = "https://fx-rates-papi.us-e2.cloudhub.io/api/fxrates/EURGBP"
    //url_string = "https://10.0.2.2:8081/api/users"

    val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
        override fun getAcceptedIssuers(): Array<X509Certificate> {
            return arrayOf()
        }
        override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) {
        }
        override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) {
        }
    })
    val sslContext = SSLContext.getInstance("SSL")
    sslContext.init(null, trustAllCerts, java.security.SecureRandom())
    HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.socketFactory)
    val hv = object : HostnameVerifier {
        override fun verify(hostname: String?, session: SSLSession?): Boolean {
            return true
        }
    }
    HttpsURLConnection.setDefaultHostnameVerifier(hv)
}

fun getPrices(url: String): MutableList<String> {
    prices.clear()
    connection()
    runBlocking {
        withContext(Dispatchers.IO) {

            //collect all JSON from web service as string
            var stb = StringBuilder("")
            var url = URL(url)
            var con = url.openConnection() as HttpURLConnection

            // use to read line by line
            val bf: BufferedReader
            try {
                bf = BufferedReader(InputStreamReader(con.inputStream))
            } catch (e: IOException) {
                e.printStackTrace()
                return@withContext
            }

            var line = bf.readLine()
            while (line != null) {
                stb.append(line)
                line = bf.readLine()
            }

            val json = JSONObject(stb.toString()) //.replace("[", "").replace("]", ""))
            currentJson = json

            var buy = json.getDouble("buy").toString()
            var sell = json.getDouble("sell").toString()
            prices.add(buy)
            prices.add(sell)
        }
    }
    return prices
}

fun getValues(url: String): MutableList<String> {
    quotes.clear()
    connection()

    runBlocking {
        withContext(Dispatchers.IO) {

            //collect all JSON from web service as string
            var stb = StringBuilder("")

            var url = URL(url)

            var con = url.openConnection() as HttpURLConnection

            // use to read line by line
            val bf: BufferedReader
            try {
                bf = BufferedReader(InputStreamReader(con.inputStream))
            } catch (e: IOException) {
                e.printStackTrace()
                return@withContext
            }

            var line = bf.readLine()
            while (line != null) {
                stb.append(line)
                line = bf.readLine()
            }

            val json = JSONObject(stb.toString()) //.replace("[", "").replace("]", ""))
            currentJson = json

            len = (json.get("quotes") as JSONArray).length()
            for (i in 0..len - 1) {
                var quote = (json.get("quotes") as JSONArray).getJSONObject(i).getString("quote")
                quotes.add(quote)
            }

            for (i in 0..len - 1) {
                var timestamp =
                    (json.get("quotes") as JSONArray).getJSONObject(i).getString("timestamp")
                timestamps.add(timestamp)
            }
        }
    }
    return quotes
}

fun getPredcition(url: String): MutableList<Double> {
    predictions.clear()
    connection()

    runBlocking {
        withContext(Dispatchers.IO) {

            //collect all JSON from web service as string
            var stb = StringBuilder("")
            var url = URL(url)
            var con = url.openConnection() as HttpURLConnection

            // use to read line by line
            val bf: BufferedReader
            try {
                bf = BufferedReader(InputStreamReader(con.inputStream))
            } catch (e: IOException) {
                e.printStackTrace()
                return@withContext
            }

            var line = bf.readLine()
            while (line != null) {
                stb.append(line)
                line = bf.readLine()
            }

            val json = JSONObject(stb.toString()) //.replace("[", "").replace("]", ""))
            currentJson = json

            val list = json.getJSONArray("list")
            for (i in 0 until list.length()) {
                predictions.add(list.getDouble(i))
            }
            //predictions = json.getJSONArray("list") as MutableList<String>

//            len = (json.get("list") as JSONArray).length()
//            for (i in 0..len - 1) {
//                var prediction_val = (json.get("list") as JSONArray).getJSONObject(i).toString()
//                predictions.add(prediction_val)
//            }

        }
    }
    return predictions
}


