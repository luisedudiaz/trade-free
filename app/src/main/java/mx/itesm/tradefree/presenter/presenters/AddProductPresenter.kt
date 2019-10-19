package mx.itesm.tradefree.presenter.presenters

import android.net.Uri
import mx.itesm.tradefree.model.interactors.AddProductInteractor
import mx.itesm.tradefree.presenter.contracts.IAddProductContract

class AddProductPresenter(private val addProductView: IAddProductContract.View): IAddProductContract.Presenter, IAddProductContract.onAddProductListener {

    private val addProductInteractor: AddProductInteractor = AddProductInteractor(this)

    override fun createProduct(title: String, description: String, images: List<Uri>) {
        addProductInteractor.performCreateProduct(title, description, images)
    }

    override fun onSuccess(message: String) {
        addProductView.onDataSuccess(message)
    }

    override fun onFailure(message: String) {
        addProductView.onDataFailure(message)
    }
}