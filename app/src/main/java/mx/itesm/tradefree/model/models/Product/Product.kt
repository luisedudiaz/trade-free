package mx.itesm.tradefree.model.models.Product

data class Product(
    var title: String = "",
    var description: String = "",
    var user: ProductUser = ProductUser(),
    var images: HashMap<String, String> = hashMapOf()
)