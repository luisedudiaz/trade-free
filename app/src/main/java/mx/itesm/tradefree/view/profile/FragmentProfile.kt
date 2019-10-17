package mx.itesm.tradefree.view.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.fragment_profile.*
import mx.itesm.tradefree.R
import mx.itesm.tradefree.model.models.User
import mx.itesm.tradefree.model.utils.enums.UserType
import mx.itesm.tradefree.presenter.contracts.IProfileContract
import mx.itesm.tradefree.presenter.presenters.ProfilePresenter
import mx.itesm.tradefree.view.addproduct.ActivityAddProduct
import mx.itesm.tradefree.view.base.BaseFragment

class FragmentProfile : BaseFragment(), View.OnClickListener,
    CompoundButton.OnCheckedChangeListener, IProfileContract.View{

    private lateinit var profilePresenter: ProfilePresenter
    private lateinit var root: View
    private lateinit var btnAddProduct: Button
    private lateinit var switch: Switch
    private lateinit var inputNameProfile: EditText
    private lateinit var inputEmailProfile: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_profile, container, false)
        initViews()
        showProgressDialog()
        getUserData()
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

    override fun onTypeProfileSuccess(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun onTypeProfileFailure(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun onDataProfileSuccess(user: User) {
        inputNameProfile.setText(user.name)
        inputEmailProfile.setText(user.email)
        switch.isChecked = user.type == UserType.SELLER
        switch.setOnCheckedChangeListener(this)
        hideProgressDialog()
    }

    /**
     * This method call updateTypeUser to change it in the database.
     */
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
        // Inputs
        inputNameProfile = root.findViewById(R.id.inputNameProfile)
        inputEmailProfile = root.findViewById(R.id.inputEmailProfile)
        //Buttons
        btnAddProduct = root.findViewById(R.id.btnAddProductProfile)
        switch = root.findViewById(R.id.swTypeUser)
        // ButtonsListerners
        btnAddProduct.setOnClickListener(this)
    }


    /**
     * This method gets the user information from database
     */
    private fun getUserData() {
        profilePresenter.getUserInfo()
    }

    /**
     * This method send to add product activity.
     */
    private fun goToAddProduct() {
        val intent= Intent(context, ActivityAddProduct::class.java)
        startActivity(intent)
        activity?.finish()
        visible_action(this)
    }

    private fun visible_action(v: FragmentProfile){
        btnAddProductProfile.visibility = View.VISIBLE

    }

}
