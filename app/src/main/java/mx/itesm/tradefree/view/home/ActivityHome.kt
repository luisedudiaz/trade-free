package mx.itesm.tradefree.view.home

import android.os.Bundle
import android.util.Log
import mx.itesm.tradefree.view.base.BaseActivity
import mx.itesm.tradefree.R

class ActivityHome : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setOnClickListenerFloatingButton(findViewById(R.id.fabMessage))
    }

    companion object {
        private const val TAG = "ACTIVITY_HOME"
    }
}
