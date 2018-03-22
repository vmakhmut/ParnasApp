package com.yateam.parnasapp.utils

import android.content.Context
import android.widget.Toast

/**
 * Created by name on 03.03.2018.
 */
class Utils {

    companion object {
        fun showToast(context: Context, text: String) {
            Toast.makeText(context, text, Toast.LENGTH_LONG).show()
        }
    }
}