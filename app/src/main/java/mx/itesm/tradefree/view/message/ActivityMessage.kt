package mx.itesm.tradefree.view.message

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.itesm.tradefree.R
import mx.itesm.tradefree.model.models.Message.Message
import mx.itesm.tradefree.model.models.User.User
import mx.itesm.tradefree.presenter.contracts.IMessegeContract
import mx.itesm.tradefree.presenter.presenters.MessagePresenter
import mx.itesm.tradefree.view.base.BaseActivity

class ActivityMessage : BaseActivity(), IMessegeContract.View, View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnSend -> sendMessage()
        }
    }

    private fun sendMessage() {
        Log.d("flag", (txtToSend.text != "").toString())
        if (!txtToSend.text.isEmpty()) {
            val hashMap = HashMap<String, Any>()
            hashMap.put("receiver", uid)
            hashMap.put("message", txtToSend.text.toString())
            txtToSend.text = ""
            messagePresenter.setMessage(hashMap)
        }

    }

    override fun onSuccessGetMessages(messages: ArrayList<Message>, uid: String) {
        val layout = LinearLayoutManager(this)
        layout.orientation = LinearLayoutManager.VERTICAL
        recylerView.layoutManager = layout

        val adapterChats = AdapterMessage(this, messages, uid)
        recylerView.adapter = adapterChats
        recylerView.scrollToPosition(messages.size - 1);
    }

    override fun onSuccessSetMessages() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var messagePresenter: MessagePresenter
    private lateinit var recylerView: RecyclerView
    private lateinit var btnSendMessage: Button
    private lateinit var txtToSend: TextView
    private lateinit var user: User
    private lateinit var uid: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        initViews()
        getMessages()
/*
        val layout = LinearLayoutManager(this)
        layout.orientation = LinearLayoutManager.VERTICAL
        recylerView.layoutManager = layout

        val adapterChats = AdapterChat(this)
        recylerView.adapter = adapterChats

        */

    }

    private fun getMessages() {
        messagePresenter.getMessages(uid)
    }

    private fun initViews() {
        messagePresenter = MessagePresenter(this)
        recylerView = this.findViewById(R.id.recyclerVIewChat)
        btnSendMessage = this.findViewById(R.id.btnSend)
        txtToSend = this.findViewById(R.id.txtToSend)

        user = intent.getSerializableExtra("USER") as User
        uid = intent.getStringExtra("USERID")

        btnSendMessage.setOnClickListener(this)
        supportActionBar?.title = user.name
    }
}
