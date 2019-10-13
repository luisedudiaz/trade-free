package mx.itesm.tradefree.presenter.presenters

import android.app.Activity
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import mx.itesm.tradefree.model.interactors.LoginInteractor
import mx.itesm.tradefree.presenter.contracts.ILoginContract

class LoginPresenter(private val loginView: ILoginContract.View): ILoginContract.Presenter, ILoginContract.onLoginListener {


    private val loginInteractor: LoginInteractor = LoginInteractor(this)

    /**
     * This method sends the user's data from the view to the presenter for authentication.
     */
    override fun login(activity: Activity, email: String, password: String) {
        loginInteractor.performFirebaseLogin(activity, email, password)
    }

    /**
     * This method sends the user account from the view to the presenter fot authentication.
     */
    override fun loginWithGoogle(activity: Activity, account: GoogleSignInAccount?) {
        loginInteractor.performFirebaseLoginWithGoogle(activity, account)
    }

    /**
     * This method gets the user authentication status.
     * @return If the user is authenticated.
     */
    override fun isAuthenticated(): Boolean {
        return loginInteractor.isAuthenticated()
    }

    /**
     * This method listen if the login was successfully.
     */
    override fun onSuccess(message: String) {
        loginView.onLoginSuccess(message)
    }

    /**
     * This method listen if the login was failure.
     */
    override fun onFailure(message: String) {
        loginView.onLoginFailure(message)
    }
}