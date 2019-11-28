package mx.itesm.tradefree.model.interactors

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import mx.itesm.tradefree.model.models.Product.Product
import mx.itesm.tradefree.model.utils.classes.FirebaseManager
import mx.itesm.tradefree.presenter.contracts.IHomeContract
import com.androidnetworking.error.ANError
import android.graphics.Bitmap
import android.util.Log
import com.androidnetworking.interfaces.BitmapRequestListener
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority


class HomeInteractor(private val homeInteractor: IHomeContract.onHomeListener):  FirebaseManager(), IHomeContract.Interactor {


    override fun getProducts() {
        db.reference.child("/products").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val products = mutableListOf<Product>()
                    dataSnapshot.children.forEach {
                        val product = it.getValue(Product::class.java)
                        if (product != null) {
                            if (product.active) {
                                products.add(product)
                            }
                        }
                    }
                    homeInteractor.onSuccess(products)
                }
            }

        })
    }

}