package mx.itesm.tradefree.Login

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.itesm.tradefree.R


class FragmentLogin : Fragment() {

    companion object {
        fun newInstance() = FragmentLogin()
    }

    private lateinit var viewModelLogin: ViewModelLogin

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelLogin = ViewModelProviders.of(this).get(ViewModelLogin::class.java)
        // TODO: Use the ViewModel
    }

}
