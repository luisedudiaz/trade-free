package mx.itesm.tradefree.Product

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import mx.itesm.tradefree.R

class FragmentProduct : Fragment() {

    private lateinit var viewModelProduct: ViewModelProduct

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelProduct = ViewModelProviders.of(this).get(ViewModelProduct::class.java)
        val root = inflater.inflate(R.layout.fragment_product, container, false)
        return root
    }

}
