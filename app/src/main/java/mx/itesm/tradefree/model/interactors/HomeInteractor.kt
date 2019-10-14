package mx.itesm.tradefree.model.interactors

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import mx.itesm.tradefree.model.utils.classes.FirebaseManager
import mx.itesm.tradefree.presenter.contracts.IHomeContract
import mx.itesm.tradefree.presenter.presenters.HomePresenter

class HomeInteractor(private val homeInteractor: IHomeContract.onHomeListener):  FirebaseManager(), IHomeContract.Interactor {
    override fun getProducts() {
        db.reference.child("/products").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    dataSnapshot.children.forEach {
                        Log.d("PRODUCTS", it.toString())
                    }
                }
            }

        })
    }

}