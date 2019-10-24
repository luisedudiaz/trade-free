package mx.itesm.tradefree.presenter.contracts

import mx.itesm.tradefree.model.models.Message.Message

interface IMessegeContract {

    interface View {
        fun onSuccessGetMessages(messages: ArrayList<Message>, uid: String)
        fun onSuccessSetMessages()
    }

    interface Presenter {
        fun getMessages(uid: String)
        fun setMessage(hash: HashMap<String, Any>)
    }

    interface Interactor {
        fun getMessages(uid: String)
        fun setMessage(hash: HashMap<String, Any>)
    }

    interface onMessageListener {
        fun onSuccessGetMessages(messages: ArrayList<Message>, uid: String)
        fun onSuccessSetMessages()
    }
}