package rmi

import Bus
import QueriesBase
import java.rmi.server.UnicastRemoteObject


class RmiCommands: UnicastRemoteObject(), RmiCommandsInterface {
    override fun findAllByRouteNumber(routeNumber: String): List<Bus> {
        return QueriesBase.findAllByRouteNumber(routeNumber)
    }

    override fun findAllByMinExploitation(exploitation: Int): List<Bus> {
        return QueriesBase.findAllByMinExploitation(exploitation)
    }

    override fun findAllByMinMileage(mileage: Int): List<Bus> {
        return QueriesBase.findAllByMinMileage(mileage)
    }
}