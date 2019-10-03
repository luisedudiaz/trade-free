package mx.itesm.tradefree.Register

import android.app.ProgressDialog
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import mx.itesm.tradefree.R

class FragmentRegister : Fragment() {

    private lateinit var viewModelRegister: ViewModelRegister
    private lateinit var dialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelRegister = ViewModelProviders.of(this).get(ViewModelRegister::class.java)
        val root = inflater.inflate(R.layout.fragment_register, container, false)
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
