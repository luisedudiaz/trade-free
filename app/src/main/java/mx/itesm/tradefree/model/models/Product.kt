package mx.itesm.tradefree.model.models

data class Product(
    val title: String,
    val description: String,
    val images: List<Image>
)