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
    }
}