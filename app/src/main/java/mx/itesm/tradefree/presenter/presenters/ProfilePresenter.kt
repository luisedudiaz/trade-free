package mx.itesm.tradefree.presenter.presenters

import android.app.Activity
import mx.itesm.tradefree.model.interactors.ProfileInteractor
import mx.itesm.tradefree.model.models.User
import mx.itesm.tradefree.model.utils.enums.UserType
import mx.itesm.tradefree.presenter.contracts.IProfileContract

class ProfilePresenter(private val profileView: IProfileContract.View): IProfileContract.Presenter, IProfileContract.onProfileListener {

    private val profileInteractor: ProfileInteractor = ProfileInteractor(this)

    /**
     * This methood sends the userType to change.
     */
    override fun updateTypeUser(activity: Activity, userType: UserType) {
        profileInteractor.performUpdateTypeUser(activity, userType)
    }

    /**
     * This method sends the user information.
     */
    override fun updateUserInfo(activity: Activity, user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * This method calls the interactor to get the user information.
     */
    override fun getUserInfo() {
        profileInteractor.getUserInfo()
    }

    /**
     * This method is called when the user information callback is success.
     */
    override fun onDataSuccess(user: User) {
        profileView.onDataProfileSuccess(user)
    }

    /**
     * This method is called when user update is correct
     */
    override fun onSuccess(message: String) {
        profileView.onTypeProfileSuccess(message)
    }

    /**
     * This method is called when user update is incorrect
     */
    override fun onFailure(message: String) {
        profileView.onTypeProfileFailure(message)
    }
}