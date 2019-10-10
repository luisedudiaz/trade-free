@file:Suppress("DEPRECATION")

package mx.itesm.tradefree

import android.app.ProgressDialog
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@Suppress("DEPRECATION")
open class BaseFragment: Fragment() {

    protected var auth: FirebaseAuth? = null
    protected var db: FirebaseDatabase? = null
    private lateinit var googleSignInClient: GoogleSignInClient

    /**
     *  Progress Dialog initialization.
     */
    @VisibleForTesting
    val progressDialog by lazy {
        ProgressDialog(context)
    }

    fun showProgressDialog() {
        progressDialog.setMessage(getString(R.string.loading))
        progressDialog.isIndeterminate = true
        progressDialog.show()
    }

    fun hideProgressDialog() {
        if (progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }

    fun hideKeyboard(view: View) {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /**
     *  Firebase initialization.
     */
    fun firebaseInit() {
        auth = FirebaseAuth.getInstance()
    }

    /**
     *  Firebase Database initialization.
     */
    fun firebaseDatabaseInit() {
        db = FirebaseDatabase.getInstance()
    }

    /**
     *  Google signin configuration.
     */
    fun googleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
    }

    override fun onStop() {
        super.onStop()
        hideProgressDialog()
    }

}