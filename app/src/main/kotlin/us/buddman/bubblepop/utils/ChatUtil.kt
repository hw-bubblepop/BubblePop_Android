package us.buddman.bubblepop.utils

import android.os.AsyncTask
import android.os.Handler
import android.util.Log
import java.io.*
import java.net.Socket

/**
 * Created by Junseok on 2017-09-29.
 */
class ChatUtil {
    companion object {
        var socket: Socket? = null
        private var manager: ChatUtil? = null
        val instance: ChatUtil
            get(){
            if (manager == null) {
                manager = ChatUtil()
                (manager as ChatUtil).connectSocket(CredentialsManager.instance.activeUser.second.nickname)
                (manager as ChatUtil).setOnReceiveListener(object : ChatUtil.OnReceiveListener {
                    override fun onReceive(msg: String) {
                        Log.d("dudco-chat", msg)
                    }
                })
            }
            return manager as ChatUtil
        }
    }

    //    private var socket: Socket? = null
    private var mHandler: Handler? = null
    private var networkReader: BufferedReader? = null
    private var networkWriter: BufferedWriter? = null
    private var checkUpate: Thread? = null
    private var showUpdate: Runnable? = null
    private var msg: String? = null
    private var onReceiveListener: OnReceiveListener? = null

    fun connectSocket(name: String) {
        SocketConnTask("www.soylatte.kr", 3115).execute()
        var initMsg = "#name " + name
        SocketSendTask().execute(initMsg)
    }

    fun send(msg: String) {
        SocketSendTask().execute(msg)
    }

    fun disconnection() {
        send("#quit")
        socket?.close()
    }

    fun setOnReceiveListener(onReceiveListener: OnReceiveListener) {
        this.onReceiveListener = onReceiveListener
    }

    inner class SocketSendTask : AsyncTask<String, Void, Void>() {
        override fun doInBackground(vararg p0: String?): Void? {
            val out = PrintWriter(networkWriter, true)
            Log.d("dudco", p0[0])
            out.println(p0[0])
            return null
        }
    }

    inner class SocketConnTask(private val ip: String, private val port: Int) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg p0: Void?): Void? {
            try {
                Log.d("dudco", "async")
                socket = Socket(ip, port)
                networkWriter = BufferedWriter(OutputStreamWriter(socket?.getOutputStream()))
                networkReader = BufferedReader(InputStreamReader(socket?.getInputStream()))
            } catch (e: IOException) {
                Log.e("dudco", "exception")
                e.printStackTrace()
            }
            return null
        }

        override fun onPostExecute(result: Void?) {
            Log.d("dudco", "excute")
            mHandler = Handler()
            showUpdate = Runnable {
                msg?.let {
                    onReceiveListener?.onReceive(it)
                }
            }

            checkUpate = object : Thread() {
                override fun run() {
                    try {
                        var line: String? = null
                        Log.w("ChattingStart", "Start Thread")
                        while (true) {
                            //                        Log.w("Chatting is running", "chatting is running")
                            line = networkReader?.readLine()
                            msg = line
                            mHandler?.post(showUpdate)
                        }
                    } catch (e: Exception) {

                    }
                }
            }
            checkUpate?.start()
        }
    }

    interface OnReceiveListener {
        fun onReceive(msg: String)
    }
}