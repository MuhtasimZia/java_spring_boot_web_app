package com.javatest.javawebapp.Service;

import com.javatest.javawebapp.model.Prescription;
import com.javatest.javawebapp.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository) {
        super();
        this.prescriptionRepository = prescriptionRepository;
    }

    @Override
    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    @Override
    public List<Prescription> getAllPrescriptionsByDate(Integer month) {
        var prescription = (List<Prescription>) prescriptionRepository.getAllPrescriptionsByDate(month);
        return prescription;
    }

    @Override
    public List<Map<LocalDate, Integer>> getPrescriptionCountByDay() {
        return prescriptionRepository.getPrescriptionCountByDay();
    }


    @Override
    public void savePrescription(Prescription prescription) {
        this.prescriptionRepository.save(prescription);
    }

    @Override
    public Prescription getPrescriptionById(long id) {
        Optional<Prescription> optional = prescriptionRepository.findById(id);
        Prescription prescription = null;
        if (optional.isPresent()) {
            prescription = optional.get();
        } else {
            throw new RuntimeException(" Prescription not found for id :: " + id);
        }
        return prescription;
    }

    @Override
    public void deletePrescriptionById(long id) {
        this.prescriptionRepository.deleteById(id);
    }


}