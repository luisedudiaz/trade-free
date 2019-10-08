package mx.itesm.tradefree.Models

data class Product(
    val title: String,
    val description: String,
    val images: List<Image>
)