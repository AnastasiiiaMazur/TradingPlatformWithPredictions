package com.example.fyp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class SignUpPage : AppCompatActivity() {

    var submit_button: Button? = null
    var user_name: EditText? = null
    var user_email: EditText? = null
    var user_pass: EditText? = null
    var user_check_pass: EditText? = null
    var error_message: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_page)

        submit_button = findViewById(R.id.submit_button)
        user_name = findViewById(R.id.name)
        user_email = findViewById(R.id.email)
        user_pass = findViewById(R.id.pass)
        user_check_pass = findViewById(R.id.pass_confirm)
        error_message = findViewById(R.id.error_message)

        submit_button?.setOnClickListener {
            val i = Intent(this, LogInPage::class.java)
            var pass = user_pass!!.text.toString().trim()
            var pass_confirm = user_check_pass!!.text.toString().trim()
            var user_name = user_name!!.text.toString().trim()
            var user_email = user_email!!.text.toString().trim()

            if (pass == pass_confirm){
                //val json = "{ \"userPass\": \"$user_pass\", \"userBalance\": 1000, \"userEmail\": \"$user_email\", \"userName\": \"$user_name\" }"
                addNewUser(user_name, user_email, pass)
                startActivity(i)
            } else {
                Log.d("Error message", "Passwords do not match")
                error_message?.setText("Passwords do not match")
            }
        }
    }
}