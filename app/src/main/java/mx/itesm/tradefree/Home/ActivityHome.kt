package mx.itesm.tradefree.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_home.*
import mx.itesm.tradefree.BaseActivity
import mx.itesm.tradefree.Login.ActivityLogin
import mx.itesm.tradefree.R

class ActivityHome : BaseActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Initialize Firebase Auth.
        firebaseInit()

        // Floating Button.
        fabhome.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        Log.d(TAG, currentUser?.email.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.profile_menu -> true
            R.id.logout_menu -> {
                showProgressDialog()
                val intent = Intent(this, ActivityLogin::class.java)
                signOut()
                startActivity(intent)
                hideProgressDialog()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     *  This method ends the user session.
     */
    private fun signOut() {
        finishAffinity()
        auth.signOut()
    }

    /**
     *  Firebase initialization.
     */
    private fun firebaseInit() {
        auth = FirebaseAuth.getInstance()
    }

    companion object {
        private const val TAG = "ACTIVITY_HOME"
    }
}
