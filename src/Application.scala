import com.springler.demo.{Input, RouteRepository}

object Application {

  def main(args: Array[String]): Unit = {

    if (args.length != 1) {
      throw new Exception("Invalid inputs")
    }

    val routes = RouteRepository.loadRoutes()

    val expectedInputParameter = Seq("source", "destination", "numberOfPassengers")

    val inputMap = args(0).split(",")
      .map(_.split("="))
      .map(p => (p(0), p(1)))
      .groupBy(_._1)
      .map(x => (x._1, x._2.find(_._1 == x._1).head._2))

    val inputKeys = inputMap.keySet

    expectedInputParameter.find(param => !inputKeys.contains(param)).map(x => throw new Exception(s"$x not found in input parameter"))

    val input = Input(inputMap("source"), inputMap("destination"), inputMap("numberOfPassengers").toInt)

    val distanceTravelled = routes.find(r => (r.source == input.source & r.destination == input.destination) |
      (r.source == input.destination & r.destination == input.source)).map(_.distance).headOption

    val minimumFair = 750
    val minimumDistance = 100

    if (distanceTravelled.isDefined) {
      val fairPerPerson = distanceTravelled.map {
        distance =>
          if (distance < minimumDistance) {
            minimumFair
          } else {
            minimumFair + ((distance - minimumDistance) * 5)
          }
      }.getOrElse(0)

      printTicket(input, distanceTravelled.head, fairPerPerson)

    } else {
      println("No route...")
    }
  }

  def printTicket(input: Input, distanceTravelled: Int, fairPerPerson: Int): Unit = {
    println("Taxi Ticket")
    println("----------------")
    println(s"Source: ${input.source}")
    println(s"Destination: ${input.destination}")
    println(s"Kms: $distanceTravelled")
    println(s"No. of Travellers = ${input.numberOfTravellers}")
    println(s"Total = ${fairPerPerson * input.numberOfTravellers} INR")
  }
}
