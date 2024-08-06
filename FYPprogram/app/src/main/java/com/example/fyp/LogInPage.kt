package com.example.fyp

//kit
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.sql.DriverManager
import kotlin.concurrent.thread

class LogInPage : AppCompatActivity() {

    var submit_button: Button? = null
    var text_username: EditText? = null
    var text_pass: EditText? = null
    var error_message: TextView? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in_page)

        submit_button = findViewById(R.id.submit_button)
        text_username = findViewById(R.id.email)
        text_pass = findViewById(R.id.pass)
        error_message = findViewById(R.id.message)

        submit_button?.setOnClickListener {
            val i = Intent(this, MainPage::class.java)
            var name = text_username!!.text.toString().trim()
            var pass = text_pass!!.text.toString().trim()

            if (checkUser(name, pass)){
                error_message?.setText("")
                Log.d("Test", "Button pressed, data is correct, need to start activity")
                startActivity(i)
            } else {
                Log.d("Error message", "Incorect password or email")
                error_message?.setText("Incorect email or password!")
            }
        }
    }
}


//    fun main() {
//        val url = "jdbc:mysql://phpmyadmin.ecs.westminster.ac.uk:3306/w1720335*0" // replace "mydatabase" with your database name
//        val username = "w1720335" // replace with your MySQL username
//        val password = "S3VFeCsnTqY5" // replace with your MySQL password
//        runBlocking {
//            launch {
//                try  // initialise the JDBC driver, with a check for it is working
//                {
//                    Class.forName("com.mysql.cj.jdbc.Driver")
//                    println("ALL GOOD!!!")
//                } catch (e: ClassNotFoundException) {
//                    println("ERROR: MySQL JDBC Driver not found; is your CLASSPATH set?")
//                    e.printStackTrace()
//                    //return
//                }
//                //Class.forName("com.mysql.cj.jdbc.Driver")
//                // Connect to the MySQL database
//                val conn = DriverManager.getConnection(url, username, password)
//
//                // Do something with the database connection, e.g. execute a query
//                val stmt = conn.createStatement()
//                val rs =
//                    stmt.executeQuery("SELECT * FROM Users") // replace "mytable" with your table name
//
//                // Print the results of the query
//                while (rs.next()) {
//                    println(rs.getString("column1") + "\t" + rs.getString("column2"))
//                    // replace "column1" and "column2" with the names of the columns in your table
//                }
//
//                // Close the database connection
//                conn.close()
//            }
//        }
//    }