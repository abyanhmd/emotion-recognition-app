package com.example.emotionly.model.bottomnav

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.emotionly.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

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

        binding.tvName.text = "Daniel Santoso"
        binding.tvEmail.text = "danielsantoso@gmail.com"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}