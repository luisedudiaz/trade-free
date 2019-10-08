package mx.itesm.tradefree.Register

import android.annotation.SuppressLint
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_register.*
import mx.itesm.tradefree.BaseFragment
import mx.itesm.tradefree.Home.ActivityHome
import mx.itesm.tradefree.Models.User

import mx.itesm.tradefree.R
import java.text.SimpleDateFormat
import java.util.*

class FragmentRegister : BaseFragment(), View.OnClickListener {

    private lateinit var viewModelRegister: ViewModelRegister
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelRegister = ViewModelProviders.of(this).get(ViewModelRegister::class.java)
        val root = inflater.inflate(R.layout.fragment_register, container, false)

        // Buttons
        val btnCreateAccountRegister: Button = root.findViewById(R.id.btnCreateAccountRegister)
        btnCreateAccountRegister.setOnClickListener(this)

        // Initialize Firebase Auth
        firebaseInit()

        // Initialize Firebase Database
        firebaseDatabaseInt()

        return root
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btnCreateAccountRegister -> signUpWithEmailPassword(inputEmailRegister.text.toString(), inputPasswordRegister.text.toString())
        }
    }

    /**
     *  Firebase initialization.
     */
    private fun firebaseInit() {
        auth = FirebaseAuth.getInstance()
    }

    /**
     *  Firebase Database initialization.
     */
    private fun firebaseDatabaseInt() {
        db = FirebaseDatabase.getInstance()
    }

    /**
     *  This method creates a new user with an email and password.
     */
    private fun signUpWithEmailPassword(email: String, password: String) {
        view?.let { hideKeyboard(it) }
        Log.d(TAG, "createAccount:$email")
        if (!validateForm()) {
            Toast.makeText(activity, REGISTER_ERROR, Toast.LENGTH_LONG).show()
            return
        }
        showProgressDialog()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    onAuthSuccess(it.result?.user!!)
                } else {
                    Toast.makeText(activity, REGISTER_ERROR, Toast.LENGTH_LONG).show()
                }
                hideProgressDialog()
            }
    }

    /**
     * Validates if the email and password inputs are not blank.
     *
     * @return  If the user correctly fill their email and password.
     */
    private fun validateForm(): Boolean {
        var valid = true
        val name = inputNameRegister.text.toString()
        val lastName = inputLastNameRegister.toString()
        val email = inputEmailRegister.text.toString()
        val password = inputPasswordRegister.text.toString()
        val retypePassword = inputRetypePasswordRegister.text.toString()
        if (name.isEmpty()) valid = false
        if (lastName.isEmpty()) valid = false
        if (email.isEmpty()) valid = false
        if (password.isEmpty()) valid = false
        if (retypePassword.isEmpty()) valid = false
        if (password != retypePassword) valid = false
        return valid
    }

    /**
     *  This method is called if authentication success.
     */
    private fun onAuthSuccess(user: FirebaseUser) {
        val name = inputNameRegister.text.toString()
        val lastName = inputLastNameRegister.text.toString()
        // Write new user
        writeNewUser(user.uid, "$name $lastName", user.email)

        // Go to ActivityHome
        val intent = Intent(context, ActivityHome::class.java)
        startActivity(intent)
        activity?.finishAffinity()
    }

    /**
     *  This method creates a new user in the database.
     */
    private fun writeNewUser(userId: String, name: String, email: String?) {
        val currentDate = getDate()
        val user = User(name, email!!,"buyer", currentDate, listOf())
        db.reference.child("/users").child(userId).setValue(user)
    }

    /**
     *  This method return the current system date.
     *  @return Current system date.
     */
    @SuppressLint("SimpleDateFormat")
    private fun getDate(): String {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        return sdf.format(Date())
    }

    companion object {
        private const val TAG = "REGISTER"
        private const val REGISTER_ERROR = "Error al registrar usuario: Verifica correctamente tus datos."
    }
}
