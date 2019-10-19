package mx.itesm.tradefree.presenter.presenters

import mx.itesm.tradefree.model.interactors.HomeInteractor
import mx.itesm.tradefree.model.models.User.UserProduct
import mx.itesm.tradefree.presenter.contracts.IHomeContract

class HomePresenter(private val homeView: IHomeContract.View): IHomeContract.Presenter, IHomeContract.onHomeListener {

    private val homeInteractor = HomeInteractor(this)

    override fun getProducts() {
        homeInteractor.getProducts()
    }

    override fun onSuccess(userProduct: UserProduct) {
        homeView.onDataSuccess(userProduct)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFailure(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
