package com.example.onelabretrofitapi.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.example.onelabretrofitapi.R
import com.example.onelabretrofitapi.databinding.ItemCharacterBinding
import com.example.onelabretrofitapi.presentation.model.Character

class CharacterPagingAdapter(
    private val onItemClick: ((Int) -> Unit),
    private val onIconClick: ((Character) -> Unit)
):
    PagingDataAdapter<Character, CharacterPagingAdapter.CharacterViewHolder>(DIFF_CALLBACK) {
    class CharacterViewHolder(
        private val binding: ItemCharacterBinding,
        private val onItemClick: (Int) -> Unit,
        private val onIconClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick.invoke(bindingAdapterPosition)
            }
            binding.rate.setOnClickListener {
                onIconClick.invoke(bindingAdapterPosition)
                binding.rate.setImageResource(R.drawable.ic_baseline_star)
            }
        }

        fun bind(character: com.example.onelabretrofitapi.presentation.model.Character) {
            with(binding) {
                name.text = character.name
                species.text = character.species

                img.load(character.image) {
                    crossfade(true)
                    placeholder(R.drawable.ic_launcher_background)
                    scale(Scale.FIT)
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<com.example.onelabretrofitapi.presentation.model.Character>() {
            override fun areItemsTheSame(oldItem: com.example.onelabretrofitapi.presentation.model.Character, newItem: com.example.onelabretrofitapi.presentation.model.Character): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: com.example.onelabretrofitapi.presentation.model.Character, newItem: com.example.onelabretrofitapi.presentation.model.Character): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(layoutInflater, parent, false)
        return CharacterViewHolder(binding, onIconClick = {
            getItem(it)?.let { it1 -> onIconClick(it1) }
        }, onItemClick = {
            getItem(it)?.id?.let {
                    it1 -> onItemClick(it1)
            }
        })
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}