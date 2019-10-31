package com.kapplication.kmessagecenter

import java.util.concurrent.atomic.AtomicInteger

class KMessage {
    companion object {
        private var messageId: AtomicInteger = AtomicInteger(0)

        fun obtainMessageId() : Int {
            return messageId.getAndIncrement()
        }

        fun obtainMessage() : KMessage {
            return KMessage()
        }
    }


    var id: Int = 0  // message id
    var tag: Int = -1  // message tag, same as the action of broadcast
    var data: Any = Any()  // message data
        set(data) {
            this.dataClass = data.javaClass
            field = data
        }
    var dataClass: Class<*>? = data.javaClass // message data type

    var isSyncMessage: Boolean = false // default is Asynchronous message
    var delay: Long = 0 // delay time in ms
    var repeat: Int = 1 // repeat time
    var interval: Long = 0 // interval of message in ms

    private constructor() {
        this.id = obtainMessageId()
    }

    constructor(tag: Int, clazz: Class<*>) {
        this.id = obtainMessageId()
        this.dataClass = clazz
        this.tag = tag
    }

    constructor(tag: Int, data: Any, delay: Long, repeat: Int, interval: Long) {
        this.id = obtainMessageId()
        this.data = data
        this.tag = tag
        this.delay = delay
        this.repeat = repeat
        this.interval = interval
    }

    constructor(message: KMessage) {
        this.tag = message.tag
        dataClass = message.dataClass
        copyData(message)
    }

    fun copyData(message: KMessage) {
        this.isSyncMessage = message.isSyncMessage
        this.data = message.data
        this.delay = message.delay
        this.repeat = message.repeat
        this.interval = message.interval
    }

    override fun toString(): String {
        return "KMessage [tag=$tag, dataClass=${dataClass?.name}]"
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + (if(dataClass == null) 0 else dataClass.hashCode())
        result = prime * result + tag
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (other == null) {
            return false
        }

        if (this.javaClass != other.javaClass) {
            return false
        }

        if (this.tag != (other as KMessage).tag) {
            return false
        }

        if (this.dataClass == null) {
            if (other.dataClass != null) {
                return false
            }
        } else if (this.dataClass!! != other.dataClass) {
            return false
        }

        return true
    }
}