package rmi

import Bus
import java.rmi.Remote
import java.rmi.RemoteException

interface RmiCommandsInterface : Remote {
    @Throws(RemoteException::class)
    fun findAllByRouteNumber(routeNumber: String): List<Bus>

    @Throws(RemoteException::class)
    fun findAllByMinExploitation(exploitation: Int): List<Bus>

    @Throws(RemoteException::class)
    fun findAllByMinMileage(mileage: Int): List<Bus>
}