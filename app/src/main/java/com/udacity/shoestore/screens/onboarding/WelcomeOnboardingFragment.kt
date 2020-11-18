package com.udacity.shoestore.screens.onboarding

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeViewModel
import com.udacity.shoestore.databinding.FragmentWelcomeOnboardingBinding


class WelcomeOnboardingFragment : Fragment() {

    private lateinit var mBinding: FragmentWelcomeOnboardingBinding
    private lateinit var viewModel: ShoeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_welcome_onboarding,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(ShoeViewModel::class.java)

        mBinding.lifecycleOwner = this
        mBinding.shoeViewModel = viewModel

        viewModel.eventOpenInstructions.observe(viewLifecycleOwner, { isNextClicked ->
            if (isNextClicked)
                instructionScreenNavigation()
        })
        mBinding.root.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        //hideNavKey(this.requireContext())
        return mBinding.root
    }

    private fun instructionScreenNavigation() {
        findNavController().navigate(WelcomeOnboardingFragmentDirections.actionInstructionOnboarding())
    }

}