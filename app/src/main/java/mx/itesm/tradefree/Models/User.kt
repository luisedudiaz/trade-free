package mx.itesm.tradefree.Models

data class User(
    val name: String,
    val email: String,
    val products: List<Product>
)