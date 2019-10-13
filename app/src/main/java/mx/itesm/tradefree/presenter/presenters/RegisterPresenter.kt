package mx.itesm.tradefree.presenter.presenters

import android.app.Activity
import mx.itesm.tradefree.model.interactors.RegisterInteractor
import mx.itesm.tradefree.presenter.contracts.IRegisterContract

class RegisterPresenter(private val registerView: IRegisterContract.View): IRegisterContract.Presenter, IRegisterContract.onRegisterListener {

    private val registerInteractor: RegisterInteractor = RegisterInteractor(this)

    override fun register(activity: Activity, name: String, password: String, email: String) {
        registerInteractor.performFirebaseRegister(activity, name, password, email)
    }

    override fun onSuccess(message: String) {
        registerView.onRegisterSuccess(message)
    }

    override fun onFailure(message: String) {
        registerView.onRegisterFailure(message)
    }
}