package mx.itesm.tradefree.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import mx.itesm.tradefree.view.base.BaseFragment
import mx.itesm.tradefree.R
import mx.itesm.tradefree.model.models.Product
import mx.itesm.tradefree.presenter.contracts.IHomeContract
import mx.itesm.tradefree.presenter.presenters.HomePresenter

class FragmentHome : BaseFragment(), IHomeContract.View {
   private lateinit var homePresenter: HomePresenter
    private lateinit var root: View
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
        homePresenter.getProducts()
    }

    private fun initViews() {
        homePresenter = HomePresenter(this)
    }

    override fun onDataSuccess(product: Product) {

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDataFailture(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
