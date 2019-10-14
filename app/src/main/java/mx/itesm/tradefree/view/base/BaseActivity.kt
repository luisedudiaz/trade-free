@file:Suppress("DEPRECATION")

package mx.itesm.tradefree.view.base

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import mx.itesm.tradefree.view.login.ActivityLogin
import mx.itesm.tradefree.view.profile.ActivityProfile
import mx.itesm.tradefree.R
import mx.itesm.tradefree.presenter.contracts.ILogoutContract
import mx.itesm.tradefree.presenter.presenters.LogoutPresenter

@SuppressLint("Registered")
open class BaseActivity: AppCompatActivity(), ILogoutContract {

    private val logoutPresenter: LogoutPresenter
        get() = LogoutPresenter()

    /**
     *  Progress Dialog initialization.
     */
    @VisibleForTesting
    val progressDialog by lazy {
        ProgressDialog(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        return logoutPresenter.isAuthenticated()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.profile_menu -> goToProfile()
            R.id.logout_menu ->  goToLogin()
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     *  Show the progress dialog in the activity.
     */
    fun showProgressDialog() {
        progressDialog.setMessage(getString(R.string.loading))
        progressDialog.isIndeterminate = true
        progressDialog.show()
    }

    /**
     *  Hide ths progress dialog in the activity.
     */
    fun hideProgressDialog() {
        if (progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }

    /**
     *  This method hide the keyboard in the activity.
     */
    fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /**
     *  Add click event to the messages button.
     */
    fun setOnClickListenerFloatingButton(fabMessage: View) {
        fabMessage.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    /**
     *  This method send you to profile activity.
     *  @return when the profile activity start.
     */
    private fun goToProfile(): Boolean {
        showProgressDialog()
        val intent = Intent(this, ActivityProfile::class.java)
        startActivity(intent)
        hideProgressDialog()
        return true
    }

    /**
     *  This method ends the user session and start login activity.
     *  @return when the user sign out from the application and the login activity starts.
     */
    private fun goToLogin(): Boolean {
        showProgressDialog()
        val intent = Intent(this, ActivityLogin::class.java)
        signOut()
        startActivity(intent)
        hideProgressDialog()
        return true
    }

    /**
     *  This method ends the user session.
     */
    private fun signOut() {
        logoutPresenter.signout(this)
        finishAffinity()
    }

    public override fun onStop() {
        super.onStop()
        hideProgressDialog()
    }
}