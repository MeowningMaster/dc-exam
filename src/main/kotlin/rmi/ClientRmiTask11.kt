package rmi

import java.rmi.registry.LocateRegistry

private lateinit var rmi: RmiCommandsInterface

fun main() {
    val registry = LocateRegistry.getRegistry(port)
    rmi = registry.lookup(registryName) as RmiCommandsInterface

    findAllByRouteNumber("EE")
    findAllByMinExploitation(3)
    findAllByMinMileage(200)
}

private fun findAllByRouteNumber(routeNumber: String) {
    println(rmi.findAllByRouteNumber(routeNumber))
}

private fun findAllByMinExploitation(exploitation: Int) {
    println(rmi.findAllByMinExploitation(exploitation))
}

private fun findAllByMinMileage(mileage: Int) {
    println(rmi.findAllByMinMileage(mileage))
}