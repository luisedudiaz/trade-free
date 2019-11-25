package mx.itesm.tradefree.view.product

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.synnapps.carouselview.CarouselView
import mx.itesm.tradefree.view.base.BaseActivity
import mx.itesm.tradefree.R
import mx.itesm.tradefree.model.models.Product.Product
import com.androidnetworking.error.ANError
import android.graphics.Bitmap
import android.text.Editable
import com.androidnetworking.interfaces.BitmapRequestListener
import com.androidnetworking.AndroidNetworking
import android.util.Log
import android.widget.*
import com.androidnetworking.common.Priority
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.activity_product.*
import mx.itesm.tradefree.model.models.User.User
import mx.itesm.tradefree.presenter.contracts.IProfileSellerContract
import mx.itesm.tradefree.presenter.presenters.ProfileSellerPresenter
import mx.itesm.tradefree.view.message.ActivityMessage
import java.io.Serializable


class ActivityProduct : BaseActivity(), View.OnClickListener, IProfileSellerContract.View,
    ImageListener {
    override fun onProductSuccess(product: Product) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDataUpdateSuccess(messageSuccess: String) {
        Toast.makeText(this, messageSuccess, Toast.LENGTH_LONG).show()
    }

    override fun onDataUpdateError(messageError: String) {
        Toast.makeText(this, messageError, Toast.LENGTH_LONG).show()
    }


    private lateinit var profileSellerPresenter: ProfileSellerPresenter
    private lateinit var txtTitleProduct: TextView
    private lateinit var txtDescriptionProduct: TextView
    private lateinit var txtTitleEditProduct: EditText
    private lateinit var txtDescriptionEditProduct: EditText
    private lateinit var btnSendMessageProduct: Button
    private lateinit var btnEditProduct: Button
    private lateinit var btnProfileSellerProduct: TextView
    private lateinit var carouselView: CarouselView
    private lateinit var product: Product
    private lateinit var user: User
    private var bitmapArray = ArrayList<Bitmap>()

    override fun setImageForPosition(position: Int, imageView: ImageView?) {
        imageView?.setImageBitmap(bitmapArray[position])
    }

    override fun onDataImageSuccess(bitmap: Bitmap) {
        bitmapArray.add(bitmap)
        if (bitmapArray.size == product.images.size) {
            Log.d("IMAGES", bitmapArray.size.toString())
            carouselView.setImageListener(this)
            carouselView.pageCount = bitmapArray.size

        }
    }

    override fun onDataUserSuccess(user: User, uid: String) {
        this.user = user
        if (product.user.id == uid) {
            btnSendMessageProduct.visibility = View.INVISIBLE
            txtTitleProduct.visibility = View.INVISIBLE
            txtDescriptionProduct.visibility = View.INVISIBLE
            btnEditProduct.visibility = View.VISIBLE
            txtTitleEditProduct.visibility = View.VISIBLE
            txtDescriptionEditProduct.visibility = View.VISIBLE

        }
        setViews()
    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.btnSendMessageProduct -> goToMessage()
            R.id.btnEditProduct -> editProduct()
        }
    }

    private fun editProduct() {
        Log.d("PRODUCTS", product.title)
        product.title = txtTitleEditProduct.text.toString()
        product.description = txtDescriptionEditProduct.text.toString()
        profileSellerPresenter.updateProduct(product)
    }

    private fun goToMessage() {
        val intent = Intent(this, ActivityMessage::class.java)
        intent.putExtra("USER", user as Serializable)
        intent.putExtra("USERID", product.user.id)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        // Initialize Floating Button
        product = intent.getSerializableExtra("PRODUCT") as Product
        Log.d("PRODUCT", product.title)
        initViews()
        getUserData()
    }



    private fun getUserData() {
        profileSellerPresenter = ProfileSellerPresenter(this)
        profileSellerPresenter.getUserData(product.user.id)
        profileSellerPresenter.getImages(product.images)
    }

    private fun setViews() {
        txtTitleProduct.text = product.title
        txtTitleEditProduct.setText(product.title)
        txtDescriptionProduct.text = product.description
        txtDescriptionEditProduct.setText(product.description)
        btnProfileSellerProduct.text = product.user.name
    }

    private fun initViews() {
        carouselView = this.findViewById(R.id.carouselViewSeeMore)
        txtTitleProduct = this.findViewById(R.id.txtTitleProduct)
        txtDescriptionProduct = this.findViewById(R.id.txtDescriptionProduct)
        txtTitleEditProduct = this.findViewById(R.id.editTextTitle)
        txtDescriptionEditProduct = this.findViewById(R.id.editTextDescription)
        btnSendMessageProduct = this.findViewById(R.id.btnSendMessageProduct)
        btnEditProduct = this.findViewById(R.id.btnEditProduct)
        btnProfileSellerProduct = this.findViewById(R.id.btnProfileSellerProduct)
        btnSendMessageProduct.setOnClickListener(this)
        btnEditProduct.setOnClickListener(this)

    }
}
