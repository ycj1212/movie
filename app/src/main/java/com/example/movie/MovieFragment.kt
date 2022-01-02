package com.example.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.movie.databinding.FragmentMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {
    private lateinit var binding: FragmentMovieBinding
    private lateinit var adapter: MovieAdapter
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadData()

        adapter = MovieAdapter(this, resources.configuration.orientation)
        binding.vpMovieInfo.apply {
            adapter = adapter
            offscreenPageLimit = 1
            clipChildren = false
            setPageTransformer(MarginPageTransformer(30.dpToPx(resources.displayMetrics)))
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
        binding.indicator.setViewPager(binding.vpMovieInfo)

        viewModel.movieList.observe(viewLifecycleOwner) { movieList ->
            adapter.submitList(movieList)
            binding.indicator.createIndicators(adapter.itemCount, 0)
        }
    }
}
