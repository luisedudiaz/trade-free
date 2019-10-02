package mx.itesm.tradefree.Login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelLogin : ViewModel() {

    private var messageContainerA: MutableLiveData<String> = MutableLiveData()
    private var messageContainerB: MutableLiveData<String> = MutableLiveData()

    fun sendMessageToB(msg: String) {
        messageContainerB!!.value = msg
    }

    fun sendMessageToA(msg: String) {
        messageContainerA!!.value = msg

    }

    fun getMessageContainerA(): LiveData<String>? {
        return messageContainerA
    }

    fun getMessageContainerB(): LiveData<String>? {
        return messageContainerB
    }

}
