package com.springler.demo

object TicketService {

  def calculateFair(distance: Int): Int = {
    val minimumFair = 750
    val minimumDistance = 100

    if (distance < minimumDistance) {
      minimumFair
    } else {
      minimumFair + ((distance - minimumDistance) * 5)
    }
  }

  def printTicket(route: Route, numberOfTravellers: Int, fairPerPerson: Int): Unit = {
    println("Taxi Ticket")
    println("----------------")
    println(s"Source: ${route.source}")
    println(s"Destination: ${route.destination}")
    println(s"Kms: ${route.distance}")
    println(s"No. of Travellers = $numberOfTravellers")
    println(s"Total = ${fairPerPerson * numberOfTravellers} INR")
  }

  def printTicket(lines: Seq[String]): Unit = {
    lines.foreach(println)
  }
}
