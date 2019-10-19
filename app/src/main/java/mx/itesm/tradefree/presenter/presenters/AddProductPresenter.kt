package mx.itesm.tradefree.presenter.presenters

import android.net.Uri
import mx.itesm.tradefree.model.interactors.AddProductInteractor
import mx.itesm.tradefree.model.models.Product
import mx.itesm.tradefree.presenter.contracts.IAddProductContract
import mx.itesm.tradefree.view.addproduct.FragmentAddProduct

class AddProductPresenter(private val addProductView: IAddProductContract.View): IAddProductContract.Presenter, IAddProductContract.onAddProductListener {

    private val addProductInteractor: AddProductInteractor = AddProductInteractor(this)

    override fun createProduct(title: String, description: String, images: List<Uri>) {
        addProductInteractor.performCreateProduct(title, description, images)
    }

    override fun onSuccess(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFailure(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}