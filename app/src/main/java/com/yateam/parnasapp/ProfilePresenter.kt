package com.yateam.parnasapp

import android.content.ContentValues.TAG
import android.util.Log
import com.google.gson.Gson
import com.yateam.parnasapp.activities.MainActivity
import com.yateam.parnasapp.models.ErrorBody
import com.yateam.parnasapp.models.Profile
import com.yateam.parnasapp.utils.ServerUtil
import com.yateam.parnasapp.utils.Utils
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by name on 03.03.2018.
 */
class ProfilePresenter(val view: ProfileContract.View) : ProfileContract.Presenter {

    private var serverUtil: ServerUtil

    init {
        view.presenter = this
        serverUtil = ServerUtil()
    }

    override fun start() {}

    override fun getProfile() {

        view.showProgress()

        serverUtil.request(null, view as MainActivity)
                .profile().enqueue(object : Callback<Profile> {
                    override fun onResponse(call: Call<Profile>, response: Response<Profile>) {

                        view.hideProgress()

                        if (response.isSuccessful) {
                            view.showProfile(response.body())
                            return
                        }

                        Gson().fromJson<ErrorBody>(response.errorBody()
                                ?.charStream(), ErrorBody::class.java)
                                .message?.let { Utils.showToast(view, it) }
                    }

                    override fun onFailure(call: Call<Profile>, t: Throwable) {
                        view.hideProgress()
                        Log.e(TAG, String.format("%s", t.message))
                    }
                })
    }

    override fun logout() {
        view.showProgress()

        serverUtil.request(null, view as MainActivity)
                .logout().enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                        view.hideProgress()

                        if (response.isSuccessful) {
                            view.startLogin()
                            return
                        }

                        Gson().fromJson<ErrorBody>(response.errorBody()
                                ?.charStream(), ErrorBody::class.java)
                                .message?.let { Utils.showToast(view, it) }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        view.hideProgress()
                        Log.e(TAG, String.format("%s", t.message))
                    }
                })
    }
}