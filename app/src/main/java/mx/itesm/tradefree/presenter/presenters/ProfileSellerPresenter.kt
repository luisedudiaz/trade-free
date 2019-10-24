package mx.itesm.tradefree.presenter.presenters


import mx.itesm.tradefree.model.interactors.ProfileSellerInteractor
import mx.itesm.tradefree.model.models.User.User
import mx.itesm.tradefree.presenter.contracts.IProfileContract
import mx.itesm.tradefree.presenter.contracts.IProfileSellerContract

class ProfileSellerPresenter(private val profileSellerView: IProfileSellerContract.View): IProfileSellerContract.Presenter, IProfileSellerContract.onProfileSellerListener{

    private val profileSellerInteractor = ProfileSellerInteractor(this)

    override fun getUserData(uid: String) {
        profileSellerInteractor.performUserData(uid)
    }

    override fun onSuccessUser(user: User) {
        profileSellerView.onDataUserSuccess(user)
    }
}