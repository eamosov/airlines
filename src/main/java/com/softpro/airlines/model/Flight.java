package com.softpro.airlines.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * Полет
 */
@Entity
@Table(indexes = {
    @Index(name = "flight_airplain_id_idx", columnList = "airplain_id,date")
})
public class Flight {

    /**
     * id полета
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Дата совершения рейса
     */
    private LocalDate date;

    /**
     * Самолет
     */
    @ManyToOne
    @JoinColumn(name = "airplain_id")
    private Airplain airplain;

    /**
     * Маршрут
     */
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    public Flight() {

    }

    public Flight(LocalDate date, Airplain airplain, Route route) {
        this.date = date;
        this.airplain = airplain;
        this.route = route;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Airplain getAirplain() {
        return airplain;
    }

    public void setAirplain(Airplain airplain) {
        this.airplain = airplain;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return "Flight{" +
            "id=" + id +
            ", time=" + date +
            ", airplain=" + airplain +
            ", route=" + route +
            '}';
    }
}
