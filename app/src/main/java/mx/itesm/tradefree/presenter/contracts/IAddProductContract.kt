package mx.itesm.tradefree.presenter.contracts

import android.net.Uri

interface IAddProductContract {

    interface View {
        fun onDataSuccess(message: String)
        fun onDataFailure(message: String)
    }
    interface Presenter {
        fun createProduct(title: String, description: String, images: List<Uri>)
    }
    interface Interactor {
        fun performCreateProduct(title: String, description: String, images: List<Uri>)
    }
    interface onAddProductListener {
        fun onSuccess(message: String)
        fun onFailure(message: String)
    }
}