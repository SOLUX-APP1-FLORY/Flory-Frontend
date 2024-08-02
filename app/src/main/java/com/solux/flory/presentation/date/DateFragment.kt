package com.solux.flory.presentation.date

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.solux.flory.R
import com.solux.flory.databinding.FragmentDateBinding
import com.solux.flory.util.base.BindingFragment
import com.solux.flory.util.fragment.toast
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class DateFragment : BindingFragment<FragmentDateBinding>(FragmentDateBinding::inflate) {
    private val viewModel by viewModels<DateViewModel>()
    private lateinit var adapter: DateAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        observeDateYear()
        observeDateMonth()
        leftArrowClick()
        rightArrowClick()
        addFabClick()
    }

    private fun initAdapter() {
        adapter = DateAdapter() {
            val isToday = isToday(it)
            val hasImage = it.imageUrl != null

            when {
                isToday && hasImage -> navigateToModify(it)
                isToday -> findNavController().navigate(R.id.action_fragment_date_to_fragment_record)
                hasImage -> navigateToModify(it)
                else -> toast("이날 기록한 일기가 없습니다.")
            }
        }
        binding.rvDate.adapter = adapter
        viewModel.dateList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun isToday(dateInfo: DateInfo): Boolean {
        val now = LocalDate.now()
        return now.monthValue == dateInfo.month && now.dayOfMonth == dateInfo.dayOfMonth
    }

    private fun navigateToModify(dateInfo: DateInfo) {
        val bundle = Bundle().apply {
            putSerializable(DATE_KEY, dateInfo)
        }
        findNavController().navigate(R.id.action_fragment_date_to_fragment_modify, bundle)
    }

    private fun observeDateYear() {
        viewModel.year.observe(viewLifecycleOwner) {
            binding.tvDateYear.text = it
        }
    }

    private fun observeDateMonth() {
        viewModel.month.observe(viewLifecycleOwner) {
            binding.tvDateMonth.text = it
        }
    }

    private fun leftArrowClick() {
        binding.ivDateLeftArrow.setOnClickListener {
            viewModel.moveToPreviousMonth()
        }
    }

    private fun rightArrowClick() {
        binding.ivDateRightArrow.setOnClickListener {
            viewModel.moveToNextMonth()
        }
    }

    private fun addFabClick() {
        binding.fabDateAdd.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_date_to_fragment_record)
        }
    }

    companion object {
        const val DATE_KEY = "date"
    }

}