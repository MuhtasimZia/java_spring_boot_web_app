package com.javatest.javawebapp.repository;

import com.javatest.javawebapp.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    @Query("SELECT p FROM Prescription p WHERE MONTH(p.prescription_date) = ?1")
    List<Prescription> getAllPrescriptionsByDate(Integer month);

    @Query("SELECT p.prescription_date as prescription_date,COUNT(distinct p.id) as prescription_count from Prescription p group by p.prescription_date")
    List<Map<LocalDate, Integer>> getPrescriptionCountByDay();

}
