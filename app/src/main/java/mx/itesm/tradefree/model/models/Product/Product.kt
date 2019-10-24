package mx.itesm.tradefree.model.models.Product

import java.io.Serializable

data class Product(
    var title: String = "",
    var description: String = "",
    var user: ProductUser = ProductUser(),
    var images: HashMap<String, Any> = hashMapOf()
) : Serializable