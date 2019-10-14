package mx.itesm.tradefree.model.models

import mx.itesm.tradefree.model.utils.enums.UserType

data class User(
     val name: String = "",
     val email: String = "",
     val type: UserType = UserType.BUYER,
     val registrationDate: String = "",
     val products: List<Product>? = emptyList()
)

