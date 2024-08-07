package com.solux.flory.presentation.gift.send

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.solux.flory.databinding.ItemSelectBouquetBinding

class BouquetAdapter(
    private val onClick: (BouquetInfo) -> Unit,
) : ListAdapter<BouquetInfo, BouquetViewHolder>(DiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BouquetViewHolder {
        return BouquetViewHolder(
            ItemSelectBouquetBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onClick
        )
    }

    override fun onBindViewHolder(holder: BouquetViewHolder, position: Int) {
        holder.bind(position, getItem(position))
    }

    companion object {
        private val DiffUtil = object : DiffUtil.ItemCallback<BouquetInfo>() {
            override fun areItemsTheSame(oldItem: BouquetInfo, newItem: BouquetInfo): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: BouquetInfo, newItem: BouquetInfo): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}

