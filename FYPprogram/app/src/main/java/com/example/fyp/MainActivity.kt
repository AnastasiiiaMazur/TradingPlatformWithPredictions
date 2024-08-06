package com.example.fyp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    var signup_button: Button? = null
    var login_button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        signup_button = findViewById<Button>(R.id.signup_button)
        login_button = findViewById<Button>(R.id.login_button)

        signup_button?.setOnClickListener {
            val i = Intent(this, SignUpPage::class.java)
            startActivity(i)
        }

        login_button?.setOnClickListener {
            val i = Intent(this, LogInPage::class.java)
            startActivity(i)
        }
    }
}