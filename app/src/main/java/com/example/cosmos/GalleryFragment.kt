package com.example.cosmos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.example.cosmos.adapters.PhotoAdapter
import com.example.cosmos.databinding.FragmentGalleryBinding
import com.example.cosmos.viewmodels.PhotosViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private val adapter = PhotoAdapter()
    private val viewModel: PhotosViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentGalleryBinding.inflate(inflater, container, false)
        context ?: return binding.root

        binding.photoList.adapter = adapter
        subscribeUi()

        return binding.root
    }

    private fun subscribeUi() {
        lifecycleScope.launch {
            viewModel.photos.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }
    }
}
