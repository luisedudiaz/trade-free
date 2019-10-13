package mx.itesm.tradefree.view.register

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_register.*
import mx.itesm.tradefree.R
import mx.itesm.tradefree.model.utils.enums.Message
import mx.itesm.tradefree.presenter.contracts.IRegisterContract
import mx.itesm.tradefree.presenter.presenters.RegisterPresenter
import mx.itesm.tradefree.view.base.BaseFragment
import mx.itesm.tradefree.view.home.ActivityHome

class FragmentRegister : BaseFragment(), View.OnClickListener, IRegisterContract.View {

    private lateinit var registerPresenter: RegisterPresenter
    private lateinit var root: View
    private lateinit var btnCreateAccountRegister: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_register, container, false)
        initViews()
        return root
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btnCreateAccountRegister -> signUpWithEmailPassword(inputEmailRegister.text.toString(), inputPasswordRegister.text.toString())
        }
    }

    override fun onRegisterSuccess(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
        val intent = Intent(context, ActivityHome::class.java)
        startActivity(intent)
        activity?.finishAffinity()
    }

    override fun onRegisterFailure(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
        inputEmailRegister.text.clear()
        inputNameRegister.text.clear()
        inputLastNameRegister.text.clear()
        inputPasswordRegister.text.clear()
        inputRetypePasswordRegister.text.clear()
    }

    /**
     * Initialize view components
     */
    private fun initViews() {
        registerPresenter = RegisterPresenter(this)
        btnCreateAccountRegister = root.findViewById(R.id.btnCreateAccountRegister)

        // Buttons Listeners
        btnCreateAccountRegister.setOnClickListener(this)
    }

    /**
     *  This method creates a new user with an email and password.
     */
    private fun signUpWithEmailPassword(email: String, password: String) {
        showProgressDialog()
        view?.let { hideKeyboard(it) }
        if (!validateForm()) {
            Toast.makeText(activity, Message.REGISTER.getMessageError(), Toast.LENGTH_LONG).show()
            return
        }
        val name = inputNameRegister.text.toString()
        val lastName = inputLastNameRegister.text.toString()
        activity?.let { registerPresenter.register(it, "$name $lastName", password, email) }
        hideProgressDialog()
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

    companion object {
        private const val TAG = "REGISTER"
    }
}
