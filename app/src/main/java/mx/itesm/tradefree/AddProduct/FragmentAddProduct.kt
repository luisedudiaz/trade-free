package mx.itesm.tradefree.AddProduct

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.itesm.tradefree.BaseFragment

import mx.itesm.tradefree.R

class FragmentAddProduct : BaseFragment() {

    private lateinit var viewModel: ViewModelAddProduct

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_product, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ViewModelAddProduct::class.java)
        // TODO: Use the ViewModel
    }



}
