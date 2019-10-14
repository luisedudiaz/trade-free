package mx.itesm.tradefree.presenter.presenters

import android.app.Activity
import mx.itesm.tradefree.model.interactors.ProfileInteractor
import mx.itesm.tradefree.model.models.User
import mx.itesm.tradefree.model.utils.enums.UserType
import mx.itesm.tradefree.presenter.contracts.IProfileContract

class ProfilePresenter(private val profileView: IProfileContract.View): IProfileContract.Presenter, IProfileContract.onProfileListener {

    private val profileInteractor: ProfileInteractor = ProfileInteractor(this)

    override fun updateTypeUser(activity: Activity, userType: UserType) {
        profileInteractor.performUpdateTypeUser(activity, userType)
    }
    override fun updateUserInfo(activity: Activity, user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUserInfo() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccess(message: String) {
        profileView.onDataProfileSuccess(message)
    }

    override fun onFailure(message: String) {
        profileView.onDataProfileFailure(message)
    }
}