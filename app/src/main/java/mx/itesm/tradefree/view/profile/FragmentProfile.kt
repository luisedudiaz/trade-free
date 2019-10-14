package mx.itesm.tradefree.view.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.Toast
import mx.itesm.tradefree.view.addproduct.ActivityAddProduct
import mx.itesm.tradefree.view.base.BaseFragment

import mx.itesm.tradefree.R
import mx.itesm.tradefree.model.utils.enums.UserType
import mx.itesm.tradefree.presenter.contracts.IProfileContract
import mx.itesm.tradefree.presenter.presenters.ProfilePresenter

class FragmentProfile : BaseFragment(), View.OnClickListener,
    CompoundButton.OnCheckedChangeListener, IProfileContract.View{

    private lateinit var profilePresenter: ProfilePresenter
    private lateinit var root: View
    private lateinit var btnAddProduct: Button
    private lateinit var switch: Switch

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_profile, container, false)
        initViews()
        return root
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnAddProductProfile -> goToAddProduct()
        }
    }

    override fun onCheckedChanged(cb: CompoundButton?, isActive: Boolean) {
        when(cb?.id) {
            R.id.swTypeUser -> changeTypeUser(isActive)
        }
    }

    override fun onDataProfileSuccess(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun onDataProfileFailure(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    private fun changeTypeUser(active: Boolean) {
        Log.d("ACTIVE", active.toString())
        if (active) activity?.let { profilePresenter.updateTypeUser(it, UserType.SELLER) }
        else activity?.let { profilePresenter.updateTypeUser(it, UserType.BUYER) }
    }

    /**
     * Initialize view components
     */
    private fun initViews() {
        profilePresenter = ProfilePresenter(this)
        //Buttons
        btnAddProduct = root.findViewById(R.id.btnAddProductProfile)
        switch = root.findViewById(R.id.swTypeUser)
        // ButtonsListerners
        btnAddProduct.setOnClickListener(this)
        switch.setOnCheckedChangeListener(this)
    }

    private fun goToAddProduct() {
        val intent= Intent(context, ActivityAddProduct::class.java)
        startActivity(intent)
    }

}
