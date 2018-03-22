package com.yateam.parnasapp.utils

import okhttp3.Interceptor
import okhttp3.Response
import android.content.Context


/**
 * Created by name on 03.03.2018.
 */
class ReceivedCookiesInterceptor(val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {
        val originalResponse = chain?.proceed(chain.request())

        if (!originalResponse?.headers("Set-Cookie")?.isEmpty()!!) {
            val cookies = HashSet<String>()

            for (header in originalResponse?.headers("Set-Cookie")) {
                cookies.add(header)
            }

            Setting.setHashSet(Setting.PreferenceCookies, cookies, context)
        }

        return originalResponse
    }
}