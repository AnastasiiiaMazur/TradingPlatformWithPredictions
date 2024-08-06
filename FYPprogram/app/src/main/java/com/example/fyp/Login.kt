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

var url_string: String? = null
var current_json: JSONObject? = null
val emailList = mutableListOf<String>()
val passList = mutableListOf<String>()
var len = 0
var userId = mutableListOf<Int>()
var balance = mutableListOf<Int>()
var holdingBalance = mutableListOf<Int>()
var curUser: Int = 0
var curBalance: Int = 0
var curHoldBalance: Int = 0

fun connectToDb () {
    url_string = "https://trading-app-papi.us-e2.cloudhub.io/api/users"
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

fun getUserInfo(): Int {

    connectToDb()

    runBlocking {
        withContext(Dispatchers.IO) {

            //collect all JSON from web service as string
            var stb = StringBuilder("")

            var url = URL(url_string)

            var con = url.openConnection() as HttpURLConnection

            // use to read line by line
            val bf: BufferedReader
            try {
                bf = BufferedReader(InputStreamReader(con.inputStream))
            }
            catch (e: IOException) {
                e.printStackTrace()
                return@withContext
            }

            var line = bf.readLine()
            while (line != null) {
                stb.append(line)
                line = bf.readLine()
            }

            val json = JSONObject(stb.toString()) //.replace("[", "").replace("]", ""))
            current_json = json
            //(json.get("users") as JSONArray).getJSONObject(0) //return the first object
            //(json.get("users") as JSONArray).getJSONObject(0).getString("userName") //return the username of a first object
            //(json.get("users") as JSONArray).length() //length of an object

            len = (json.get("list") as JSONArray).length()
            for (i in 0..len - 1) {
                var email = (json.get("list") as JSONArray).getJSONObject(i).getString("email")
                emailList.add(email)
            }
            for (i in 0..len - 1) {
                var id = (json.get("list") as JSONArray).getJSONObject(i).getInt("id")
                userId.add(id)
            }
            //balance
            for (i in 0..len - 1) {
                var bal = (json.get("list") as JSONArray).getJSONObject(i).getInt("balance")
                balance.add(bal)
            }
            //holdingBalance
            for (i in 0..len - 1) {
                var holdBal = (json.get("list") as JSONArray).getJSONObject(i).getInt("holdingBalance")
                holdingBalance.add(holdBal)
            }
            //println(nameList)
            for (i in 0..len - 1) {
                var name = (json.get("list") as JSONArray).getJSONObject(i).getString("password")
                passList.add(name)
            }
        }
    }

    return len
}


fun checkUser(userEmail: String, userPass: String): Boolean {

    var len = getUserInfo()

    for (i in 0..len - 1) {
        if (userEmail == emailList[i] && userPass == passList[i]) {
            Log.d("Success", "Login successful")
            curUser = userId[i]
            curBalance = balance[i]
            curHoldBalance = holdingBalance[i]
            return true
        } else {
            Log.d("Error", "Incorrect pass or username")
        }
    }
    return false
}

fun currentUser(): UserInfo {
    var id = curUser
    var balance = curBalance
    var holdingBalance = curHoldBalance
    return UserInfo(id, balance, holdingBalance)
}

data class UserInfo(val id: Int, val balance: Int, val holdingBalance: Int)

