package com.springler.demo

import com.springler.demo.RouteRepository.routes

import scala.collection.mutable

protected class RouteRepository {

  def loadRoutes(): mutable.Seq[Route] = {
    routes = mutable.Seq(
      Route("PUNE", "MUMBAI", 120),
      Route("PUNE", "NASHIK", 200),
      Route("MUMBAI", "GOA", 350),
      Route("MUMBAI", "NASHIK", 180)
    )
    routes
  }

  def addRoute(route: Route): Route = {
    routes = routes :+ route
    route
  }

  def findDistanceBetweenEndpoints(endpoint1: String, endpoint2: String): Option[Int] = {
    routes.find(r => (r.source == endpoint1 & r.destination == endpoint2) |
      (r.source == endpoint2 & r.destination == endpoint1)).map(_.distance).headOption
  }
}

object RouteRepository {

  private var routes: mutable.Seq[Route] = mutable.Seq.empty

  def apply(): RouteRepository = {
    new RouteRepository()
  }
}
