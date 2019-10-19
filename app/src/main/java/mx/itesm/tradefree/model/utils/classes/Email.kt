package mx.itesm.tradefree.model.utils.classes

class Email {

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}