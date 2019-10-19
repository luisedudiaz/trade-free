package mx.itesm.tradefree.model.models

data class Product(
    var title: String = "",
    var description: String = "",
    var images: List<Image>? = emptyList()
)