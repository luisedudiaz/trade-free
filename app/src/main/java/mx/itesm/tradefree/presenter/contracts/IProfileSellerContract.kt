package mx.itesm.tradefree.presenter.contracts

import android.graphics.Bitmap
import mx.itesm.tradefree.model.models.Product.Product
import mx.itesm.tradefree.model.models.User.User
import java.util.HashMap

interface IProfileSellerContract {
    interface View {
        fun onDataUserSuccess(user: User, uid: String)
        fun onDataImageSuccess(bitmap: Bitmap)
        fun onDataUpdateSuccess(messageSuccess: String)
        fun onDataUpdateError(messageError: String)
        fun onProductSuccess(product: Product)
    }

    interface Presenter {
        fun getUserData(uid: String)
        fun getImages(images: HashMap<String, Any>)
        fun updateProduct(product: Product)
        fun getProduct(id: String)
    }

    interface Interactor {
        fun performUserData(uid: String)
        fun performProductImages(images: HashMap<String, Any>)
        fun performUpdateProduct(product: Product)
        fun performProductUser(id: String)
    }

    interface onProfileSellerListener {
        fun onSuccessUser(user: User, uid: String)
        fun onSuccessImages(bitmap: Bitmap)
        fun onUpdateSuccess(messageSuccess: String)
        fun onUpdateError(messageError: String)
        fun onSuccessProduct(it: Product)
    }
}