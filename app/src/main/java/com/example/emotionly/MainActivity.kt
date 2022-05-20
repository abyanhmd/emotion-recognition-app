package com.example.emotionly

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.emotionly.databinding.ActivityMainBinding
import com.example.emotionly.model.bottomnav.HomeActivity
import com.example.emotionly.model.login.LoginActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.tvUsspeak.setTypeface(null, Typeface.BOLD)

        Handler(Looper.getMainLooper()).postDelayed({
            val loginIntent = Intent(this@MainActivity, HomeActivity::class.java)
            startActivity(loginIntent)
            finish()
        }, 3000)

    }
}