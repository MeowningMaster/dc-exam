val busses = arrayListOf(
    Bus("Hicks", "J. A.", "D-544", "DD", "BMW", 2005, 105),
    Bus("Anderson", "E. H.", "E-312", "EE", "Audi", 2014, 210),
    Bus("Ramirez", "M. T.", "K-323", "KK", "Hyundai", 2020, 315)
)

const val currentYear = 2022

object QueriesBase {
    fun findAllByRouteNumber(routeNumber: String): List<Bus> {
        return busses.filter{x -> x.routeNumber == routeNumber}
    }

    fun findAllByMinExploitation(exploitation: Int): List<Bus> {
        return busses.filter{x -> (currentYear - x.exploitationStartYear) >= exploitation}
    }

    fun findAllByMinMileage(mileage: Int): List<Bus> {
        return busses.filter{x -> x.mileage >= mileage}
    }
}