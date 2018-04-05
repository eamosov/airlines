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

    /**
     * Получить самолет по id
     * @param id
     * @return
     * @throws AirplainNotFoundException
     */
    @RequestMapping("/airplains/{id}")
    public Airplain getAirplain(@PathVariable(value = "id") String id) throws AirplainNotFoundException {
        return airlinesService.airplain(Long.parseLong(id));
    }

    /**
     * Посчитать все посадки самолета airplain_id в аэропорту airport_id
     * @param airplainId
     * @param airportId
     * @return
     * @throws AirplainNotFoundException
     */
    @RequestMapping("/lands/{airplain_id}/{airport_id}")
    public long countLands(@PathVariable(value = "airplain_id") String airplainId,
                           @PathVariable(value = "airport_id") String airportId) throws AirplainNotFoundException {
        return airlinesService.countLands(Long.parseLong(airplainId), Long.parseLong(airportId));
    }

    /**
     * Посчитать все посадки самолета airplain_id в аэропорту airport_id за дни между start и finish
     * @param airplainId
     * @param airportId
     * @param start
     * @param finish
     * @return
     * @throws AirplainNotFoundException
     */
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

    /**
     * Получить список всех самолетов
     * @return
     */
    @RequestMapping("/airplains")
    public List<Airplain> getAirplain() {
        return airlinesService.airplains();
    }

    /**
     * Получить Аэропорт по id
     * @param id
     * @return
     * @throws AirportNotFoundException
     */
    @RequestMapping("/airports/{id}")
    public Airport getAirport(@PathVariable(value = "id") String id) throws AirportNotFoundException {
        return airlinesService.airport(Long.parseLong(id));
    }

    /**
     * Получить список всех Аэропортов
     * @return
     */
    @RequestMapping("/airports")
    public List<Airport> getAirports() {
        return airlinesService.airports();
    }

    @RequestMapping("/routes/{id}")
    public Route getRoute(@PathVariable(value = "id") String id) throws RouteNotFoundException {
        return airlinesService.route(Long.parseLong(id));
    }

    /**
     * Получить список всех маршрутов
     * @return
     */
    @RequestMapping("/routes")
    public List<Route> getRoutes() {
        return airlinesService.routes();
    }

    /**
     * Получить Полет по его id
     * @param id
     * @return
     * @throws FlightNotFoundException
     */
    @RequestMapping("/flights/{id}")
    public Flight getFlight(@PathVariable(value = "id") String id) throws FlightNotFoundException {
        return airlinesService.flight(Long.parseLong(id));
    }

    /**
     * Получить список всех полетов
     * @return
     */
    @RequestMapping("/flights")
    public List<Flight> getFlights() {
        return airlinesService.flights();
    }

    /**
     * Добавить полет самолета airplain_id по маршуруту route_id в день date
     * @param date
     * @param airplainId
     * @param routeId
     * @return
     * @throws RouteNotFoundException
     * @throws AirplainNotFoundException
     */
    @RequestMapping("/flights/add/{date}/{airplain_id}/{route_id}")
    public Flight addFlight(@PathVariable("date") String date,
                            @PathVariable("airplain_id") String airplainId,
                            @PathVariable("route_id") String routeId) throws RouteNotFoundException, AirplainNotFoundException {

        return airlinesService.addFlight(LocalDate.parse(date), Long.parseLong(airplainId), Long.parseLong(routeId));
    }

}