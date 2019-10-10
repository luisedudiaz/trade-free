package mx.itesm.tradefree.Product

import android.os.Bundle
import mx.itesm.tradefree.BaseActivity
import mx.itesm.tradefree.R

class ActivityProduct : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        // Google Sign In
        googleSignIn()

        // Initialize Firebase Auth.
        firebaseInit()

        // Initialize Floating Button
        setOnClickListenerFloatingButton(findViewById(R.id.fabMessage))
    }
}
