package com.solux.flory.presentation.date

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.solux.flory.databinding.ItemDateBinding

class DateAdapter(
    private val onClick: (DateInfo) -> Unit,
) : ListAdapter<DateInfo, DateViewHolder>(DiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        return DateViewHolder(
            ItemDateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onClick
        )
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DiffUtil = object : DiffUtil.ItemCallback<DateInfo>() {
            override fun areItemsTheSame(oldItem: DateInfo, newItem: DateInfo): Boolean {
                return oldItem.dayOfMonth == newItem.dayOfMonth
            }

            override fun areContentsTheSame(oldItem: DateInfo, newItem: DateInfo): Boolean {
                return oldItem == newItem
            }
        }
    }
}