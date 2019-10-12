package mx.itesm.tradefree.view.profile

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import mx.itesm.tradefree.view.addproduct.ActivityAddProduct
import mx.itesm.tradefree.view.base.BaseFragment

import mx.itesm.tradefree.R
//import mx.itesm.tradefree.app.viewmodels.ViewModelProfile

class FragmentProfile : BaseFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnAddProductProfile -> goToAddProduct()
        }
    }

    private fun goToAddProduct() {
        val intent= Intent(context, ActivityAddProduct::class.java)
        startActivity(intent)
    }

//    private lateinit var viewModelProfile: ViewModelProfile

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
  //      viewModelProfile = ViewModelProviders.of(this).get(ViewModelProfile::class.java)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        //Buttons
        val btnAddProduct: Button=root.findViewById(R.id.btnAddProductProfile)
        btnAddProduct.setOnClickListener(this)
        return root

    }

}
