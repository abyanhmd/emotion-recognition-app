package com.example.emotionly.model.bottomnav

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
import com.example.emotionly.adapter.History
import com.example.emotionly.adapter.HistoryAdapter
import com.example.emotionly.api.ApiConfig
import com.example.emotionly.api.TokenPref
import com.example.emotionly.databinding.FragmentHistoryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHistoryBinding.inflate(layoutInflater)
        val context: Context = this.context ?: return
        val token = TokenPref(context)
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

        ApiConfig.getApiService().getHistory().enqueue(object : Callback<History> {
            override fun onResponse(call: Call<History>, response: Response<History>) {
                if(response.isSuccessful) {
                    if(response.body() != null) {
                        setHistory(response.body()!!)
                    }
                } else {
                    Log.e(TAG, "isNotSuccessful: ${response.body()}")
//                    Toast.makeText(HomeActivity(), "Failed to load history", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<History>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
//                Toast.makeText(HomeActivity(), "Failed to load history", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setHistory(data: History) {
        val listHistory = ArrayList<History>()
        val emotion = History(
            data.emotion,
            data.duration,
            data.date
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