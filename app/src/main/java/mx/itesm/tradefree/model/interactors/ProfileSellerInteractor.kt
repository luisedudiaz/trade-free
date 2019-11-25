package mx.itesm.tradefree.model.interactors

import android.graphics.Bitmap
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.BitmapRequestListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import mx.itesm.tradefree.model.models.Product.Product
import mx.itesm.tradefree.model.models.User.User
import mx.itesm.tradefree.model.utils.classes.FirebaseManager
import mx.itesm.tradefree.model.utils.enums.Message
import mx.itesm.tradefree.presenter.contracts.IProfileSellerContract
import java.util.HashMap

class ProfileSellerInteractor(private val profileSellerInteractor: IProfileSellerContract.onProfileSellerListener): FirebaseManager(), IProfileSellerContract.Interactor {
    override fun performProductUser(id: String) {
        db.reference.child("products/$id").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()) {
                    dataSnapshot.getValue(Product::class.java)?.let {
                        profileSellerInteractor.onSuccessProduct(
                            it
                        )
                    }
                }
            }

        })
    }

    override fun performUpdateProduct(product: Product) {
        Log.d("PRODUCT", "users/${product.user.id}/products/${product.id}")
        db.reference.child("users/${product.user.id}/products/${product.id}").child("title").setValue(product.title)
        db.reference.child("users/${product.user.id}/products/${product.id}").child("description").setValue(product.description)
        db.reference.child("products/${product.id}").child("title").setValue(product.title)
        db.reference.child("products/${product.id}").child("description").setValue(product.description).addOnCompleteListener {
           if (it.isSuccessful) {
               profileSellerInteractor.onUpdateSuccess(Message.UPDATE_PRODUCT.getMessageSuccess())
           } else {
               profileSellerInteractor.onUpdateError(Message.UPDATE_PRODUCT.getMessageError())
           }
        }
    }

    override fun performProductImages(images: HashMap<String, Any>) {
        images.values.forEach {
            AndroidNetworking.get(it.toString().substring(5, it.toString().length-1))
                .setTag("imageRequestTag")
                .setPriority(Priority.MEDIUM)
                .setBitmapMaxHeight(100)
                .setBitmapMaxWidth(100)
                .setBitmapConfig(Bitmap.Config.ARGB_8888)
                .build()
                .getAsBitmap(object : BitmapRequestListener {
                    override fun onResponse(bitmap: Bitmap) {
                        profileSellerInteractor.onSuccessImages(bitmap)
                    }

                    override fun onError(error: ANError) {
                        // handle error
                    }
                })
        }
    }

    override fun performUserData(uid: String) {
        db.reference.child("users/$uid").addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(response: DataSnapshot) {
                if (response.exists()) {
                    profileSellerInteractor.onSuccessUser(response.getValue(User::class.java)!!, auth.currentUser?.uid.toString())

                }
            }
        })
    }
}