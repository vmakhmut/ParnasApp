package com.yateam.parnasapp.models

/**
 * Created by name on 04.03.2018.
 */
open class ErrorBody(var success: Boolean,
                     var code: String? = null,
                     var message: String? = null)