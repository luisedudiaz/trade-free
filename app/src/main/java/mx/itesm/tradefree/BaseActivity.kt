@file:Suppress("DEPRECATION")

package mx.itesm.tradefree

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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import mx.itesm.tradefree.Login.ActivityLogin
import mx.itesm.tradefree.Profile.ActivityProfile

@SuppressLint("Registered")
open class BaseActivity: AppCompatActivity() {

    protected var auth: FirebaseAuth? = null
    private lateinit var googleSignInClient: GoogleSignInClient

    /**
     *  Progress Dialog initialization.
     */
    @VisibleForTesting
    val progressDialog by lazy {
        ProgressDialog(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        return auth?.currentUser != null
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
     *  Firebase initialization.
     */
    fun firebaseInit() {
        auth = FirebaseAuth.getInstance()
    }

    /**
     *  Google Sign In Client
     */
    fun googleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
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
        auth?.let { signOut(it, googleSignInClient) }
        startActivity(intent)
        hideProgressDialog()
        return true
    }

    /**
     *  This method ends the user session.
     */
    private fun signOut(auth: FirebaseAuth, googleSignInClient: GoogleSignInClient) {
        finishAffinity()
        auth.signOut()
        googleSignInClient.signOut()
    }

    public override fun onStop() {
        super.onStop()
        hideProgressDialog()
    }

}