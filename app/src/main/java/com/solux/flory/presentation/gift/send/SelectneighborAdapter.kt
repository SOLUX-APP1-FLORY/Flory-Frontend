package com.solux.flory.presentation.gift.send

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.solux.flory.databinding.ItemGiftSelectneighborsBinding
import com.solux.flory.presentation.profile.NeighborInfo

class SelectneighborAdapter(
    private val onNeighborClick: (NeighborInfo) -> Unit
) : ListAdapter<NeighborInfo, SelectneighborViewHolder>(DiffUtil) {
    private var selectedNeighbor: NeighborInfo? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectneighborViewHolder {
        return SelectneighborViewHolder(
            ItemGiftSelectneighborsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ) { neighborInfo, isChecked ->
            if (isChecked) {
                selectedNeighbor = neighborInfo
            } else if (selectedNeighbor == neighborInfo) {
                selectedNeighbor = null
            }
            notifyDataSetChanged()
            onNeighborClick(neighborInfo)
        }
    }

        override fun onBindViewHolder(holder: SelectneighborViewHolder, position: Int) {
            val neighborInfo = getItem(position)
            holder.bind(neighborInfo, neighborInfo == selectedNeighbor)
        }

        fun getSelectedNeighbor(): NeighborInfo? {
            return selectedNeighbor
        }

        companion object {
            private val DiffUtil = object : DiffUtil.ItemCallback<NeighborInfo>() {
                override fun areItemsTheSame(oldItem: NeighborInfo, newItem: NeighborInfo): Boolean {
                    return oldItem.profileName == newItem.profileName
                }

                override fun areContentsTheSame(oldItem: NeighborInfo, newItem: NeighborInfo): Boolean {
                    return oldItem == newItem
                }
            }
        }
}