package com.springler.demo

object RouteRepository {



  def loadRoutes(): Seq[Route] = {
    Seq(
      Route("PUNE", "MUMBAI", 120),
      Route("PUNE", "NASHIK", 200),
      Route("MUMBAI", "GOA", 350),
      Route("MUMBAI", "NASHIK", 180)
    )
  }


}
