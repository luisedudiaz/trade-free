package mx.itesm.tradefree.model.utils.classes

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

open class FirebaseManager {

    protected val auth: FirebaseAuth = FirebaseAuth.getInstance()
    protected val db: FirebaseDatabase = FirebaseDatabase.getInstance()
    protected val storage: FirebaseStorage = FirebaseStorage.getInstance()

}