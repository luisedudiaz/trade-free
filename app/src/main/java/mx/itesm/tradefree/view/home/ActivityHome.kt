package mx.itesm.tradefree.view.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import mx.itesm.tradefree.view.base.BaseActivity
import mx.itesm.tradefree.R
import mx.itesm.tradefree.model.models.Product.Product
import mx.itesm.tradefree.presenter.contracts.IHomeContract
import mx.itesm.tradefree.presenter.presenters.HomePresenter
import mx.itesm.tradefree.view.product.ActivityProduct
import mx.itesm.tradefree.view.profileseller.ActivityProfileSeller
import java.io.Serializable

class ActivityHome :
    BaseActivity(),
    IHomeContract.View,
    AdapterHome.onProductCardListener, SearchView.OnQueryTextListener {

    private lateinit var homePresenter: HomePresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var products: MutableList<Product>
    private lateinit var menuItem: MenuItem
    private lateinit var searchView: SearchView
    private lateinit var adapterHome: AdapterHome

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        AndroidNetworking.initialize(this)
        initViews()
        getProducts()
    }

    companion object {
        private const val TAG = "ACTIVITY_HOME"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        menuInflater.inflate(R.menu.search_menu, menu)

        menuItem = menu.findItem(R.id.action_search)
        searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return true
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        var textInput = p0?.toLowerCase()
        var newList = mutableListOf<Product>()
        Log.d("ADAPTER", textInput.toString())
        for (product in this.products) {
            if (product.title.toLowerCase().contains(textInput.toString())) {
                newList.add(product)
            }
        }
        adapterHome.updateList(newList)
        return true
    }

    override fun onDeleteProduct(position: Int) {

    }

    override fun onProfileSellerClick(position: Int) {
        val intent = Intent(this, ActivityProfileSeller::class.java)
        intent.putExtra("PRODUCT", products[position] as Serializable)
        startActivity(intent)
    }

    override fun onSeeMoreClick(position: Int) {
        val intent = Intent(this, ActivityProduct::class.java)
        intent.putExtra("PRODUCT", products[position] as Serializable)
        startActivity(intent)
    }

    private fun getProducts() {
        showProgressDialog()
        homePresenter.getProducts()
    }

    private fun initViews() {
        homePresenter = HomePresenter(this)
        recyclerView = this.findViewById(R.id.recyclerViewHome)
    }

    override fun onDataSuccess(products: MutableList<Product>) {
        this.products = products

        val layout = LinearLayoutManager(this)
        layout.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layout
        adapterHome = this?.let {
            AdapterHome(it, this.products, this)
        }
        recyclerView.adapter = adapterHome
        hideProgressDialog()

    }

    override fun onDataFailture(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
