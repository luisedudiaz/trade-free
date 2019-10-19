package mx.itesm.tradefree.model.interactors

import android.net.Uri
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.UploadTask
import mx.itesm.tradefree.model.models.Product
import mx.itesm.tradefree.model.models.User
import mx.itesm.tradefree.model.utils.classes.FirebaseManager
import mx.itesm.tradefree.presenter.contracts.IAddProductContract
import androidx.annotation.NonNull
import com.google.android.gms.tasks.OnFailureListener
import mx.itesm.tradefree.model.models.Image
import java.util.*


class AddProductInteractor(private val addProductorInteractor: IAddProductContract.onAddProductListener): FirebaseManager(), IAddProductContract.Interactor {
    override fun performCreateProduct(title: String, description: String, images: List<Uri>) {
        val uid = auth.currentUser?.uid
        db.reference.child("users/$uid").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(response: DataSnapshot) {
                if (response.exists()) {
                    val imageList = mutableListOf<Image>()
                    val uuidProduct = UUID.randomUUID()
                    val uuidImages = UUID.randomUUID()
                    images.forEach {
                        val uuidImage = UUID.randomUUID()
                        storage.reference.child("images/$uuidImage").putFile(it)
                            .addOnSuccessListener {
                                storage.reference.child("images/$uuidImage").downloadUrl.addOnSuccessListener { uri ->
                                    imageList.add(Image(uri.toString()))
                                }
                            }
                    }

                    val product = Product(title, description, imageList as List<Image>)
                    db.reference.child("/users/${auth.currentUser?.uid}/products/$uuidProduct").setValue(product).addOnCompleteListener {
                        if (it.isSuccessful) {
                            db.reference.child("/users/${auth.currentUser?.uid}/products/$uuidProduct/images").setValue(imageList).addOnCompleteListener {
                                db.reference.child("/products/$uuidProduct").setValue(product).addOnCompleteListener { task ->
                                    if (task.isSuccessful) {

                                    }
                                }
                            }

                        }
                    }
                }
            }
        })
    }


}