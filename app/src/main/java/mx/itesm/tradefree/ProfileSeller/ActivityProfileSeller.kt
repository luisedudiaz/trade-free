package mx.itesm.tradefree.ProfileSeller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Switch
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_profile_seller.*
import kotlinx.android.synthetic.main.fragment_profile_seller.*
import mx.itesm.tradefree.Home.ActivityHome
import mx.itesm.tradefree.Login.ActivityLogin
import mx.itesm.tradefree.R

class ActivityProfileSeller : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_seller)
        fabprofiles.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val swVendedor = findViewById<Switch>(R.id.swComprador)
        swVendedor?.setOnCheckedChangeListener({ _, isChecked ->
            if (isChecked)  else swComprador_on()
        })
    }

    private fun swComprador_on() {
        val intent = Intent(this, ActivityHome::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.profile_menu -> true
            R.id.logout_menu -> {
                val intent = Intent(this, ActivityLogin::class.java)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }




}
