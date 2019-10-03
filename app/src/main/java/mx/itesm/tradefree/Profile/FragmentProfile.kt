package mx.itesm.tradefree.Profile

import android.app.ProgressDialog
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import mx.itesm.tradefree.R

class FragmentProfile : Fragment() {

    companion object {
        fun newInstance() = FragmentProfile()
    }

    private lateinit var viewModelProfile: ViewModelProfile
    private lateinit var dialog: ProgressDialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelProfile = ViewModelProviders.of(this).get(ViewModelProfile::class.java)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
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
