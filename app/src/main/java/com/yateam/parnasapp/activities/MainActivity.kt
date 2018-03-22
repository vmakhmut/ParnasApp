package com.yateam.parnasapp.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.yateam.parnasapp.models.Profile
import com.yateam.parnasapp.ProfileContract
import com.yateam.parnasapp.ProfilePresenter
import com.yateam.parnasapp.R
import com.yateam.parnasapp.utils.Setting
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ProfileContract.View {

    override lateinit var presenter: ProfileContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = ProfilePresenter(this)
        presenter.getProfile()

        logout.setOnClickListener({presenter.logout()})
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.profile_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_refresh) {
            presenter.getProfile()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun showProgress() {}

    override fun hideProgress() {}

    override fun showProfile(profile: Profile?) {
        name.text = profile?.fields?.name
        surname.text = profile?.fields?.surname
        email.text = profile?.email
    }

    override fun startLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        Setting.clear(this)
        finish()
    }
}
