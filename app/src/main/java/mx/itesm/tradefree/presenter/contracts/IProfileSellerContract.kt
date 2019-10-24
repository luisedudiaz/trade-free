package mx.itesm.tradefree.presenter.contracts

import mx.itesm.tradefree.model.models.User.User

interface IProfileSellerContract {
    interface View {
        fun onDataUserSuccess(user: User, uid: String)
    }

    interface Presenter {
        fun getUserData(uid: String)
    }

    interface Interactor {
        fun performUserData(uid: String)
    }

    interface onProfileSellerListener {
        fun onSuccessUser(user: User, uid: String)
    }
}