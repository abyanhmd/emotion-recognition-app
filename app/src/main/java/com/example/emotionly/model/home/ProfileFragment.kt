package com.example.emotionly.model.home

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.media.session.MediaSession
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.auth0.android.jwt.JWT
import com.bumptech.glide.Glide
import com.example.emotionly.R
import com.example.emotionly.api.ApiConfig
import com.example.emotionly.api.TokenPref
import com.example.emotionly.databinding.FragmentProfileBinding
import com.example.emotionly.model.login.LoginActivity
import com.example.emotionly.response.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var tokenPref: TokenPref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvName.setTypeface(null, Typeface.BOLD)
        binding.tvEmail.setTypeface(null, Typeface.NORMAL)

        val context: Context = this.context ?: return
        tokenPref = TokenPref(context)
        val token = tokenPref.getToken()
        val userId = tokenPref.getUserId()

        showLoading(true)

        ApiConfig.getApiService().getUser("Bearer $token", userId).enqueue(object: Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                showLoading(false)
                if(response.isSuccessful) {
                    Glide.with(context)
                        .load(response.body()?.avatar)
                        .placeholder(R.drawable.placeholder_circle)
                        .error(R.drawable.placeholder_circle)
                        .into(binding.imgPhoto)

                    binding.tvName.text = response.body()!!.name
                    binding.tvEmail.text = response.body()!!.email
                } else {
                    Log.e(TAG, "isNotSuccessful: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                showLoading(false)
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })

        binding.progressBar.visibility = View.GONE
    }

    private fun showLoading(state: Boolean) {
        if(state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val TAG = "ProfileFragment"
    }
}