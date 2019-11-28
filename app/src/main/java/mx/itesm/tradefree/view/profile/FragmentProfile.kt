package mx.itesm.tradefree.view.profile

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.itesm.tradefree.R
import mx.itesm.tradefree.model.models.Product.Product
import mx.itesm.tradefree.model.models.User.User
import mx.itesm.tradefree.model.utils.enums.UserType
import mx.itesm.tradefree.presenter.contracts.IProfileContract
import mx.itesm.tradefree.presenter.presenters.ProfilePresenter
import mx.itesm.tradefree.view.addproduct.ActivityAddProduct
import mx.itesm.tradefree.view.base.BaseFragment
import mx.itesm.tradefree.view.product.ActivityProduct
import mx.itesm.tradefree.view.profileseller.ActivityProfileSeller
import java.io.Serializable



class FragmentProfile : BaseFragment(), View.OnClickListener,
    CompoundButton.OnCheckedChangeListener, IProfileContract.View, AdapterProfile.onProductCardListener{
    override fun onProductSuccess(value: Product) {
        val intent = Intent(context, ActivityProduct::class.java)
        intent.putExtra("PRODUCT", value as Serializable)
        startActivity(intent)
    }

    override fun onProfileSellerClick(position: Int) {
        val intent = Intent(context, ActivityProfileSeller::class.java)
        intent.putExtra("PRODUCT", user.products.toList()[position].second as Serializable)
        startActivity(intent)
    }

    override fun onSeeMoreClick(position: Int) {
        profilePresenter.getProduct(user.products.toList()[position].second.id)

    }

    private lateinit var profilePresenter: ProfilePresenter
    private lateinit var root: View
    private lateinit var btnAddProduct: Button
    private lateinit var switch: Switch
    private lateinit var inputNameProfile: EditText
    private lateinit var inputEmailProfile: EditText
    private lateinit var buttonAddProduct: Button
    private lateinit var recyclerViewProducts: RecyclerView
    private lateinit var btnUpdateUserData: Button
    private lateinit var user: User

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
            R.id.btnUpdateUserProfile -> updateUserProfile()
        }
    }

    override fun onCheckedChanged(cb: CompoundButton?, isActive: Boolean) {
        when(cb?.id) {
            R.id.swTypeUser -> changeTypeUser(isActive)
        }
    }

    override fun onTypeProfileSuccess(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
        profilePresenter.deleteProducts()
    }

    override fun onTypeProfileFailure(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun onDataProfileSuccess(user: User) {
        this.user = user
        inputNameProfile.setText(user.name)
        inputEmailProfile.setText(user.email)
        switch.isChecked = user.type == UserType.SELLER
        btnAddProduct.visibility = if (user.type == UserType.SELLER) View.VISIBLE else View.INVISIBLE
        recyclerViewProducts.visibility = if (user.type == UserType.SELLER) View.VISIBLE else View.INVISIBLE
        switch.setOnCheckedChangeListener(this)

        val layout = LinearLayoutManager(context)
        layout.orientation = LinearLayoutManager.VERTICAL
        recyclerViewProducts.layoutManager = layout
        val adapterProfile = context?.let { AdapterProfile(it, user.products.toList(), this) }
        recyclerViewProducts.adapter = adapterProfile
        hideProgressDialog()


    }

    override fun onProfileUpdateSuccess(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun onProfileUpdateFailure(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }


    /**
     * This method call updateTypeUser to change it in the database.
     */
    private fun changeTypeUser(active: Boolean) {
        if (active) {
            AlertDialog.Builder(context).setTitle("Alerta").setMessage("Esta acción cambiara tu tipo de usuario a vendedor.")
                .setPositiveButton(android.R.string.yes
                ) { dialog, which ->

                    activity?.let { profilePresenter.updateTypeUser(it, UserType.SELLER) }
                    btnAddProduct.visibility = View.VISIBLE
                    recyclerViewProducts.visibility = View.VISIBLE

                }
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()

        } else {
            AlertDialog.Builder(context).setTitle("Alerta").setMessage("Al cambiar tu tipo de usuario se eliminaran los productos antes agregados.")
                .setPositiveButton(android.R.string.yes
                ) { dialog, which ->
                    activity?.let { profilePresenter.updateTypeUser(it, UserType.BUYER) }
                    btnAddProduct.visibility = View.GONE
                    recyclerViewProducts.visibility = View.GONE

                }
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
        }



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
        buttonAddProduct = root.findViewById(R.id.btnAddProductProfile)
        btnUpdateUserData = root.findViewById(R.id.btnUpdateUserProfile)
        // RecyclerView
        recyclerViewProducts = root.findViewById(R.id.recyclerViewProductProfile)
        // ButtonsListerners
        btnAddProduct.setOnClickListener(this)
        btnUpdateUserData.setOnClickListener(this)
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
    }

    /**
     * This method update the email or name of the user
     */
    private fun updateUserProfile() {
        val user = User(
            inputNameProfile.text.toString(),
            inputEmailProfile.text.toString()
        )
        activity?.let { profilePresenter.updateUserInfo(it, user) }
        view?.let { hideKeyboard(it) }
    }

}
