package com.udacity.shoestore.screens.shoeList

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoesListBinding
import com.udacity.shoestore.databinding.ShoeItemViewBinding
import com.udacity.shoestore.viewmodel.ShoeViewModel

class ShoesListFragment : Fragment() {

    private lateinit var mBinding: FragmentShoesListBinding
    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoes_list, container, false)
        setHasOptionsMenu(true)
        mBinding.lifecycleOwner = this
        mBinding.viewModel = viewModel

        viewModel.shoes.observe(viewLifecycleOwner, { shoes ->
            mBinding.llShoesItemList.removeAllViews()
            shoes.forEach { shoe ->
                val shoeBinding: ShoeItemViewBinding =
                    DataBindingUtil.inflate(inflater, R.layout.shoe_item_view, container, false)
                shoeBinding.shoe = shoe
                shoeBinding.executePendingBindings()
                mBinding.llShoesItemList.addView(shoeBinding.root)
            }
        })

        viewModel.eventOpenShoeDetails.observe(viewLifecycleOwner, { isNextClicked ->
            if (isNextClicked) {
                detailsScreenNavigation()
                viewModel.eventOpenShoeDetailsComplete()
            }

        })

        return mBinding.root
    }

    private fun detailsScreenNavigation() {
        findNavController().navigate(ShoesListFragmentDirections.actionShoeDetailsFragment())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.findItem(R.id.actionLogOut).isVisible = true
        super.onCreateOptionsMenu(menu, inflater)
    }
}