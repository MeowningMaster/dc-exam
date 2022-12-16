package sockets

import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.ServerSocket
import java.io.EOFException

private lateinit var socketOut: ObjectOutputStream
private lateinit var socketIn: ObjectInputStream

fun main() {
    val serverSocket = ServerSocket(port)
    println("Server started on $port")

    val socket = serverSocket.accept()
    socketIn = ObjectInputStream(socket.getInputStream())
    socketOut = ObjectOutputStream(socket.getOutputStream())
    println("End of queries")

    try {
        while(true) {
            executeCommand(socketRead())
        }
    } catch (e: EOFException) {
        println("Server shutting down")
    }

}

private fun executeCommand(command: String) {
    when (command) {
        findAllByRouteNumber -> findAllByRouteNumber()
        findAllByMinExploitation -> findAllByMinExploitation()
        findAllByMinMileage -> findAllByMinMileage()
    }
}

private fun socketWrite(obj: Any) {
    socketOut.writeObject(obj)
    socketOut.flush()
    println("$obj ->")
}

private inline fun <reified T> socketRead(): T {
    val obj = socketIn.readObject()
    return if (obj is T) {
        println("<- $obj")
        obj
    } else {
        throw RuntimeException()
    }
}

private fun findAllByRouteNumber() {
    val routeNumber = socketRead<String>()
    socketWrite(QueriesBase.findAllByRouteNumber(routeNumber))
}

private fun findAllByMinExploitation() {
    val exploitation = socketRead<Int>()
    socketWrite(QueriesBase.findAllByMinExploitation(exploitation))
}

private fun findAllByMinMileage() {
    val mileage = socketRead<Int>()
    socketWrite(QueriesBase.findAllByMinMileage(mileage))
}