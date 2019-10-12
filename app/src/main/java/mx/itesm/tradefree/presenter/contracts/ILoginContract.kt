package mx.itesm.tradefree.presenter.contracts

import android.app.Activity
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

interface ILoginContract {
    interface View {
        fun onLoginSuccess(message: String)
        fun onLoginFailure(message: String)
    }

    interface Presenter {
        fun login(activity: Activity, email: String, password: String)
        fun loginWithGoogle(
            activity: Activity,
            account: GoogleSignInAccount?
        )
        fun isAuthenticated() : Boolean
    }

    interface Intractor {
        fun performFirebaseLogin(activity: Activity, email: String, password: String)
        fun performFirebaseLoginWithGoogle(activity: Activity, account: GoogleSignInAccount?)
        fun isAuthenticated() : Boolean
    }

    interface onLoginListener {
        fun onSuccess(message: String)
        fun onFailure(message: String)
    }
}