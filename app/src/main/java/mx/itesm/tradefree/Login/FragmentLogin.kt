package mx.itesm.tradefree.Login

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_login.*
import mx.itesm.tradefree.BaseFragment
import mx.itesm.tradefree.Home.ActivityHome
import mx.itesm.tradefree.R
import mx.itesm.tradefree.Register.ActivityRegister


class FragmentLogin : BaseFragment(), View.OnClickListener {

    private lateinit var viewModelLogin: ViewModelLogin
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelLogin = ViewModelProviders.of(this).get(ViewModelLogin::class.java)
        val root = inflater.inflate(R.layout.fragment_login, container, false)

        // Buttons
        val btnRegister: Button = root.findViewById(R.id.btnRegistrate)
        val btnLogin: Button = root.findViewById(R.id.btnLogin)
        btnRegister.setOnClickListener(this)
        btnLogin.setOnClickListener(this)

        // Initialize Firebase Auth
        firebaseInit()

        return root

    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        Log.d("AUTH", currentUser?.email.toString())
    }

    override fun onResume() {
        showProgressDialog()
        if (auth.currentUser != null) {
            val intent = Intent(activity, ActivityHome::class.java)
            startActivity(intent)
        }
        hideProgressDialog()
        super.onResume()
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btnRegistrate -> signUpWithEmailPassword()
            R.id.btnLogin -> signInWithEmailPassword(inputEmailLogin.text.toString(), inputPasswordLogin.text.toString())
        }
    }

    /**
     *  Firebase initialization.
     */
    private fun firebaseInit() {
        auth = FirebaseAuth.getInstance()
    }

    /**
     * This method authenticates the user with his email and password.
     *
     * @param email     the user email retrieved from inputEmailLogin
     * @param password  the user password retrieved from inputPasswordlLogin
     * @return          if the user fill blank values
     */
    private fun signInWithEmailPassword(email: String, password: String) {
        view?.let { hideKeyboard(it) }
        if (!validateForm()) {
            Toast.makeText(activity, LOGIN_ERROR, Toast.LENGTH_LONG).show()
            return
        }
        showProgressDialog()
        Log.d(TAG, email + password)
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val intent = Intent(context, ActivityHome::class.java)
                    startActivity(intent)
                    activity?.finishAffinity()
                } else {
                    inputEmailLogin.text.clear()
                    inputPasswordLogin.text.clear()
                    Toast.makeText(activity, LOGIN_ERROR, Toast.LENGTH_LONG).show()
                }
                hideProgressDialog()
            }
    }

    /**
     * This method creates user registration activity.
     */
    private fun signUpWithEmailPassword() {
        showProgressDialog()
        val intent = Intent(context, ActivityRegister::class.java)
        startActivity(intent)
        hideProgressDialog()
    }

    /**
     * Validates if the email and password inputs are not blank.
     *
     * @return  If the user correctly fill their email and password.
     */
    private fun validateForm(): Boolean {
        var valid = true
        val email = inputEmailLogin.text.toString()
        val password = inputPasswordLogin.text.toString()
        if (email.isEmpty()) valid = false
        if (password.isEmpty()) valid = false
        return valid
    }

    companion object {
        private const val TAG = "LOGIN"
        private const val LOGIN_ERROR = "Ingrese correctamente su correo y/o contraseña."
    }

}
