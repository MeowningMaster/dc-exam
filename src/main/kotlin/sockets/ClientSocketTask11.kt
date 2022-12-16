package sockets

import Bus
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket

private lateinit var socketOut: ObjectOutputStream
private lateinit var socketIn: ObjectInputStream

fun main() {
    val socket = Socket(host, port)
    socketOut = ObjectOutputStream(socket.getOutputStream())
    socketIn = ObjectInputStream(socket.getInputStream())
    println("Connected to $host:$port")

    findAllByRouteNumber("EE")
    findAllByMinExploitation(3)
    findAllByMinMileage(200)
}

private fun socketWrite(obj: Any) {
    socketOut.writeObject(obj)
    socketOut.flush()
}

private inline fun <reified T> socketRead(): T {
    val obj = socketIn.readObject()
    return if (obj is T) {
        obj
    } else {
        throw RuntimeException()
    }
}

private fun findAllByRouteNumber(routeNumber: String) {
    socketWrite(findAllByRouteNumber)
    socketWrite(routeNumber)
    val result = socketRead<List<Bus>>()
    println(result)
}

private fun findAllByMinExploitation(exploitation: Int) {
    socketWrite(findAllByMinExploitation)
    socketWrite(exploitation)
    val result = socketRead<List<Bus>>()
    println(result)
}

private fun findAllByMinMileage(mileage: Int) {
    socketWrite(findAllByMinMileage)
    socketWrite(mileage)
    val result = socketRead<List<Bus>>()
    println(result)
}
