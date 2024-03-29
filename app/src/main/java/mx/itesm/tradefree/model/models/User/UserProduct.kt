package mx.itesm.tradefree.model.models.User

import java.io.Serializable


data class UserProduct(
    var id: String = "",
    var title: String = "",
    var description: String = "",
    var active: Boolean = true,
    var images: HashMap<String, Any> = hashMapOf()
) : Serializable