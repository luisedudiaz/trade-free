package mx.itesm.tradefree.Home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import mx.itesm.tradefree.R

class FragmentHome : Fragment() {

    companion object {
        fun newInstance() = FragmentHome()
    }

    private lateinit var viewModelHome: ViewModelHome

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelHome = ViewModelProviders.of(this).get(ViewModelHome::class.java)
        // TODO: Use the ViewModel
    }

}
