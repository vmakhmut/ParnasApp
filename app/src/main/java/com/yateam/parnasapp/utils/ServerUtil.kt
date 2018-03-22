package com.yateam.parnasapp.utils

import android.content.Context

import com.google.gson.Gson
import com.yateam.parnasapp.models.Profile
import com.yateam.parnasapp.R

import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

/**
 * Created by Vadim on 22.01.16.
 */

open class ServerUtil {

    fun request(gson: Gson?, context: Context): ServerApi {

        val logging = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = unsafeOkHttpClient.newBuilder()
                .addNetworkInterceptor(logging)
                .addInterceptor(ReceivedCookiesInterceptor(context))
                .addInterceptor(AddCookiesInterceptor(context))
//                .addInterceptor { chain ->
//                    val request = chain.request().newBuilder()
//                            .addHeader(AcceptLanguage, Locale.getDefault().toString())
//                            .addHeader(UserAgent, getUserAgent(context))
//
//                    if (body != null)
//                        request.post(body)
//
//                    chain.proceed(request.build())
//                }
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(context.getString(R.string.mobile_url))
                .addConverterFactory(if (gson == null)
                    GsonConverterFactory.create()
                else
                    GsonConverterFactory.create(gson))
                .client(client)
                .build()

        return retrofit.create(ServerApi::class.java)
    }

    interface ServerApi {

        @POST(User + Login)
        fun login(@Body body: HashMap<String, String>): Call<ResponseBody>

        @POST(User + Logged)
        fun profile(): Call<Profile>

        @POST(User + Logout)
        fun logout(): Call<ResponseBody>
    }

    companion object {

        private val TAG = ServerUtil::class.java.simpleName

        private const val User = "user/"
        private const val Login = "login/"
        private const val Logged = "logged/"
        private const val Logout = "logout/"

        private val AcceptLanguage = "Accept-Language"
        private val UserAgent = "User-Agent"

//        private fun getUserAgent(context: Context): String {
//            return context.getString(R.string.user_agent_format, context.getString(R.string.app_name),
//                    context.getString(R.string.app_version), Build.MANUFACTURER + " " + Build.MODEL,
//                    Build.VERSION.RELEASE, context.getString(R.string.scale))
//        }

        private val unsafeOkHttpClient: OkHttpClient
            get() = OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .hostnameVerifier { _, _ -> true }
                    .build()
    }
}
