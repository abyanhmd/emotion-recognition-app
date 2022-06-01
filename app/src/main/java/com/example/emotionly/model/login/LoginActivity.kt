package com.example.emotionly.model.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.emotionly.api.ApiConfig
import com.example.emotionly.api.TokenPref
import com.example.emotionly.databinding.ActivityLoginBinding
import com.example.emotionly.model.bottomnav.HomeActivity
import com.example.emotionly.model.signup.SignupActivity
import com.example.emotionly.response.LoginResponse
import com.example.emotionly.response.UserRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.titleLogin.setTypeface(null, Typeface.BOLD)

        binding.btnSignup.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignupActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
            showLoading(true)
            val request = UserRequest()
            request.email = binding.edtEmail.text.toString().trim()
            request.password = binding.edtPassword.text.toString().trim()
            ApiConfig.getApiService().loginUser(request).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    showLoading(false)
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Successfully login",
                            Toast.LENGTH_SHORT
                        ).show()
                        val token = TokenPref(this@LoginActivity)
                        token.setToken(response.body()!!.token)
                        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "Failed to login",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    showLoading(false)
                    Toast.makeText(
                        this@LoginActivity,
                        "Failed to login",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
        playAnimation()
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun playAnimation(){
        val email = ObjectAnimator.ofFloat(binding.layoutEmail, View.ALPHA, 1F).setDuration(500)
        val password = ObjectAnimator.ofFloat(binding.layoutPassword, View.ALPHA, 1F).setDuration(500)
        val login = ObjectAnimator.ofFloat(binding.btnLogin, View.ALPHA, 1F).setDuration(500)
        val question = ObjectAnimator.ofFloat(binding.tvAcc, View.ALPHA, 1F).setDuration(500)

        AnimatorSet().apply{
            playTogether(email,password,login,question)
            startDelay = (500)
        }.start()

        ObjectAnimator.ofFloat(binding.btnSignup, View.ALPHA, 1F).apply{
            duration = 1200
            startDelay = (1200)

        }.start()
    }

}