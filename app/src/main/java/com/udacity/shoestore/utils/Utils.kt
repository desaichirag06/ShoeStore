package com.udacity.shoestore.utils

import com.udacity.shoestore.models.Shoe

class Utils {
    companion object {
        fun isLoginDetailsValid(email: String, password: String): Boolean {
            return email.isNotBlank() && password.isNotBlank()
        }

        fun isShoeDetailsValid(shoe: Shoe): Boolean {
            return shoe.name.isNotBlank() && shoe.company.isNotBlank() && shoe.size.isNotBlank()
                    && shoe.description.isNotBlank()
        }
    }
}