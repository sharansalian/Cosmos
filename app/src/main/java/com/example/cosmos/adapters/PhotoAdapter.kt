package com.example.cosmos.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cosmos.GalleryFragmentDirections
import com.example.cosmos.data.Photo
import com.example.cosmos.databinding.ListItemPhotoBinding


class PhotoAdapter : ListAdapter<Photo, RecyclerView.ViewHolder>(PhotoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PhotoViewHolder(
            ListItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val photo = getItem(position)
        (holder as PhotoViewHolder).bind(photo)
    }

    class PhotoViewHolder(
        private val binding: ListItemPhotoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.photo?.let { photo ->

                    navigateToPhotoDetail(photo, it)
                }
            }
        }

        private fun navigateToPhotoDetail(
            photo: Photo,
            view: View
        ) {
            val direction =
                GalleryFragmentDirections.actionGalleryFragmentToPhotoDetailFragment(
                    photo.id.toLong()
                )
            view.findNavController().navigate(direction)
        }

        fun bind(item: Photo) {
            binding.apply {
                photo = item
                executePendingBindings()
            }
        }
    }
}

private class PhotoDiffCallback : DiffUtil.ItemCallback<Photo>() {

    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }
}
