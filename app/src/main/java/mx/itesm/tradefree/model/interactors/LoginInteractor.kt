package mx.itesm.tradefree.model.interactors

import android.app.Activity

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import mx.itesm.tradefree.model.models.User.User
import mx.itesm.tradefree.model.utils.classes.Date
import mx.itesm.tradefree.model.utils.classes.FirebaseManager
import mx.itesm.tradefree.model.utils.enums.Message
import mx.itesm.tradefree.model.utils.enums.UserType
import mx.itesm.tradefree.presenter.contracts.ILoginContract

class LoginInteractor(private val onLoginListener: ILoginContract.onLoginListener) : FirebaseManager(), ILoginContract.Intractor {

    /**
     * This method authenticates the user with his email and password.
     * @param activity  the current activity
     * @param email     the user email retrieved from inputEmailLogin
     * @param password  the user password retrieved from inputPasswordlLogin
     */
    override fun performFirebaseLogin(activity: Activity, email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                onLoginListener.onSuccess(Message.LOGIN.getMessageSuccess() + auth.currentUser?.email)
            } else {
                onLoginListener.onFailure(Message.LOGIN.getMessageError())
            }
        }
    }

    /**
     * This method authenticates the user with google credentials.
     * @param activity  the current activity
     * @param account   user account to sign in
     */
    override fun performFirebaseLoginWithGoogle(activity: Activity, account: GoogleSignInAccount?) {
        val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                db.reference.child("/users").addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        var userExist = false
                        dataSnapshot.children.forEach {user->
                            if (user.key == auth.currentUser?.uid.toString()) {
                                userExist = true
                                return@forEach
                            }
                        }
                        if (!userExist) {
                            writeNewUser()
                        }
                    }

                })
                onLoginListener.onSuccess(Message.LOGIN.getMessageSuccess() + auth.currentUser?.displayName)
            } else {
                onLoginListener.onFailure(Message.LOGIN.getMessageError())
            }

        }
    }

    /**
     * This method validates if the user is authenticated.
     * @return If the user is authenticated
     */
    override fun isAuthenticated(): Boolean {
        return auth.currentUser != null
    }


    /**
     *  This method creates a new user in the database.
     */
    private fun writeNewUser() {
        val userId = auth.currentUser?.uid
        val name = auth.currentUser?.displayName.toString()
        val email = auth.currentUser?.email.toString()
        val currentDate = Date().getDate()
        val userType = UserType.BUYER
        val user = User(
            name,
            email,
            userType,
            currentDate,
            hashMapOf()
        )
        if (userId != null) {
            db.reference.child("/users").child(userId).setValue(user)
        }
    }

    companion object {
        private const val TAG = "LOGIN_INTERACTOR"
    }

}