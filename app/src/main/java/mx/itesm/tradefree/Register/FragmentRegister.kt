package mx.itesm.tradefree.Register

import android.app.ProgressDialog
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import mx.itesm.tradefree.Register.ActivityRegister

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
        val btnCreateAccountRegister: Button = root.findViewById(R.id.btnCreateAccountRegister)
        btnCreateAccountRegister.setOnClickListener {
            val intent = Intent(context, ActivityRegister::class.java)
            startActivity(intent)
        }

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
