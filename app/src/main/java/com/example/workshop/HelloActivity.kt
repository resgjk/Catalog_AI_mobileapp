package com.example.workshop

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HelloActivity : AppCompatActivity() {
    private var nextBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hello_activity)

        nextBtn = findViewById<Button>(R.id.nextBtn).apply {
            setOnClickListener {
                val intent = Intent(this@HelloActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}