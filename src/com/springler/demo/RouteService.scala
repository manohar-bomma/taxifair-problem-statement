package com.springler.demo

import scala.collection.mutable

object RouteService {

  def apply(routeRepository: RouteRepository): RouteService = {
    routeRepository.loadRoutes()
    new RouteService(routeRepository)
  }
}

case class RouteService(routeRepository: RouteRepository) {

  def findAllRoutes(): mutable.Seq[Route] = routeRepository.loadRoutes()

  def addRoute(route: Route): Route = routeRepository.addRoute(route)

  def findDistanceBetweenEndpoints(endpoint1: String, endpoint2: String): Route = {
    val distance = routeRepository.findDistanceBetweenEndpoints(endpoint1, endpoint2)
    if (distance.isEmpty) {
      throw new Exception(s"No Route Found between $endpoint1 and $endpoint2")
    }
    Route(endpoint1, endpoint2, distance.head)
  }
}
