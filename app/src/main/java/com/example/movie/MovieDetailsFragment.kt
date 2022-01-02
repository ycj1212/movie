package com.example.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movie.data.MovieInfo
import com.example.movie.databinding.FragmentMovieDetailsBinding

class MovieDetailsFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieInfo = arguments?.getParcelable<MovieInfo>(KEY) ?: return

        binding.tvMovieTitle.text = movieInfo.title
        binding.tvMovieRating.text = "${movieInfo.certification}세 이상"
        binding.tvMovieRating.apply {
            text = "${movieInfo.certification}세 이상"
            when (movieInfo.certification) {
                "7" -> setBackgroundResource(R.drawable.bg_round_rect_blue)
                "12" -> setBackgroundResource(R.drawable.bg_round_rect_green)
                "15" -> setBackgroundResource(R.drawable.bg_round_rect_yellow)
                "19" -> setBackgroundResource(R.drawable.bg_round_rect_red)
                else -> {
                    text = "전체관람가"
                    setBackgroundResource(R.drawable.bg_round_rect_blue)
                }
            }
        }
        binding.tvMovieAdditionalInfo.text =
            "${movieInfo.releaseDate} / ${movieInfo.genre.first()} / ${movieInfo.runTime}분"
        binding.tvMovieInfo.text = movieInfo.overview
        binding.tvMovieCast.text =
            movieInfo.casts.map { "${it.name} - ${it.character}" }.reduce { acc, s -> "$acc\n$s" }
    }

    companion object {
        const val KEY = "MovieDetailsFragment"

        fun newInstance(data: MovieInfo) = MovieDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY, data)
            }
        }
    }
}
