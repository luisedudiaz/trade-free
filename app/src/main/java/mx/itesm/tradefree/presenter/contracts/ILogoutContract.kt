package mx.itesm.tradefree.presenter.contracts

import android.app.Activity

interface ILogoutContract {

    interface Presenter {
        fun signout(activity: Activity)
        fun isAuthenticated() : Boolean
    }

    interface Intractor {
        fun performSignout(activity: Activity)
        fun isAuthenticated() : Boolean
    }
}