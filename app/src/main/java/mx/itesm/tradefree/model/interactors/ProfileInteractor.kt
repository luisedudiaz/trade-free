package mx.itesm.tradefree.model.interactors

import android.app.Activity
import android.util.Log
import com.google.firebase.database.ValueEventListener
import mx.itesm.tradefree.model.models.User
import mx.itesm.tradefree.model.utils.classes.FirebaseManager
import mx.itesm.tradefree.model.utils.enums.Message
import mx.itesm.tradefree.model.utils.enums.UserType
import mx.itesm.tradefree.presenter.contracts.IProfileContract
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot


class ProfileInteractor(private val profileInteractor: IProfileContract.onProfileListener): FirebaseManager(), IProfileContract.Interactor {

    override fun performUpdateTypeUser(activity: Activity, userType: UserType) {
        val uid = auth.currentUser?.uid
        db.reference.child("/users/$uid").child("type").setValue(userType).addOnCompleteListener {
            if (it.isSuccessful) {
                profileInteractor.onSuccess("${Message.PROFILE_TYPE.getMessageSuccess()}${if (userType == UserType.BUYER) "comprador." else "vendedor."}")
            } else {
                profileInteractor.onFailure(Message.PROFILE_TYPE.getMessageError())
            }
        }
    }

    override fun performUpdateUserInfo(activity: Activity, user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUserInfo() {
        val uid = auth.currentUser?.uid
        db.reference.child("users/$uid").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(response: DataSnapshot) {
                if (response.exists()) {
                     profileInteractor.onDataSuccess(response.getValue(User::class.java)!!)
                }
            }
        })
    }
}