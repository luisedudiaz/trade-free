package mx.itesm.tradefree.presenter.contracts

import android.graphics.Bitmap
import mx.itesm.tradefree.model.models.Product.Product

interface IHomeContract {
    interface View {
        fun onDataSuccess(products: MutableList<Product>)
        fun onDataFailture(message: String)
    }
    interface Presenter{
        fun getProducts()
    }
    interface  Interactor{
        fun getProducts()
    }
    interface onHomeListener{
        fun onSuccess(products: MutableList<Product>)
        fun onFailure(message: String)
    }
}