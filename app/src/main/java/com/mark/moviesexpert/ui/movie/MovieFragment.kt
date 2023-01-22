package com.mark.moviesexpert.ui.movie

import RvAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mark.moviesexpert.MovieViewModel
import com.mark.moviesexpert.databinding.FragmentMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {
    lateinit var binding: FragmentMovieBinding
    val viewModel: MovieViewModel by viewModels()

    val movieAdapter = MoviePagingAdapter()
    lateinit var genresAdapter :RvAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setQuery("")
        viewModel.getMoviesGeneres()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setRecyclerView()

        binding.movieSearch.setOnQueryTextListener(object :androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.setQuery(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })


        viewModel.list.observe(viewLifecycleOwner) {
            movieAdapter.submitData(lifecycle, it)
        }
        viewModel.movieGeneres.observe(viewLifecycleOwner) {
               genresAdapter = RvAdapter(it.peekContent().data!!.genres)
            setGenreRecyclerView()

        }
        movieAdapter.onMovieClick {
            println("marsk ${it}")
            val action = MovieFragmentDirections.actionMovieFragmentToDetailsFragment()
            action.id=it
            viewModel.getMovieDetails(it)
            findNavController().navigate(action)
        }

        viewModel.list.observe(viewLifecycleOwner) {
            movieAdapter.submitData(lifecycle, it)
        }

    }

    private fun setRecyclerView() {
        binding.movieRecycler.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }


    }
    private fun setGenreRecyclerView() {

        binding.generes_recycler.apply{
            adapter = genresAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }


    }


}