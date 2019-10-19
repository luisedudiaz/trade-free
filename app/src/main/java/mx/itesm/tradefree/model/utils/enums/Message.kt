package mx.itesm.tradefree.model.utils.enums

import mx.itesm.tradefree.model.utils.intefaces.IMessage

enum class Message : IMessage {
    LOGIN {
        override fun getMessageSuccess() = "Bienvenido "
        override fun getMessageError() = "Ingrese correctamente su correo y/o contraseña."
    },
    REGISTER {
        override fun getMessageSuccess() = "Bienvenido "
        override fun getMessageError() = "Ingrese correctamente sus datos."
    },
    PROFILE_TYPE {
        override fun getMessageSuccess(): String = "Has cambiado tu tipo de usuario a "
        override fun getMessageError(): String = "No pudimos cambiar tu tipo de usuario correctamente."
    },
    PROFILE_DATA {
        override fun getMessageSuccess(): String = "Has cambiado tu "
        override fun getMessageError(): String = "No se pudo realizar la actualización de tus datos."
    },
    ADD_PRODUCT {
        override fun getMessageSuccess(): String = "Tu producto se ha agregado correctamente."
        override fun getMessageError(): String = "No se pudo agregar producto."
    }
}