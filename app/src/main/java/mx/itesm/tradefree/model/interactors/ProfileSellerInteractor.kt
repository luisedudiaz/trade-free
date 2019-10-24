package mx.itesm.tradefree.model.interactors

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import mx.itesm.tradefree.model.models.User.User
import mx.itesm.tradefree.model.utils.classes.FirebaseManager
import mx.itesm.tradefree.presenter.contracts.IProfileSellerContract

class ProfileSellerInteractor(private val profileSellerInteractor: IProfileSellerContract.onProfileSellerListener): FirebaseManager(), IProfileSellerContract.Interactor {
    override fun performUserData(uid: String) {
        db.reference.child("users/$uid").addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(response: DataSnapshot) {
                Log.d("response", response.toString())
                if (response.exists()) {
                    profileSellerInteractor.onSuccessUser(response.getValue(User::class.java)!!)

                }
            }
        })
    }
}