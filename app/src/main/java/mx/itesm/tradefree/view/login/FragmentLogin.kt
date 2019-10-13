    package mx.itesm.tradefree.view.login

    import android.content.Intent
    import android.os.Bundle
    import android.util.Log
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.Button
    import android.widget.Toast
    import com.google.android.gms.auth.api.signin.GoogleSignIn
    import com.google.android.gms.auth.api.signin.GoogleSignInClient
    import com.google.android.gms.auth.api.signin.GoogleSignInOptions
    import com.google.android.gms.common.SignInButton
    import com.google.android.gms.common.api.ApiException
    import kotlinx.android.synthetic.main.fragment_login.*
    import mx.itesm.tradefree.R
    import mx.itesm.tradefree.model.utils.enums.Message
    import mx.itesm.tradefree.presenter.contracts.ILoginContract
    import mx.itesm.tradefree.presenter.presenters.LoginPresenter
    import mx.itesm.tradefree.view.base.BaseFragment
    import mx.itesm.tradefree.view.home.ActivityHome
    import mx.itesm.tradefree.view.register.ActivityRegister

    class FragmentLogin : BaseFragment(), View.OnClickListener, ILoginContract.View {

        private lateinit var loginPresenter: LoginPresenter
        private lateinit var root: View
        private lateinit var btnRegister: Button
        private lateinit var btnLogin: Button
        private lateinit var btnGoogle: SignInButton
        private lateinit var googleSignInClient: GoogleSignInClient


        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            root = inflater.inflate(R.layout.fragment_login, container, false)
            googleSignInInit()
            initViews()
            return root
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
             super.onActivityResult(requestCode, resultCode, data)
             if (requestCode == RC_SIGN_IN) {
                 val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                 try {
                     val account = task.getResult(ApiException::class.java)
                     activity?.let { loginPresenter.loginWithGoogle(it, account) }
                     hideProgressDialog()
                 } catch (e: ApiException) {
                     // Google Sign In failed, update UI appropriately
                     Log.w(TAG, "Google sign in failed", e)
                     Toast.makeText(activity, Message.LOGIN.getMessageError(), Toast.LENGTH_LONG).show()
                 }
             }
         }

        override fun onResume() {
            showProgressDialog()
            if (loginPresenter.isAuthenticated()) {
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
                R.id.btnGoogle -> signInWithGoogle()
            }
        }

        override fun onLoginSuccess(message: String) {
            Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
            val intent = Intent(context, ActivityHome::class.java)
            startActivity(intent)
            activity?.finishAffinity()
        }

        override fun onLoginFailure(message: String) {
            inputEmailLogin.text.clear()
            inputPasswordLogin.text.clear()
            Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
        }

        /**
         * Initialize view components
         */
        private fun initViews() {
            loginPresenter = LoginPresenter(this)
            btnRegister = root.findViewById(R.id.btnRegistrate)
            btnLogin = root.findViewById(R.id.btnLogin)
            btnGoogle = root.findViewById(R.id.btnGoogle)

            // Buttons Listeners
            btnRegister.setOnClickListener(this)
            btnLogin.setOnClickListener(this)
            btnGoogle.setOnClickListener(this)
        }


        /**
         * This method authenticates the user with his email and password.
         *
         * @param email     the user email retrieved from inputEmailLogin
         * @param password  the user password retrieved from inputPasswordlLogin
         *
         */
        private fun signInWithEmailPassword(email: String, password: String) {
            showProgressDialog()
            view?.let { hideKeyboard(it) }
            if (!validateForm()) {
                Toast.makeText(activity, Message.LOGIN.getMessageError(), Toast.LENGTH_LONG).show()
                return
            }
            activity?.let { loginPresenter.login(it, email, password) }
            hideProgressDialog()
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
         * This method sign the user with google.
         */
        private fun signInWithGoogle() {
            showProgressDialog()
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        /**
         *  Google signin init configuration.
         */
        private fun googleSignInInit() {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
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
            private const val TAG = "LOGIN_FRAGMENT"
            private const val RC_SIGN_IN = 9001
        }

    }
