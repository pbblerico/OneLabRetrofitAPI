package com.example.onelabretrofitapi.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.example.onelabretrofitapi.R
import com.example.onelabretrofitapi.databinding.ItemCharacterBinding
import com.example.onelabretrofitapi.presentation.model.Character

class CharacterAdapter(
    private val onItemClick: ((Int) -> Unit), private val onIconClick: ((Character) -> Unit)
) : ListAdapter<Character, CharacterAdapter.CharacterViewHolder>(DIFF_CALLBACK) {

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
            }
        }

        fun bind(character: Character) {
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
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem == newItem
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(layoutInflater, parent, false)
        return CharacterViewHolder(binding, onIconClick = {
            onIconClick(getItem(it))
        }, onItemClick = {
            getItem(it).id?.let { it1 -> onItemClick(it1) }
        })
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}