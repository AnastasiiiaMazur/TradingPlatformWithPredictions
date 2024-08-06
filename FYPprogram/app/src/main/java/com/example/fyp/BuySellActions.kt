package com.example.fyp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.security.cert.X509Certificate
import javax.net.ssl.*


fun buyProcess(amount: Int, currency: String){
    //connection()

    var user = currentUser()
    var id = user.id

    val url = "https://trading-app-papi.us-e2.cloudhub.io/api/user/$id/positions"

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

    // "userBalance": "1000",
    val json = """
        {
          "qType": "buy",
          "amount": $amount,
          "currency": "$currency"
        }
    """.trimIndent()

    runBlocking {
        withContext(Dispatchers.IO) {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.doOutput = true
            connection.setRequestProperty("Content-Type", "application/json")
            val writer = OutputStreamWriter(connection.outputStream)
            writer.write(json)
            writer.flush()
            val responseCode = connection.responseCode
            println("Response Code : $responseCode")

            val response = connection.inputStream.bufferedReader().use { it.readText() }
            println("Response Body : $response")
        }
    }
}

fun sellProcess(amount: Int, currency: String){
    //connection()

    var user = currentUser()
    var id = user.id

    val url = "https://trading-app-papi.us-e2.cloudhub.io/api/user/$id/positions"

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

    // "userBalance": "1000",
    val json = """
        {
          "qType": "sell",
          "amount": $amount,
          "currency": "$currency"
        }
    """.trimIndent()

    runBlocking {
        withContext(Dispatchers.IO) {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.doOutput = true
            connection.setRequestProperty("Content-Type", "application/json")
            val writer = OutputStreamWriter(connection.outputStream)
            writer.write(json)
            writer.flush()
            val responseCode = connection.responseCode
            println("Response Code : $responseCode")

            val response = connection.inputStream.bufferedReader().use { it.readText() }
            println("Response Body : $response")
        }
    }
}

fun closeProcess(position: Int){
    //connection()

    var user = currentUser()
    var id = user.id

    var url = "https://trading-app-papi.us-e2.cloudhub.io/api/user/$id/positions/$position/close"

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

    runBlocking {
        withContext(Dispatchers.IO) {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.requestMethod = "PUT"
            connection.doOutput = true
            connection.setRequestProperty("Content-Type", "application/json")

            val responseCode = connection.responseCode
            println("Response Code : $responseCode")

            val response = connection.inputStream.bufferedReader().use { it.readText() }
            println("Response Body : $response")
        }
    }
}