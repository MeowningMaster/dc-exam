package sockets

import QueriesBase
import java.io.EOFException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.ServerSocket
import java.net.Socket

fun main() {
    val serverSocket = ServerSocket(port)
    serverSocket.reuseAddress = true
    println("Server started on $port")

    while (true) {
        val socket = serverSocket.accept()
        println("Client connected")
        val handler = Handler(socket)
        Thread(handler).start()
    }
}

private class Handler(socket: Socket): Runnable {
    val socketIn = ObjectInputStream(socket.getInputStream())
    val socketOut = ObjectOutputStream(socket.getOutputStream())

    override fun run() {
        try {
            while(true) {
                executeCommand(socketRead())
            }
        } catch (e: EOFException) {
            println("Connection closed")
        }
    }

    fun executeCommand(command: String) {
        when (command) {
            findAllByRouteNumber -> findAllByRouteNumber()
            findAllByMinExploitation -> findAllByMinExploitation()
            findAllByMinMileage -> findAllByMinMileage()
        }
    }

    fun socketWrite(obj: Any) {
        socketOut.writeObject(obj)
        socketOut.flush()
        println("$obj ->")
    }

    inline fun <reified T> socketRead(): T {
        val obj = socketIn.readObject()
        return if (obj is T) {
            println("<- $obj")
            obj
        } else {
            throw RuntimeException()
        }
    }

    fun findAllByRouteNumber() {
        val routeNumber = socketRead<String>()
        socketWrite(QueriesBase.findAllByRouteNumber(routeNumber))
    }

    fun findAllByMinExploitation() {
        val exploitation = socketRead<Int>()
        socketWrite(QueriesBase.findAllByMinExploitation(exploitation))
    }

    fun findAllByMinMileage() {
        val mileage = socketRead<Int>()
        socketWrite(QueriesBase.findAllByMinMileage(mileage))
    }
}