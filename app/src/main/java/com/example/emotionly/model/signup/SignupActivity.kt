package com.example.emotionly.model.signup

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
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
                                "Successfully created an acount",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(this@SignupActivity, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this@SignupActivity,
                                "Failed to sign up",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                        Toast.makeText(this@SignupActivity, "Failed to sign up", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
        }
    }
}