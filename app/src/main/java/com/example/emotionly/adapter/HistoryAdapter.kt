package com.example.emotionly.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.emotionly.databinding.RowHistoryBinding

class HistoryAdapter(private val listHistory: ArrayList<History>, val context: Context?) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    class ViewHolder(binding: RowHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        val emotion = binding.tvEmotion
        val duration = binding.tvDuration
        val date = binding.tvDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.emotion.text = listHistory[position].emotion
        holder.duration.text = listHistory[position].duration
        holder.date.text = listHistory[position].date
    }

    override fun getItemCount(): Int = listHistory.size
}