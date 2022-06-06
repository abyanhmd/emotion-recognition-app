package com.example.emotionly.model.signup

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.emotionly.api.ApiConfig
import com.example.emotionly.databinding.ActivitySignupBinding
import com.example.emotionly.model.login.LoginActivity
import com.example.emotionly.response.RegisterResponse
import com.example.emotionly.response.UserRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.titleSignup.setTypeface(null, Typeface.BOLD)

        binding.btnSignup.setOnClickListener {
            val request = UserRequest()
            request.name = binding.edtName.text.toString().trim()
            request.email = binding.edtEmail.text.toString().trim()
            request.password = binding.edtPassword.text.toString().trim()
            ApiConfig.getApiService().registerUser(request)
                .enqueue(object : Callback<RegisterResponse> {
                    override fun onResponse(
                        call: Call<RegisterResponse>,
                        response: Response<RegisterResponse>
                    ) {
                        if (response.isSuccessful) {
                            Toast.makeText(
                                this@SignupActivity,
                                "Successfully created an account",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(this@SignupActivity, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this@SignupActivity,
                                response.message().toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                        Toast.makeText(this@SignupActivity, t.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                })
        }
        playAnimation()
    }
    private fun playAnimation(){
        val name = ObjectAnimator.ofFloat(binding.layoutName, View.ALPHA, 1F ).setDuration(500)
        val email = ObjectAnimator.ofFloat(binding.layoutEmail, View.ALPHA, 1F).setDuration(500)
        val password = ObjectAnimator.ofFloat(binding.layoutPassword, View.ALPHA, 1F).setDuration(500)
        val confirm = ObjectAnimator.ofFloat(binding.layoutConfirmPassword, View.ALPHA, 1F).setDuration(500)

        AnimatorSet().apply {
            playTogether(name,email, password,confirm)
            startDelay = (500)
        }.start()

        ObjectAnimator.ofFloat(binding.btnSignup, View.ALPHA, 1F).apply{
            duration = 1200
            startDelay = (1200)

        }.start()

    }
}