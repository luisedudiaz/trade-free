package mx.itesm.tradefree.model.interactors

import android.app.Activity
import mx.itesm.tradefree.model.models.User
import mx.itesm.tradefree.model.utils.classes.Date
import mx.itesm.tradefree.model.utils.classes.FirebaseManager
import mx.itesm.tradefree.model.utils.enums.Message
import mx.itesm.tradefree.model.utils.enums.UserType
import mx.itesm.tradefree.presenter.contracts.IRegisterContract

class RegisterInteractor(private val onRegisterListener: IRegisterContract.onRegisterListener) : FirebaseManager(), IRegisterContract.Interactor {

    /**
     * This method creates the user with his email and password.
     * @param activity  the current activity
     * @param name      the user name retrieved from inputNameRegister and inputLastNameRegister
     * @param email     the user email retrieved from inputEmailRegister
     * @param password  the user password retrieved from inputPasswordlRegister
     */
    override fun performFirebaseRegister(activity: Activity, name: String, password: String, email: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                writeNewUser(name)
                onRegisterListener.onSuccess(Message.REGISTER.getMessageSuccess() + name)
            } else {
                onRegisterListener.onFailure(Message.REGISTER.getMessageSuccess())
            }
        }
    }

    /**
     *  This method creates a new user in the database.
     */
    private fun writeNewUser(name: String) {
        val userId = auth.currentUser?.uid
        val email = auth.currentUser?.email.toString()
        val currentDate = Date().getDate()
        val userType = UserType.BUYER
        val user = User(name, email, userType, currentDate, emptyList())
        if (userId != null) {
            db.reference.child("/users").child(userId).setValue(user)
        }
    }
}