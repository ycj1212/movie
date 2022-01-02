package com.example.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movie.data.MovieFragmentState
import com.example.movie.data.MovieInfo
import com.example.movie.databinding.FragmentMovieInfoBinding

class MovieInfoFragment : Fragment() {
    private lateinit var binding: FragmentMovieInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentState = arguments?.getParcelable<MovieFragmentState>(KEY1) ?: return
        val movieInfo = arguments?.getParcelable<MovieInfo>(KEY2) ?: return

        when (fragmentState) {
            MovieFragmentState.POSTER -> {
                val moviePosterFragment = MoviePosterFragment.newInstance(movieInfo.imageUrl)
                childFragmentManager.beginTransaction()
                    .replace(R.id.fcv_movie_info, moviePosterFragment).commit()
            }
            MovieFragmentState.DETAILS -> {
                val movieDetailsFragment = MovieDetailsFragment.newInstance(movieInfo)
                childFragmentManager.beginTransaction()
                    .replace(R.id.fcv_movie_info, movieDetailsFragment).commit()
            }
            MovieFragmentState.BOTH -> {
                val moviePosterFragment = MoviePosterFragment.newInstance(movieInfo.imageUrl)
                val movieDetailsFragment = MovieDetailsFragment.newInstance(movieInfo)
                childFragmentManager.beginTransaction()
                    .add(R.id.fcv_movie_poster, moviePosterFragment)
                    .add(R.id.fcv_movie_details, movieDetailsFragment)
                    .commit()
            }
        }
    }

    companion object {
        const val KEY1 = "MovieInfoFragment1"
        const val KEY2 = "MovieInfoFragment2"

        fun newInstance(data1: MovieFragmentState, data2: MovieInfo) = MovieInfoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY1, data1)
                putParcelable(KEY2, data2)
            }
        }
    }
}
