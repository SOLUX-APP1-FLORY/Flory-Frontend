package com.solux.flory.presentation.gift.send

import android.os.Bundle
import androidx.activity.viewModels
import coil.load
import coil.transform.CircleCropTransformation
import com.solux.flory.databinding.ActivityGiftSelectbouquetBinding
import com.solux.flory.presentation.gift.confirm.PresentInfo
import com.solux.flory.presentation.profile.NeighborInfo
import com.solux.flory.util.base.BindingActivity

class SelectBouquetActivity : BindingActivity<ActivityGiftSelectbouquetBinding>(ActivityGiftSelectbouquetBinding::inflate){
    private val viewModel by viewModels<BouquetViewModel>()
    private lateinit var adapter: BouquetAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initAdapter()
        impossibleBtnClick()

        val neighborInfo = intent.getSerializableExtra("selectedNeighbor") as? NeighborInfo
        neighborInfo?.let {
            binding.ivGiftImage.load(neighborInfo.profileImage) {
                transformations(CircleCropTransformation())
            }
            binding.tvSelectneighborName.text = neighborInfo.profileName
        }
    }

    private fun initAdapter() {
        adapter = BouquetAdapter(){
            val dialog = BouquetDialogFragment(it)
            dialog.show(supportFragmentManager, dialog.tag)
        }
        binding.rvBouquets.adapter = adapter
        adapter.submitList(viewModel.mockBouquet)
    }

    private fun impossibleBtnClick(){
        binding.ivSelectbouquetImpossible.setOnClickListener{
            finish()
        }
    }
}