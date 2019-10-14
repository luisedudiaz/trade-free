package mx.itesm.tradefree.model.interactors

import android.app.Activity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import mx.itesm.tradefree.R
import mx.itesm.tradefree.model.utils.classes.FirebaseManager
import mx.itesm.tradefree.presenter.contracts.ILogoutContract

class LogoutInteractor : FirebaseManager(), ILogoutContract.Intractor {

    override fun performSignout(activity: Activity) {
        auth.signOut()
        googleSignIn(activity)?.signOut()
    }

    /**
     * This method validates if the user is authenticated.
     * @return If the user is authenticated
     */
    override fun isAuthenticated(): Boolean {
        return auth.currentUser != null
    }

    /**
     *  Google Sign In Client
     */
    private fun googleSignIn(activity: Activity): GoogleSignInClient? {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(R.string.default_web_client_id.toString())
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(activity, gso)
    }

    companion object {
        private const val TAG = "LOGIN_INTERACTOR"
    }

}