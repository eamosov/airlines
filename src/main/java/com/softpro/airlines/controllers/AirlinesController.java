package com.softpro.airlines.controllers;

import com.softpro.airlines.AirlinesService;
import com.softpro.airlines.model.Airplain;
import com.softpro.airlines.model.Airport;
import com.softpro.airlines.model.Flight;
import com.softpro.airlines.model.Route;
import com.softpro.airlines.model.exceptions.AirplainNotFoundException;
import com.softpro.airlines.model.exceptions.AirportNotFoundException;
import com.softpro.airlines.model.exceptions.FlightNotFoundException;
import com.softpro.airlines.model.exceptions.RouteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class AirlinesController {

    @Autowired
    private AirlinesService airlinesService;

    @RequestMapping("/airplains/{id}")
    public Airplain getAirplain(@PathVariable(value = "id") String id) throws AirplainNotFoundException {
        return airlinesService.airplain(Long.parseLong(id));
    }

    @RequestMapping("/lands/{airplain_id}/{airport_id}")
    public long countLands(@PathVariable(value = "airplain_id") String airplainId,
                           @PathVariable(value = "airport_id") String airportId) throws AirplainNotFoundException {
        return airlinesService.countLands(Long.parseLong(airplainId), Long.parseLong(airportId));
    }

    @RequestMapping("/lands/{airplain_id}/{airport_id}/{start}/{finish}")
    public long countLands(@PathVariable(value = "airplain_id") String airplainId,
                           @PathVariable(value = "airport_id") String airportId,
                           @PathVariable(value = "start") String start,
                           @PathVariable(value = "finish") String finish) throws AirplainNotFoundException {

        return airlinesService.countLands(Long.parseLong(airplainId),
                                          Long.parseLong(airportId),
                                          LocalDate.parse(start),
                                          LocalDate.parse(finish));
    }

    @RequestMapping("/airplains")
    public List<Airplain> getAirplain() {
        return airlinesService.airplains();
    }

    @RequestMapping("/airports/{id}")
    public Airport getAirport(@PathVariable(value = "id") String id) throws AirportNotFoundException {
        return airlinesService.airport(Long.parseLong(id));
    }

    @RequestMapping("/airports")
    public List<Airport> getAirports() {
        return airlinesService.airports();
    }

    @RequestMapping("/routes/{id}")
    public Route getRoute(@PathVariable(value = "id") String id) throws RouteNotFoundException {
        return airlinesService.route(Long.parseLong(id));
    }

    @RequestMapping("/routes")
    public List<Route> getRoutes() {
        return airlinesService.routes();
    }

    @RequestMapping("/flights/{id}")
    public Flight getFlight(@PathVariable(value = "id") String id) throws FlightNotFoundException {
        return airlinesService.flight(Long.parseLong(id));
    }

    @RequestMapping("/flights")
    public List<Flight> getFlights() {
        return airlinesService.flights();
    }

    @RequestMapping("/flights/add/{date}/{airplain_id}/{route_id}")
    public Flight addFlight(@PathVariable("date") String date,
                            @PathVariable("airplain_id") String airplainId,
                            @PathVariable("route_id") String routeId) throws RouteNotFoundException, AirplainNotFoundException {

        return airlinesService.addFlight(LocalDate.parse(date), Long.parseLong(airplainId), Long.parseLong(routeId));
    }

}