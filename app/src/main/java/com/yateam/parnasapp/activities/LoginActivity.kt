package com.yateam.parnasapp.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import com.yateam.parnasapp.*
import com.yateam.parnasapp.utils.Utils
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View, TextWatcher {

    override lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(this)
        presenter.start()

        connectEvents()
    }

    private fun connectEvents() {
        send.setOnClickListener({login()})
        login.addTextChangedListener(this)
    }

    private fun login() {
        if (login.text.isNullOrEmpty()) {
            Utils.showToast(this, getString(R.string.login_is_empty))
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(login.text).matches()) {
            Utils.showToast(this, getString(R.string.invalid_email))
            return
        }

        if (password.text.isNullOrEmpty()) {
            Utils.showToast(this, getString(R.string.password_is_empty))
            return
        }

        presenter.login(login.text.toString(), password.text.toString())
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun startProfile() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun afterTextChanged(p0: Editable?) {
        send.rotate(45f).doOnComplete { login.fadeOut(1000).subscribe() }.subscribe()
    }
}
