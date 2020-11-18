package com.udacity.shoestore.screens.login

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
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.screens.MainActivity
import com.udacity.shoestore.viewmodel.ShoeViewModel

class LoginFragment : Fragment() {

    private lateinit var mBinding: FragmentLoginBinding
    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        //viewModel = ViewModelProvider(this).get(ShoeViewModel::class.java)

        mBinding.lifecycleOwner = this
        mBinding.shoeViewModel = viewModel
        viewModel.eventRegisterOrLogin.observe(viewLifecycleOwner, { hasLoggedIn ->
            if (hasLoggedIn) {
                viewModel.storeLoginToPreferences(activity as MainActivity, true)
                viewModel.onLoginComplete()
                welcomeScreenNavigation()
            }
        })

        viewModel.eventShowErrorSnackbar.observe(viewLifecycleOwner, { eventError ->
            if (eventError) {
                Snackbar.make(
                    mBinding.tilEmailAddress,
                    getString(R.string.please_fill_all_details),
                    Snackbar.LENGTH_SHORT
                ).show()
                viewModel.onSnackbarComplete()
            }
        })

        return mBinding.root
    }

    private fun welcomeScreenNavigation() {
        findNavController().navigate(LoginFragmentDirections.actionWelcomeOnboarding())
    }

}