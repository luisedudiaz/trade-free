package mx.itesm.tradefree.presenter.contracts

import mx.itesm.tradefree.model.models.Product
import mx.itesm.tradefree.model.models.User

interface IHomeContract {
    interface View {
        fun onDataSuccess(product: Product)
        fun onDataFailture(message: String)
    }
    interface Presenter{
        fun getProducts()
    }
    interface  Interactor{
        fun getProducts()
    }
    interface onHomeListener{
        fun onSuccess(product: Product)
        fun onFailure(message: String)
    }
}