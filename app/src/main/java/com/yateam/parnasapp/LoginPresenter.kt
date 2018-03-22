package com.yateam.parnasapp

import android.content.ContentValues.TAG
import android.util.Log
import com.google.gson.Gson
import com.yateam.parnasapp.activities.LoginActivity
import com.yateam.parnasapp.models.ErrorBody
import com.yateam.parnasapp.utils.ServerUtil
import com.yateam.parnasapp.utils.Utils
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

/**
 * Created by name on 03.03.2018.
 */
class LoginPresenter(val view: LoginContract.View) : LoginContract.Presenter {

    private var serverUtil: ServerUtil

    init {
        view.presenter = this
        serverUtil = ServerUtil()
    }

    override fun start() {}

    override fun login(login: String?, pswd: String?) {
        if (login.isNullOrEmpty() || pswd.isNullOrEmpty())
            return

        view.showProgress()

        val body = HashMap<String, String>()
        body["loginType"] = "email"
        body["login"] = login!!
        body["password"] = pswd!!

        serverUtil.request(null, view as LoginActivity)
                .login(body).enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                        view.hideProgress()

                        if (response.isSuccessful) {
                            view.startProfile()
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