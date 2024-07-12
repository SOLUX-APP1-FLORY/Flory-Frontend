package com.solux.flory.presentation.profile

import android.app.Dialog
import android.os.Bundle
import android.view.View
import coil.load
import coil.transform.CircleCropTransformation
import com.solux.flory.R
import com.solux.flory.databinding.FragmentFollowcancelDialogBinding
import com.solux.flory.util.base.BindingDialogFragment

class NeighborDialogFragment(
    private val neighborInfo: NeighborInfo
) : BindingDialogFragment<FragmentFollowcancelDialogBinding>(FragmentFollowcancelDialogBinding::inflate) {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext(), R.style.CustomDialogTheme)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        confirmBtnClick()
        cancelBtnClick()
    }

    private fun initView() {
        binding.ivNeighborImage.load(neighborInfo.profileImage) {
            transformations(CircleCropTransformation())
        }
        binding.tvNeighborNickname.text = neighborInfo.profileName
        binding.tvNeighborNicknameInfo.text = neighborInfo.profileName
    }

    private fun confirmBtnClick() {
        binding.btnYes.setOnClickListener {
            dismiss()
            //팔로우 끊기 기능 구현
        }
    }

    private fun cancelBtnClick() {
        binding.btnNo.setOnClickListener {
            dismiss()
        }
    }
}