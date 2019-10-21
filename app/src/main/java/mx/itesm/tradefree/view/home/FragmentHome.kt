package mx.itesm.tradefree.view.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.itesm.tradefree.view.base.BaseFragment
import mx.itesm.tradefree.R
import mx.itesm.tradefree.model.models.Product.Product
import mx.itesm.tradefree.presenter.contracts.IHomeContract
import mx.itesm.tradefree.presenter.presenters.HomePresenter
import mx.itesm.tradefree.view.product.ActivityProduct
import mx.itesm.tradefree.view.profileseller.ActivityProfileSeller

class FragmentHome : BaseFragment(), IHomeContract.View, AdapterHome.onProductCardListener {


    private lateinit var homePresenter: HomePresenter
    private lateinit var root: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var products: MutableList<Product>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_home, container, false)
        initViews()
        getProducts()
        return root
    }

    override fun onProfileSellerClick(position: Int) {
        val intent = Intent(context, ActivityProfileSeller::class.java)
        //intent.putExtra("product", products.get(position))
        startActivity(intent)

    }

    override fun onSeeMoreClick(position: Int) {
        val intent = Intent(context, ActivityProduct::class.java)
        startActivity(intent)
    }

    private fun getProducts() {
        showProgressDialog()
        homePresenter.getProducts()
    }

    private fun initViews() {
        homePresenter = HomePresenter(this)
        recyclerView = root.findViewById(R.id.recyclerViewHome)
    }

    override fun onDataSuccess(products: MutableList<Product>) {
        this.products = products
        val layout = LinearLayoutManager(context)
        layout.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layout
        val adapterProducts = context?.let {
            AdapterHome(
                it,
                this.products,
                this
            )
        }
        recyclerView.adapter = adapterProducts
        hideProgressDialog()
    }

    override fun onDataFailture(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
