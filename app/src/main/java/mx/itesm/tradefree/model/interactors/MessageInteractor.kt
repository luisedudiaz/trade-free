package mx.itesm.tradefree.model.interactors

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import mx.itesm.tradefree.model.models.Message.Message
import mx.itesm.tradefree.model.utils.classes.FirebaseManager
import mx.itesm.tradefree.presenter.contracts.IMessegeContract

class MessageInteractor(private val messageInteractor: IMessegeContract.onMessageListener): FirebaseManager(), IMessegeContract.Interactor {
    override fun getMessages(uid: String) {
        db.reference.child("chats").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                var messages = ArrayList<Message>()
                for (snapshot in p0.children) {
                    val message = snapshot.getValue(Message::class.java)
                    if (message?.receiver == auth.currentUser?.uid && message?.sender == uid  || message?.receiver == uid && message?.sender == auth.currentUser?.uid) {
                        messages.add(message)
                    }
                }
                messageInteractor.onSuccessGetMessages(messages, auth.currentUser?.uid.toString())
            }

        })
    }

    override fun setMessage(hash: HashMap<String, Any>) {
        hash.put("sender", auth.currentUser?.uid.toString())
        db.reference.child("chats").push().setValue(hash)
    }
}