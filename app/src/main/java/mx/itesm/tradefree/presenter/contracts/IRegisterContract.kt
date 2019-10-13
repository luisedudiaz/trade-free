package mx.itesm.tradefree.presenter.contracts

import android.app.Activity

interface IRegisterContract {
    interface View {
        fun onRegisterSuccess(message: String)
        fun onRegisterFailure(message: String)
    }

    interface Presenter {
        fun register(activity: Activity, name: String, password: String, email: String)
    }

    interface Interactor {
        fun performFirebaseRegister(activity: Activity, name: String, password: String, email: String)
    }

    interface onRegisterListener {
        fun onSuccess(message: String)
        fun onFailure(message: String)
    }
}