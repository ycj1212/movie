package com.example.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageUrl = arguments?.getString(KEY) ?: return

        Glide.with(this)
            .load(imageUrl)
            .into(binding.ivMoviePoster)
    }

    companion object {
        const val KEY = "MoviePosterFragment"

        fun newInstance(data: String) = MoviePosterFragment().apply {
            arguments = Bundle().apply {
                putString(KEY, data)
            }
        }
    }
}
