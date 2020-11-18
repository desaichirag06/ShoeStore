package com.udacity.shoestore.screens.shoeDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeViewModel
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.shoestore.models.Shoe

class ShoeDetailsFragment : Fragment() {

    private lateinit var mBinding: FragmentShoeDetailsBinding
    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_details, container, false)

        //viewModel = ViewModelProvider(this).get(ShoeViewModel::class.java)

        mBinding.lifecycleOwner = this
        mBinding.shoeViewModel = viewModel
        mBinding.shoe = Shoe()

        viewModel.eventCancel.observe(viewLifecycleOwner, { isCancelClicked ->
            if (isCancelClicked) {
                navigateToList()
                viewModel.eventCancelComplete()
            }
        })

        viewModel.eventSave.observe(viewLifecycleOwner, { isSaveClicked ->
            if (isSaveClicked) {
                navigateToList()
                viewModel.eventSaveComplete()
            }
        })

        viewModel.eventShowErrorSnackbar.observe(viewLifecycleOwner, { eventError ->
            if (eventError) {
                Snackbar.make(
                    mBinding.tvShoeCompanyStatic,
                    getString(R.string.please_fill_all_details),
                    Snackbar.LENGTH_SHORT
                ).show()
                viewModel.onSnackbarComplete()
            }
        })
        mBinding.root.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        return mBinding.root
    }

    private fun navigateToList() {
        findNavController().navigate(ShoeDetailsFragmentDirections.actionDetailsToListFragment())
    }

}