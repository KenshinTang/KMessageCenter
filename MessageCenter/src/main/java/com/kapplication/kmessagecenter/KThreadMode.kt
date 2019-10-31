package com.kapplication.kmessagecenter

enum class KThreadMode {
    MAIN,  // message execution on main thread, default
    POST,  // message execution on post thread.
    ASYNC  // message execution on a new thread.
}