package mx.itesm.tradefree.presenter.contracts

import mx.itesm.tradefree.model.models.User.UserProduct

interface IHomeContract {
    interface View {
        fun onDataSuccess(userProduct: UserProduct)
        fun onDataFailture(message: String)
    }
    interface Presenter{
        fun getProducts()
    }
    interface  Interactor{
        fun getProducts()
    }
    interface onHomeListener{
        fun onSuccess(userProduct: UserProduct)
        fun onFailure(message: String)
    }
}