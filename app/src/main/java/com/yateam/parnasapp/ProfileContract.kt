package com.yateam.parnasapp

import com.yateam.parnasapp.models.Profile

/**
 * Created by name on 03.03.2018.
 */
interface ProfileContract {

    interface View : BaseView<Presenter> {
        fun showProgress()
        fun hideProgress()
        fun showProfile(profile: Profile?)
        fun startLogin()
    }

    interface Presenter : BasePresenter{
        fun getProfile()
        fun logout()
    }
}