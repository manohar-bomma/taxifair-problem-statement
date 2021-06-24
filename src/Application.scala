import com.springler.demo.{Input, Route, RouteRepository, RouteService, TicketService}

object Application {

  def main(args: Array[String]): Unit = {

    val input = praseCommandLine(args)

    val routeService = RouteService.apply(RouteRepository.apply())

    val route = routeService.findDistanceBetweenEndpoints(input.source, input.destination)

    val fairPerPerson = TicketService.calculateFair(route.distance)

    TicketService.printTicket(route, input.numberOfTravellers, fairPerPerson)

    println()

    TicketService.printTicket(Seq(
      "Taxi Ticket",
      "----------------",
      s"Source: ${route.source} To ${route.destination}",
      s"Kms: ${route.distance}",
      s"No. of Travellers = ${input.numberOfTravellers}",
      s"Total = ${fairPerPerson * input.numberOfTravellers} INR"
    ))

  }

  def praseCommandLine(args: Array[String]) = {
    if (args.length != 1) {
      throw new Exception("Invalid inputs")
    }

    val expectedInputParameter = Seq("source", "destination", "numberOfPassengers")

    val inputMap = args(0).split(",")
      .map(_.split("="))
      .map(p => (p(0), p(1)))
      .groupBy(_._1)
      .map(x => (x._1, x._2.find(_._1 == x._1).head._2))

    val inputKeys = inputMap.keySet

    expectedInputParameter.find(param => !inputKeys.contains(param)).map(x => throw new Exception(s"$x not found in input parameter"))

    val input = Input(inputMap("source"), inputMap("destination"), inputMap("numberOfPassengers").toInt)

    if (input.numberOfTravellers <= 0) {
      throw new Exception("atleast one traveller/s required")
    }
    input
  }

}
