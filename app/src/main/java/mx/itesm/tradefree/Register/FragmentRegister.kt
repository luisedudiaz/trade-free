package mx.itesm.tradefree.Register

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
import kotlinx.android.synthetic.main.fragment_register.*
import mx.itesm.tradefree.BaseFragment
import mx.itesm.tradefree.Home.ActivityHome

import mx.itesm.tradefree.R

class FragmentRegister : BaseFragment(), View.OnClickListener {

    private lateinit var viewModelRegister: ViewModelRegister
    private lateinit var auth: FirebaseAuth

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

    private fun signUpWithEmailPassword(email: String, password: String) {
        view?.let { hideKeyboard(it) }
        Log.d(TAG, "createAccount:$email")
        if (!validateForm()) {
            return
        }
        showProgressDialog()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    val intent = Intent(context, ActivityHome::class.java)
                    startActivity(intent)
                    activity?.finishAffinity()
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
        val email = inputEmailRegister.text.toString()
        val password = inputPasswordRegister.text.toString()
        if (email.isEmpty()) valid = false
        if (password.isEmpty()) valid = false
        return valid
    }

    companion object {
        private const val TAG = "REGISTER"
        private const val REGISTER_ERROR = "Error al registrar usuario: Verifica correctamente tus datos."
    }
}
