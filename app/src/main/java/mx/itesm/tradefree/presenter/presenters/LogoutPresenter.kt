package mx.itesm.tradefree.presenter.presenters

import android.app.Activity
import mx.itesm.tradefree.model.interactors.LogoutInteractor
import mx.itesm.tradefree.presenter.contracts.ILogoutContract

class LogoutPresenter : ILogoutContract.Presenter {

    private val logoutInteractor: LogoutInteractor = LogoutInteractor()

    override fun signout(activity: Activity) {
        logoutInteractor.performSignout(activity)
    }

    /**
     * This method gets the user authentication status.
     * @return If the user is authenticated.
     */
    override fun isAuthenticated(): Boolean {
        return logoutInteractor.isAuthenticated()
    }
}