package mx.itesm.tradefree.Profile

import android.os.Bundle
import android.util.Log
import mx.itesm.tradefree.BaseActivity
import mx.itesm.tradefree.R

class ActivityProfile : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Google Sign In
        googleSignIn()

        // Initialize Firebase Auth.
        firebaseInit()

        // Initialize Floating Button
        setOnClickListenerFloatingButton(findViewById(R.id.fabMessage))
    }
}
