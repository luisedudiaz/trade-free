package mx.itesm.tradefree.view.product

import android.os.Bundle
import mx.itesm.tradefree.view.base.BaseActivity
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
