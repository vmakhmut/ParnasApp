package com.yateam.parnasapp

/**
 * Created by name on 03.03.2018.
 */
interface LoginContract {

    interface View : BaseView<Presenter> {
        fun showProgress()
        fun hideProgress()
        fun startProfile()
    }

    interface Presenter : BasePresenter{
        fun login(login: String?, pswd: String?)
    }
}