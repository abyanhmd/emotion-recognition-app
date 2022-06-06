package com.example.emotionly

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.emotionly.api.TokenPref
import com.example.emotionly.databinding.ActivityMainBinding
import com.example.emotionly.model.home.HomeActivity
import com.example.emotionly.model.login.LoginActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var tokenPref: TokenPref

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.tvUsspeak.setTypeface(null, Typeface.BOLD)
        tokenPref = TokenPref(this)

        Handler(Looper.getMainLooper()).postDelayed({
            if(tokenPref.getToken() == "") {
                val loginIntent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(loginIntent)
                finish()
            } else {
                val homeIntent = Intent(this@MainActivity, HomeActivity::class.java)
                startActivity(homeIntent)
                finish()
            }
        }, 3000)
    }
}