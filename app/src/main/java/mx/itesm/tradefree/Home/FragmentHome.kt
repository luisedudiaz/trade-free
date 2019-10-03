package mx.itesm.tradefree.Home

import android.app.ProgressDialog
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import mx.itesm.tradefree.Login.ViewModelLogin

import mx.itesm.tradefree.R
import mx.itesm.tradefree.Register.ActivityRegister

class FragmentHome : Fragment() {

    private lateinit var viewModelHome: ViewModelHome
    private lateinit var dialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelHome = ViewModelProviders.of(this).get(ViewModelHome::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root

    }

    private fun mostrarDialogoEspera() {
        this.dialog = ProgressDialog(context)
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        dialog.setMessage("Descargando")
        dialog.isIndeterminate = true
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }




}
