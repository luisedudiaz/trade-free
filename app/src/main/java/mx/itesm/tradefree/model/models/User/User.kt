package mx.itesm.tradefree.model.models.User

import mx.itesm.tradefree.model.utils.enums.UserType
import java.io.Serializable


data class User(
     var name: String = "",
     var email: String = "",
     var type: UserType = UserType.BUYER,
     var registrationDate: String = "",
     var products: HashMap<String ,UserProduct> = hashMapOf()
) : Serializable

