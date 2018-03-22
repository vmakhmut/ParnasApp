package com.yateam.parnasapp.utils

import okhttp3.Interceptor
import okhttp3.Response
import android.content.Context
import android.util.Log


/**
 * Created by name on 03.03.2018.
 */
class AddCookiesInterceptor(val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response? {
        val builder = chain?.request()?.newBuilder()
        val preferences = Setting.getHashSet(Setting.PreferenceCookies, context) as? HashSet
        if (preferences != null) {
            for (cookie in preferences) {
                builder?.addHeader("Cookie", cookie)
                Log.v("OkHttp", "Adding Header: $cookie")
            }
        }

        return builder?.build().let { chain?.proceed(it) }
    }
}