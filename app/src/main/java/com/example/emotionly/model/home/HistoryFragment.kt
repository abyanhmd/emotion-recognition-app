package com.example.emotionly.model.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emotionly.R
import com.example.emotionly.adapter.HistoryAdapter
import com.example.emotionly.api.ApiConfig
import com.example.emotionly.api.TokenPref
import com.example.emotionly.databinding.FragmentHistoryBinding
import com.example.emotionly.response.HistoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHistoryBinding.inflate(layoutInflater)
        binding.rvHistory.layoutManager = LinearLayoutManager(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context: Context = this.context ?: return
        val token = TokenPref(context).getToken()

        ApiConfig.getApiService().getHistory("Bearer $token").enqueue(object : Callback<HistoryResponse> {
            override fun onResponse(call: Call<HistoryResponse>, response: Response<HistoryResponse>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val data = response.body()
                        val listHistory = HistoryResponse(
                            data?.emotion,
                            data?.dateTaken ?: Date(),
                            data?.duration ?: "",
                            data?.suggestion,
                            data?.fileName ?: "",
                            data?.audioFile ?: ""
                        )
                        setHistory(listHistory)
                    }
                } else {
                    Log.e(TAG, "isNotSuccessful: ${response.errorBody()}")
                    Toast.makeText(getContext(), "Failed to load history", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<HistoryResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
                Toast.makeText(getContext(), "Failed to load history", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setHistory(data: HistoryResponse) {
        val listHistory = ArrayList<HistoryResponse>()

        val emotion = HistoryResponse(
            data.emotion,
            data.dateTaken,
            data.duration,
            data.suggestion,
            data.fileName,
            data.audioFile
        )
        listHistory.add(emotion)

        val mContext = context
        val adapter = HistoryAdapter(listHistory, mContext)
        binding.rvHistory.adapter = adapter
    }

    companion object {
        const val TAG = "HistoryFragment"
    }
}