package com.softpro.airlines;

import com.softpro.airlines.model.Airplain;
import com.softpro.airlines.model.AirplainRepository;
import com.softpro.airlines.model.Airport;
import com.softpro.airlines.model.AirportRepository;
import com.softpro.airlines.model.Flight;
import com.softpro.airlines.model.FlightRepository;
import com.softpro.airlines.model.Route;
import com.softpro.airlines.model.RouteRepository;
import com.softpro.airlines.model.Routes;
import com.softpro.airlines.model.exceptions.AirplainNotFoundException;
import com.softpro.airlines.model.exceptions.AirportNotFoundException;
import com.softpro.airlines.model.exceptions.FlightNotFoundException;
import com.softpro.airlines.model.exceptions.RouteNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

import static com.google.common.collect.ImmutableList.copyOf;
import static com.google.common.collect.ImmutableList.of;
import static com.softpro.airlines.model.Routes.M1;
import static com.softpro.airlines.model.Routes.M2;
import static com.softpro.airlines.model.Routes.M3;

@Component
public class AirlinesService {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private AirplainRepository airplainRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    @javax.transaction.Transactional
    public void createStoredProcedures() {
        jdbcTemplate.execute("DROP INDEX IF EXISTS route_airports_airport_id_idx");
        jdbcTemplate.execute("create index route_airports_airport_id_idx on route_airports(airport_id)");
        jdbcTemplate.execute("CREATE OR REPLACE FUNCTION count_lands(airplain_id bigint, airport_id bigint) RETURNS bigint AS $$select count(1) from flight f JOIN route_airports ra ON f.route_id=ra.route_id where f.airplain_id=$1 AND ra.airport_id=$2;$$ LANGUAGE SQL");
        jdbcTemplate.execute("CREATE OR REPLACE FUNCTION count_lands(airplain_id bigint, airport_id bigint, start date, finish date) RETURNS bigint AS $$select count(1) from flight f JOIN route_airports ra ON f.route_id=ra.route_id where f.airplain_id=$1 AND ra.airport_id=$2 AND f.date >= $3 AND f.date <=$4;$$ LANGUAGE SQL");
    }

    public Airport airport(String name) throws AirportNotFoundException {
        return airportRepository.findByName(name)
                                .stream()
                                .findFirst()
                                .orElseThrow(() -> new AirportNotFoundException(name));
    }

    public Airplain airplain(long id) throws AirplainNotFoundException {
        return airplainRepository.findById(id).orElseThrow(() -> new AirplainNotFoundException(id));
    }

    public List<Airplain> airplains() {
        return copyOf(airplainRepository.findAll());
    }

    public Airport airport(long id) throws AirportNotFoundException {
        return airportRepository.findById(id).orElseThrow(() -> new AirportNotFoundException(id));
    }

    public List<Airport> airports() {
        return copyOf(airportRepository.findAll());
    }

    public Route route(long id) throws RouteNotFoundException {
        return routeRepository.findById(id).orElseThrow(() -> new RouteNotFoundException(id));
    }

    public List<Route> routes() {
        return copyOf(routeRepository.findAll());
    }

    public Flight flight(long id) throws FlightNotFoundException {
        return flightRepository.findById(id).orElseThrow(() -> new FlightNotFoundException(id));
    }

    public List<Flight> flights() {
        return copyOf(flightRepository.findAll());
    }

    public long countLands(long airplainId, long airportId) {
        return airplainRepository.countLands(airplainId, airportId);
    }

    public long countLands(long airplainId, long airportId, LocalDate start, LocalDate finish) {
        return airplainRepository.countLands(airplainId, airportId, start, finish);
    }

    void initAirplanes() {
        log.info("Save airplanes");

        of(new Airplain(1, "Boeing 747-400 #1"),
           new Airplain(2, "Boeing 747-400 #2"),
           new Airplain(3, "Boeing 747-400 #3"),
           new Airplain(4, "Boeing 747-400 #4"),
           new Airplain(5, "Airbus A380-800 #1")).forEach(airplainRepository::save);
    }

    void initAirports() {
        log.info("Save airports");

        of(new Airport(1, "Москва (Шереметьево)"),
           new Airport(2, "Санкт Петербург (Пулково)"),
           new Airport(3, "Москва (Внуково)"),
           new Airport(4, "Симферополь"),
           new Airport(5, "Москва (Домодедово)")).forEach(airportRepository::save);
    }

    @Transactional
    public void initRoutes() throws AirportNotFoundException {
        log.info("Save routes");

        // M1 Москва (Шереметьево) - Санкт Петербург (Пулково)
        Route routeM1a = new Route(M1.a, "M1a: Москва (Шереметьево) - Санкт Петербург (Пулково)", of(airport("Санкт Петербург (Пулково)")));
        // и обратно
        Route routeM1b = new Route(M1.b, "M1b: Санкт Петербург (Пулково) - Москва (Шереметьево)", of(airport("Москва (Шереметьево)")));

        // М2 Москва (Внуково) - Симферополь
        Route routeM2a = new Route(M2.a, "M2a: Москва (Внуково) - Симферополь", of(airport("Симферополь")));
        // и обратно
        Route routeM2b = new Route(M2.b, "M2b: Симферополь - Москва (Внуково)", of(airport("Москва (Внуково)")));

        // М3 Санкт-Петербург (Пулково) - Москва (Домодедово) - Симферополь
        Route routeM3a = new Route(M3.a, "М3a: Санкт-Петербург (Пулково) - Москва (Домодедово) - Симферополь",
                                   of(airport("Москва (Домодедово)"), airport("Симферополь")));
        //и обратно
        Route routeM3b = new Route(M3.b, "М3b: Симферополь - Москва (Домодедово) - Санкт-Петербург (Пулково)",
                                   of(airport("Москва (Домодедово)"), airport("Санкт Петербург (Пулково)")));

        of(routeM1a, routeM1b, routeM2a, routeM2b, routeM3a, routeM3b).forEach(routeRepository::save);
    }

    @Transactional
    public Flight addFlight(LocalDate date, long airplainId, long routeId) throws AirplainNotFoundException, RouteNotFoundException {
        return flightRepository.save(new Flight(date, airplain(airplainId), route(routeId)));
    }

    public List<Flight> addFlight(LocalDate date, long airplainId, Routes r) throws AirplainNotFoundException, RouteNotFoundException {
        return of(addFlight(date, airplainId, r.a),
                  addFlight(date, airplainId, r.b));
    }

    @Transactional
    public void initFlights() throws AirplainNotFoundException, RouteNotFoundException {

        flightRepository.deleteAll();

        //Полеты за неделю со 2 по 8 апреля
        for (int day = 2; day <= 8; day++) {
            //М1 - два раза в день
            addFlight(LocalDate.of(2018, 4, day), 1, M1);
            addFlight(LocalDate.of(2018, 4, day), 1, M1);

            //М2 - раз в день
            addFlight(LocalDate.of(2018, 4, day), 2, M2);
        }

        //М3 - раз в неделю по субботам (в Симферополь)
        addFlight(LocalDate.of(2018, 4, 7), 3, M3.a);
        // и обратно (в Санкт-Петербург) по воскресеньям
        addFlight(LocalDate.of(2018, 4, 8), 3, M3.b);
    }
}
