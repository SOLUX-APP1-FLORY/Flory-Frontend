package com.solux.flory.presentation.gift.send

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import coil.load
import com.solux.flory.R
import com.solux.flory.databinding.FragmentGiftWriteMessageBinding
import com.solux.flory.presentation.gift.send.BouquetDialogFragment.Companion.NEIGHBOR_KEY
import com.solux.flory.util.base.BindingFragment
import com.solux.flory.util.fragment.stringOf
import com.solux.flory.util.setupToolbarClickListener

class WriteMessageFragment :
    BindingFragment<FragmentGiftWriteMessageBinding>(FragmentGiftWriteMessageBinding::inflate) {
    private var bouquetInfo: BouquetInfo? = null
    private var neighborName: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            bouquetInfo = it.getSerializable(FLOWER_KEY) as? BouquetInfo
            neighborName = it.getString(NEIGHBOR_KEY)
        }

        initView()
        initToolbar()
        nextBtnClick()
    }

    private fun initView() {
        bouquetInfo?.let {
            binding.ivWriteMessageBouquet.load(it.imageUrl)
            binding.tvWriteMessageBouquetName.text = it.name
            binding.tvWriteMessageBouquetMeaning.text = it.meaning
            binding.tvWriteMessageNeighborName.text = neighborName
        }
    }

    private fun initToolbar() {
        with(binding.toolbarWriteMessage) {
            tvToolbarTitle.text = stringOf(R.string.tv_gift_toolbar_title)
            setupToolbarClickListener(ibToolbar2LeftIcon)
        }
    }

    private fun nextBtnClick() {
        binding.btnWriteMessageGoNext.setOnClickListener {
            val giftMessage = binding.etWriteMessage.text.toString()
            val bouquetInfo = bouquetInfo

            val bundle = Bundle().apply {
                putString(MESSAGE, giftMessage)
                putSerializable(FLOWER_KEY, bouquetInfo)
                putString(NEIGHBOR_KEY, neighborName)
            }

            findNavController().navigate(
                R.id.action_fragment_write_message_to_fragment_select_card,
                bundle
            )
        }
    }


    companion object {
        const val FLOWER_KEY = "selectedFlower"
        const val MESSAGE = "message"
    }
}