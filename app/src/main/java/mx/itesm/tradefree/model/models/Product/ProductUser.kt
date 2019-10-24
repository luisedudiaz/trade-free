package mx.itesm.tradefree.model.models.Product
import java.io.Serializable


data class ProductUser(
    var id: String = "",
    var name: String = "",
    var email: String = ""
) : Serializable