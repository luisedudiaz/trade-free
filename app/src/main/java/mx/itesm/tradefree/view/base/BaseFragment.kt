@file:Suppress("DEPRECATION")

package mx.itesm.tradefree.view.base

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
import com.google.firebase.storage.FirebaseStorage
import mx.itesm.tradefree.R


@Suppress("DEPRECATION")
open class BaseFragment: Fragment() {

    protected var auth: FirebaseAuth? = null
    protected var db: FirebaseDatabase? = null
    protected var storage: FirebaseStorage? = null

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
     *  FirebaseStorage initialization.
     */
    fun firestorageInit() {
        storage = FirebaseStorage.getInstance()
    }

    override fun onStop() {
        super.onStop()
        hideProgressDialog()
    }
    
}