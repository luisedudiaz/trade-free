package mx.itesm.tradefree.view.profileseller


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import mx.itesm.tradefree.view.base.BaseActivity
import mx.itesm.tradefree.R
import mx.itesm.tradefree.model.models.Product.Product
import mx.itesm.tradefree.model.models.User.User
import mx.itesm.tradefree.presenter.contracts.IProfileSellerContract
import mx.itesm.tradefree.presenter.presenters.ProfileSellerPresenter
import mx.itesm.tradefree.view.message.ActivityMessage
import java.io.Serializable
import android.graphics.Bitmap
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.itesm.tradefree.presenter.presenters.ProfilePresenter
import mx.itesm.tradefree.view.product.ActivityProduct


class ActivityProfileSeller : BaseActivity(), IProfileSellerContract.View, View.OnClickListener,
    AdapterProfileSeller.onProductCardListener {
    override fun onProductSuccess(product: Product) {
        val intent = Intent(this, ActivityProduct::class.java)
        intent.putExtra("PRODUCT", product as Serializable)
        startActivity(intent)    }

    override fun onSeeMoreClick(position: Int) {
        profileSellerPresenter.getProduct(user.products.toList()[position].second.id)
    }

    override fun onDeleteProduct(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProfileSellerClick(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDataUpdateSuccess(messageSuccess: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDataUpdateError(messageError: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDataImageSuccess(bitmap: Bitmap) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private lateinit var profileSellerPresenter: ProfileSellerPresenter
    private lateinit var product: Product
    private lateinit var user: User
    private lateinit var txtNameSeller : TextView
    private lateinit var txtProductsSize : TextView
    private lateinit var btnContactSeller : TextView
    private lateinit var divider: View
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_seller)
        showProgressDialog()
        initViews()
        product = intent.getSerializableExtra("PRODUCT") as Product
        getUserData()
    }



    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnContactSeller -> goToMessage()
        }
    }

    private fun goToMessage() {
        val intent = Intent(this, ActivityMessage::class.java)
        intent.putExtra("USER", user as Serializable)
        intent.putExtra("USERID", product.user.id)
        startActivity(intent)
    }

    private fun getUserData() {
        profileSellerPresenter.getUserData(product.user.id)
    }

    private fun initViews() {
        profileSellerPresenter = ProfileSellerPresenter(this)
        txtNameSeller = this.findViewById(R.id.txtNameSeller)
        txtProductsSize = this.findViewById(R.id.txtProductsSize)
        btnContactSeller = this.findViewById(R.id.btnContactSeller)
        divider = this.findViewById(R.id.divider3)
        btnContactSeller.setOnClickListener(this)
        recyclerView = this.findViewById(R.id.recyclerViewProfileSeller)
    }

    override fun onDataUserSuccess(user: User, uid: String) {
        this.user = user
        if (product.user.id == uid) {
            btnContactSeller.visibility = View.GONE
            divider.visibility = View.GONE
        }
        setValues()
    }

    private fun setValues() {
        Log.d("USER",  user.products.size.toString())
        txtNameSeller.text = product.user.name
        txtProductsSize.text = user.products.size.toString()
        val layout = LinearLayoutManager(this)
        layout.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layout
        val adapaterProfileSeller = AdapterProfileSeller(this, user, this)
        recyclerView.adapter = adapaterProfileSeller
        hideProgressDialog()
    }

}
