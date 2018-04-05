package com.softpro.airlines.model;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface AirplainRepository extends CrudRepository<Airplain, Long> {

    @Procedure(procedureName = "count_lands")
    long countLands(@Param("airplain_id") long airplainId, @Param("airport_id") long airportId);

    @Procedure(procedureName = "count_lands")
    long countLands(@Param("airplain_id") long airplainId,
                    @Param("airport_id") long airportId,
                    @Param("start") LocalDate start,
                    @Param("finish") LocalDate finish);

}