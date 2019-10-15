package mx.itesm.tradefree.presenter.presenters

import mx.itesm.tradefree.model.interactors.HomeInteractor
import mx.itesm.tradefree.model.models.Product
import mx.itesm.tradefree.presenter.contracts.IHomeContract

class HomePresenter(private val homeView: IHomeContract.View): IHomeContract.Presenter, IHomeContract.onHomeListener {

    private val homeInteractor = HomeInteractor(this)

    override fun getProducts() {
        homeInteractor.getProducts()
    }

    override fun onSuccess(product: Product) {
        homeView.onDataSuccess(product)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFailure(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
