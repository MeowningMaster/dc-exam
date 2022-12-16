import java.io.Serializable

data class Bus(
    val driverSurname: String,
    val driverInitials: String,
    val number: String,
    val routeNumber: String,
    val brand: String,
    val exploitationStartYear: Int,
    val mileage: Int
): Serializable
