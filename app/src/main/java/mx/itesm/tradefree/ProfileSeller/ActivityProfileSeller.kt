package mx.itesm.tradefree.ProfileSeller


import android.os.Bundle
import mx.itesm.tradefree.BaseActivity
import mx.itesm.tradefree.R

class ActivityProfileSeller : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_seller)

        // Google Sign In
        googleSignIn()

        // Initialize Firebase Auth.
        firebaseInit()

        // Initialize Floating Button
        setOnClickListenerFloatingButton(findViewById(R.id.fabMessage))
    }
}
