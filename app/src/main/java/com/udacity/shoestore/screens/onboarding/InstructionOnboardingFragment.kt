package com.udacity.shoestore.screens.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionOnboardingBinding
import com.udacity.shoestore.viewmodel.ShoeViewModel

class InstructionOnboardingFragment : Fragment() {

    private lateinit var mBinding: FragmentInstructionOnboardingBinding
    private lateinit var viewModel: ShoeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_instruction_onboarding,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(ShoeViewModel::class.java)

        mBinding.lifecycleOwner = this
        mBinding.shoeViewModel = viewModel

        viewModel.eventOpenShoesList.observe(viewLifecycleOwner, { isNextClicked ->
            if (isNextClicked) {
                shoesListScreenNavigation()
                viewModel.eventOpenShoesListComplete()
            }
        })

        return mBinding.root
    }

    private fun shoesListScreenNavigation() {
        findNavController().navigate(InstructionOnboardingFragmentDirections.actionShoesListFragment())
    }

}