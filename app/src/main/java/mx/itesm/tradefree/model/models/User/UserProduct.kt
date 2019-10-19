package mx.itesm.tradefree.model.models.User

data class UserProduct(
    var title: String = "",
    var description: String = "",
    var images: HashMap<String, String> = hashMapOf()
)