package com.example.fyp


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.security.cert.X509Certificate
import javax.net.ssl.*


fun addNewUser(firstName: String, email: String, password: String) {
    val url = "https://trading-app-papi.us-e2.cloudhub.io/api/users"
    //https://localhost:8081/api/users

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
            "email": "$email",
            "firstname": "$firstName",
            "lastname": "",
            "password": "$password"
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