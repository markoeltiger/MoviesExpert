package com.mark.moviesexpert.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mark.moviesexpert.MovieViewModel
import com.mark.moviesexpert.databinding.FragmentDetailsBinding
import com.mark.moviesexpert.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    lateinit var binding: FragmentDetailsBinding

    val args: DetailsFragmentArgs by navArgs()

    val viewModel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.backPress.setOnClickListener {
            findNavController().popBackStack()
        }
        args.id?.let {
            println("argsId ${it}")
            viewModel.getMovieDetails(it)
        }

        viewModel.movieDetails.observe(viewLifecycleOwner) {
            when (it.getContentIfNotHandled()?.status) {

                Status.LOADING -> {

                    binding.detailsProgress.visibility = View.VISIBLE

                }
                Status.ERROR -> {
                    binding.detailsProgress.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    binding.detailsProgress.visibility = View.GONE

                    binding.movieDetails = it.peekContent().data

                }

            }
        }

    }
}