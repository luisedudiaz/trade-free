package mx.itesm.tradefree.view.home

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
import mx.itesm.tradefree.view.AdapterHome

class FragmentHome : BaseFragment(), IHomeContract.View {

    private lateinit var homePresenter: HomePresenter
    private lateinit var root: View
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_home, container, false)
        initViews()
        getProducts()
        return root
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
        val layout = LinearLayoutManager(context)
        layout.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layout
        val adapterProducts = context?.let { AdapterHome(it, products) }
        recyclerView.adapter = adapterProducts
        hideProgressDialog()
    }

    override fun onDataFailture(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
