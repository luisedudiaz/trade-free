package mx.itesm.tradefree.model.models.Product

import java.io.Serializable

data class Product(
    var id : String = "",
    var title: String = "",
    var description: String = "",
    var active: Boolean = true,
    var user: ProductUser = ProductUser(),
    var images: HashMap<String, Any> = hashMapOf()
) : Serializable