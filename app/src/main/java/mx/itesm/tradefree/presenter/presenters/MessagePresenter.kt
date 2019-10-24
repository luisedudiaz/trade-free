package mx.itesm.tradefree.presenter.presenters

import mx.itesm.tradefree.model.interactors.MessageInteractor
import mx.itesm.tradefree.model.models.Message.Message
import mx.itesm.tradefree.presenter.contracts.IMessegeContract
import mx.itesm.tradefree.view.message.ActivityMessage

class MessagePresenter (private val messageView: IMessegeContract.View): IMessegeContract.Presenter, IMessegeContract.onMessageListener {

    private val messageInteractor = MessageInteractor(this)

    override fun getMessages(uid: String) {
        messageInteractor.getMessages(uid)
    }

    override fun setMessage(hash: HashMap<String, Any>) {
        messageInteractor.setMessage(hash)
    }

    override fun onSuccessGetMessages(messages: ArrayList<Message>, uid: String) {
        messageView.onSuccessGetMessages(messages, uid)
    }

    override fun onSuccessSetMessages() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}