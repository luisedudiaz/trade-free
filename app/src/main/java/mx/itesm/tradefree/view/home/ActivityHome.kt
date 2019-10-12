package mx.itesm.tradefree.view.home

import android.os.Bundle
import android.util.Log
import mx.itesm.tradefree.view.base.BaseActivity
import mx.itesm.tradefree.R

class ActivityHome : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Google Sign In
        googleSignIn()

        // Initialize Firebase Auth.
        firebaseInit()

        // Initialize Floating Button
        setOnClickListenerFloatingButton(findViewById(R.id.fabMessage))
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth?.currentUser
        Log.d(TAG, currentUser?.email.toString())
    }


    companion object {
        private const val TAG = "ACTIVITY_HOME"
    }
}
