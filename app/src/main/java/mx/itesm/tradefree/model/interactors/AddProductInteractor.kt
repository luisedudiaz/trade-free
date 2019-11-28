package mx.itesm.tradefree.model.interactors

import android.net.Uri
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import mx.itesm.tradefree.model.models.Product.Product
import mx.itesm.tradefree.model.models.Product.ProductUser
import mx.itesm.tradefree.model.models.User.User
import mx.itesm.tradefree.model.models.User.UserProduct
import mx.itesm.tradefree.model.utils.classes.FirebaseManager
import mx.itesm.tradefree.model.utils.enums.Message
import mx.itesm.tradefree.presenter.contracts.IAddProductContract
import java.util.*
import kotlin.collections.HashMap


class AddProductInteractor(private val addProductorInteractor: IAddProductContract.onAddProductListener): FirebaseManager(), IAddProductContract.Interactor {
    override fun performCreateProduct(title: String, description: String, images: List<Uri>) {
        val uid = auth.currentUser?.uid
        val uuidProduct = UUID.randomUUID()
        val refUser  = db.reference.child("/users/$uid")
        val refUserProduct = db.reference.child("/users/$uid/products/$uuidProduct")
        val refProduct = db.reference.child("/products/$uuidProduct")

        refUser.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(User::class.java)
                val userProduct = UserProduct(uuidProduct.toString() ,title, description, true)
                val product = user?.email?.let { ProductUser(uid.toString(), user.name, it) }?.let { Product(uuidProduct.toString(), title, description, true ,it)}
                refUserProduct.setValue(userProduct).addOnCompleteListener {
                    if (it.isSuccessful) {
                        refProduct.setValue(product).addOnCompleteListener { itProduct ->
                            if (itProduct.isSuccessful) {
                                images.forEach {image ->
                                    val uuidImage = UUID.randomUUID()
                                    storage.reference.child("images/$uuidImage").putFile(image).addOnSuccessListener {
                                        storage.reference.child("images/$uuidImage").downloadUrl.addOnSuccessListener { uri ->
                                            val hashMapImage = HashMap<String, String>()
                                            hashMapImage["url"] = uri.toString()
                                            refUserProduct.child("images").push().setValue(hashMapImage)
                                            refProduct.child("images").push().setValue(hashMapImage)
                                        }
                                    }
                                }
                                addProductorInteractor.onSuccess(Message.ADD_PRODUCT.getMessageSuccess())
                            } else addProductorInteractor.onFailure(Message.ADD_PRODUCT.getMessageError())
                        }
                    } else addProductorInteractor.onFailure(Message.ADD_PRODUCT.getMessageError())
                }
            }
            override fun onCancelled(p0: DatabaseError) {
                addProductorInteractor.onFailure(Message.ADD_PRODUCT.getMessageError())
            }
        })

    }
}