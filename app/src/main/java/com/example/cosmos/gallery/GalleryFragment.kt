package com.example.cosmos.gallery

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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private val adapter = PhotoAdapter()
    private val viewModel: GalleryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentGalleryBinding.inflate(inflater, container, false)
        context ?: return binding.root

        binding.photoList.adapter = adapter
        subscribeUi(binding)

        return binding.root
    }

    private fun subscribeUi(binding: FragmentGalleryBinding) {
        lifecycleScope.launch {
            viewModel.photos.observe(viewLifecycleOwner) { photos ->
                binding.hasPhotos = !photos.isNullOrEmpty()
                adapter.submitList(photos)
            }
        }
    }
}
