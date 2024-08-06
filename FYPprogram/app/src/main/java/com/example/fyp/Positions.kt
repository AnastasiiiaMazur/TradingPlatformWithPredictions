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

var positions = mutableListOf<JSONObject>()

fun getPositionsInfo(): MutableList<JSONObject> {
    connection()

    positions = mutableListOf<JSONObject>()

    var id = currentUser()
    var url = "https://trading-app-papi.us-e2.cloudhub.io/api/user/${id.id}/positions"

    //(json.get("users") as JSONArray).getJSONObject(0)

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
            current_json = json

            len = (json.get("list") as JSONArray).length()


            for (i in 0..len - 1) {
                var obj = (json.get("list") as JSONArray).getJSONObject(i)
                positions.add(obj)
            }


        }
    }

    return positions
}