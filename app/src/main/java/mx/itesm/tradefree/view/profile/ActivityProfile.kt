package mx.itesm.tradefree.view.profile

import android.os.Bundle
import mx.itesm.tradefree.view.base.BaseActivity
import mx.itesm.tradefree.R


class ActivityProfile : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        // Initialize Floating Button
        setOnClickListenerFloatingButton(findViewById(R.id.fabMessage))
    }
}
