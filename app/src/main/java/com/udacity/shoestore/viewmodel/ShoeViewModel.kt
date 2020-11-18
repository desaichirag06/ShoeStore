package com.udacity.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.screens.MainActivity
import com.udacity.shoestore.utils.PreferencesHelper
import com.udacity.shoestore.utils.Utils

class ShoeViewModel : ViewModel() {

    private val _emailAddress = MutableLiveData<String>()
    val emailAddress: MutableLiveData<String> get() = _emailAddress

    private val _password = MutableLiveData<String>()
    val password: MutableLiveData<String> get() = _password

    private val _eventRegisterOrLogin = MutableLiveData<Boolean>()
    val eventRegisterOrLogin: LiveData<Boolean> get() = _eventRegisterOrLogin

    private val _eventShowErrorSnackbar = MutableLiveData<Boolean>()
    val eventShowErrorSnackbar: LiveData<Boolean> get() = _eventShowErrorSnackbar

    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isAlreadyLoggedIn: LiveData<Boolean> get() = _isLoggedIn

    private val _isLogout: MutableLiveData<Boolean> = MutableLiveData()
    val isLogout: LiveData<Boolean>
        get() = _isLogout

    private val _eventOpenInstructions = MutableLiveData<Boolean>()
    val eventOpenInstructions: LiveData<Boolean> get() = _eventOpenInstructions

    private val _eventOpenShoesList = MutableLiveData<Boolean>()
    val eventOpenShoesList: LiveData<Boolean> get() = _eventOpenShoesList


    private val _eventOpenShoeDetails = MutableLiveData<Boolean>()
    val eventOpenShoeDetails: LiveData<Boolean> get() = _eventOpenShoeDetails

    private val _eventCancel = MutableLiveData<Boolean>()
    val eventCancel: LiveData<Boolean> get() = _eventCancel

    private val _eventSave = MutableLiveData<Boolean>()
    val eventSave: LiveData<Boolean> get() = _eventSave

    private val _shoes = MutableLiveData<MutableList<Shoe>>()
    private val shoesDataList = ArrayList<Shoe>()
    val shoes: LiveData<MutableList<Shoe>> get() = _shoes

    init {
        _shoes.value = ArrayList()
        _emailAddress.value = ""
        _password.value = ""
    }


    fun onRegisterOrLogin() {
        if (Utils.isLoginDetailsValid(_emailAddress.value.toString(), _password.value.toString()))
            _eventRegisterOrLogin.value = true
        else
            _eventShowErrorSnackbar.value = true
    }

    fun onSnackbarComplete() {
        _eventShowErrorSnackbar.value = false
    }

    fun onLoginComplete() {
        _eventRegisterOrLogin.value = false
    }

    fun storeLoginToPreferences(activity: MainActivity, isLoggedIn: Boolean) {
        PreferencesHelper.storeLoginToPreferences(activity, isLoggedIn)
    }

    fun getLoginFromPreferences(activity: MainActivity) {
        val isLoggedIn = PreferencesHelper.getLoginFromPreferences(activity)
        _isLoggedIn.value = isLoggedIn
    }

    fun onOpenInstructions() {
        _eventOpenInstructions.value = true
    }

    fun onOpenShoesList() {
        _eventOpenShoesList.value = true
    }

    fun eventOpenShoesListComplete() {
        _eventOpenShoesList.value = false
    }

    fun onOpenShoesDetails() {
        _eventOpenShoeDetails.value = true
    }

    fun eventOpenShoeDetailsComplete() {
        _eventOpenShoeDetails.value = false
    }

    fun onCancelClicked() {
        _eventCancel.value = true
    }

    fun eventCancelComplete() {
        _eventCancel.value = false
    }

    fun onSaveClicked(shoe: Shoe?) {
        if (shoe != null && Utils.isShoeDetailsValid(shoe)) {
            _eventSave.value = true
            shoesDataList.add(shoe)
            _shoes.value = shoesDataList
        } else {
            _eventShowErrorSnackbar.value = true
        }
    }

    fun eventSaveComplete() {
        _eventSave.value = false
    }

    fun logout(activity: MainActivity) {
        storeLoginToPreferences(activity, false)
        _isLogout.value = true
    }

    fun eventLogoutComplete() {
        _isLogout.value = false
    }


}