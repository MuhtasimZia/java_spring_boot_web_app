package com.javatest.javawebapp.Service;

import com.javatest.javawebapp.model.Prescription;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface PrescriptionService {

    List<Prescription> getAllPrescriptions();

    List<Prescription> getAllPrescriptionsByDate(Integer month);

    List<Map<LocalDate, Integer>> getPrescriptionCountByDay();

    void savePrescription(Prescription prescription);
    Prescription getPrescriptionById(long id);
    void deletePrescriptionById(long id);


}