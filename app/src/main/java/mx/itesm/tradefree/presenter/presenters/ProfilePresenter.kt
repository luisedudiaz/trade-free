package mx.itesm.tradefree.presenter.presenters

import android.app.Activity
import mx.itesm.tradefree.model.interactors.ProfileInteractor
import mx.itesm.tradefree.model.models.Product.Product
import mx.itesm.tradefree.model.models.User.User
import mx.itesm.tradefree.model.models.User.UserProduct
import mx.itesm.tradefree.model.utils.enums.UserType
import mx.itesm.tradefree.presenter.contracts.IProfileContract

class ProfilePresenter(private val profileView: IProfileContract.View): IProfileContract.Presenter, IProfileContract.onProfileListener {
    override fun onProductDeleted() {
        profileView.onProductDeletedSuccess()
    }

    override fun deleteProduct(product: UserProduct) {
        profileInteractor.performDeleteProduct(product)
    }

    override fun deleteProducts() {
        profileInteractor.performDeleteProducts()
    }

    override fun onSuccessProduct(value: Product) {
        profileView.onProductSuccess(value)
    }

    override fun getProduct(id: String) {
        profileInteractor.performProductUser(id)
    }


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
        profileInteractor.performUpdateUserInfo(activity, user)
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

    override fun onProfileUpdateSuccess(message: String) {
        profileView.onProfileUpdateSuccess(message)
    }

    override fun onProfileUpdateFailure(message: String) {
        profileView.onProfileUpdateFailure(message)
    }
}