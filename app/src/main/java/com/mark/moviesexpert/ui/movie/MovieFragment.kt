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
import com.mark.moviesexpert.data.models.Genre
import com.mark.moviesexpert.databinding.FragmentMovieBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieFragment : Fragment() {
    lateinit var binding: FragmentMovieBinding
    val viewModel: MovieViewModel by viewModels()

    val movieAdapter = MoviePagingAdapter()
    lateinit var genresAdapter: RvAdapter


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

        binding.movieSearch.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.setQuery("search $it")
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
            if (it.peekContent().data?.genres != null) {

                var genresList = it.peekContent().data!!.genres
                var allGenre: Genre = Genre(0, "All")

                genresList.toMutableList().add(0, allGenre)

                val listofgenres: MutableList<Genre> = mutableListOf()
                listofgenres.add(0, allGenre)
                genresList.map {
                    listofgenres.add(it)
                }
                genresAdapter = listofgenres.let { it1 -> RvAdapter(listofgenres) }
                setGenreRecyclerView()

                genresAdapter.onGenreCLick {
                    if (it == "0") {
                        viewModel.setQuery("")
                    } else {
                        viewModel.setQuery(it)
                    }
                }


            }


        }
        movieAdapter.onMovieClick {
            val action = MovieFragmentDirections.actionMovieFragmentToDetailsFragment()
            action.id = it
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
        binding.generesRecycler.apply {
            adapter = genresAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,true)
        }
        binding.generesRecycler.refreshDrawableState()
         binding.generesRecycler.adapter = genresAdapter

    }


}