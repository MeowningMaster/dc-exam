package rmi
import java.rmi.registry.LocateRegistry

fun main() {
    val commands = RmiCommands()
    val registry = LocateRegistry.createRegistry(port)
    registry.bind(registryName, commands)
    println("Server started on $port ($registryName)")
}