package mx.itesm.tradefree.presenter.presenters


import android.graphics.Bitmap
import mx.itesm.tradefree.model.interactors.ProfileSellerInteractor
import mx.itesm.tradefree.model.models.Product.Product
import mx.itesm.tradefree.model.models.User.User
import mx.itesm.tradefree.presenter.contracts.IProfileContract
import mx.itesm.tradefree.presenter.contracts.IProfileSellerContract
import java.util.HashMap

class ProfileSellerPresenter(private val profileSellerView: IProfileSellerContract.View): IProfileSellerContract.Presenter, IProfileSellerContract.onProfileSellerListener{
    override fun onSuccessProduct(it: Product) {
        profileSellerView.onProductSuccess(it)    }

    override fun getProduct(id: String) {
        profileSellerInteractor.performProductUser(id)
    }

    override fun onUpdateSuccess(messageSuccess: String) {
        profileSellerView.onDataUpdateSuccess(messageSuccess)
    }

    override fun onUpdateError(messageError: String) {
        profileSellerView.onDataUpdateError(messageError)
    }

    override fun updateProduct(product: Product) {
        profileSellerInteractor.performUpdateProduct(product)
    }

    override fun onSuccessImages(bitmap: Bitmap) {
        profileSellerView.onDataImageSuccess(bitmap)
    }

    private val profileSellerInteractor = ProfileSellerInteractor(this)

    override fun getUserData(uid: String) {
        profileSellerInteractor.performUserData(uid)
    }

    override fun getImages(images: HashMap<String, Any>) {
        profileSellerInteractor.performProductImages(images)
    }

    override fun onSuccessUser(user: User, uid: String) {
        profileSellerView.onDataUserSuccess(user, uid)
    }
}