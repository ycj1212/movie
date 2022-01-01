package com.example.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movie.databinding.FragmentMoviePosterBinding

class MoviePosterFragment : Fragment() {
    private lateinit var binding: FragmentMoviePosterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviePosterBinding.inflate(inflater, container, false)
        return binding.root
    }
}
