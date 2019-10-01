package mx.itesm.tradefree.ProfileSeller

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import mx.itesm.tradefree.R

class FragmentProfileSeller : Fragment() {

    companion object {
        fun newInstance() = FragmentProfileSeller()
    }

    private lateinit var viewModel: ViewModeltProfileSeller

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_seller, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ViewModeltProfileSeller::class.java)
        // TODO: Use the ViewModel
    }

}
