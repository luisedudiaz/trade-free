package mx.itesm.tradefree.view.message

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_chat_to_row.view.*
import mx.itesm.tradefree.R
import mx.itesm.tradefree.model.models.Message.Message

class AdapterMessage(private val ctxt: Context, private val messages: ArrayList<Message>, private val currentUser: String): RecyclerView.Adapter<AdapterMessage.MessageView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageView {
        if (viewType == MSG_TYPE_RIGHT) {
            val view = LayoutInflater.from(ctxt).inflate(R.layout.adapter_chat_to_row, parent, false)
            return MessageView(view)
        } else {
            val view = LayoutInflater.from(ctxt).inflate(R.layout.adapter_chat_from_row, parent, false)
            return MessageView(view)
        }

    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onBindViewHolder(holder: MessageView, position: Int) {
        val message = messages[position]
        holder.set(message)

    }

    override fun getItemViewType(position: Int): Int {

        return if (messages[position].sender == currentUser) {
            MSG_TYPE_RIGHT
        } else {
            MSG_TYPE_LEFT
        }
    }

    inner class MessageView(var messageView: View): RecyclerView.ViewHolder(messageView) {

        fun set(message: Message) {
            messageView.txtTo.text = message.message
        }
    }

    companion object {
        private const val MSG_TYPE_LEFT = 0
        private const val MSG_TYPE_RIGHT = 1
    }

}