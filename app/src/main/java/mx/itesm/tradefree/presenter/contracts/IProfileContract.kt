package mx.itesm.tradefree.presenter.contracts

import android.app.Activity
import mx.itesm.tradefree.model.models.User.User
import mx.itesm.tradefree.model.utils.enums.UserType

interface IProfileContract {
    interface View {
        fun onTypeProfileSuccess(message: String)
        fun onTypeProfileFailure(message: String)
        fun onDataProfileSuccess(user: User)
        fun onProfileUpdateSuccess(message: String)
        fun onProfileUpdateFailure(message: String)
    }

    interface Presenter {
        fun updateTypeUser(activity: Activity, userType: UserType)
        fun updateUserInfo(activity: Activity, user: User)
        fun getUserInfo()
    }

    interface Interactor {
        fun performUpdateTypeUser(activity: Activity, userType: UserType)
        fun performUpdateUserInfo(activity: Activity, user: User)
        fun getUserInfo()
    }

    interface onProfileListener {
        fun onSuccess(message: String)
        fun onFailure(message: String)
        fun onDataSuccess(user: User)
        fun onProfileUpdateSuccess(message: String)
        fun onProfileUpdateFailure(message: String)
    }
}