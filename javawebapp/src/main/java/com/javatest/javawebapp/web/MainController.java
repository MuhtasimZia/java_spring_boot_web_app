package com.javatest.javawebapp.web;

import com.javatest.javawebapp.Service.PrescriptionService;
import com.javatest.javawebapp.model.Prescription;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Calendar;

@Controller
public class MainController {


    private final PrescriptionService prescriptionService;

    public MainController(PrescriptionService prescriptionService) {
        super();
        this.prescriptionService = prescriptionService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        model.addAttribute("listPrescriptions", prescriptionService.getAllPrescriptionsByDate(month));
        return "index";
    }

    @GetMapping("/showPrescription")
    public String showPage(Model model, @Param("months") Integer months) {
        int month;
        if(months == 0){
            month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        }
        else {
            month = months;
        }
        model.addAttribute("listPrescriptions", prescriptionService.getAllPrescriptionsByDate(month));
        return "index";
    }

    @GetMapping("/api/v1/prescription")
    public String viewPage(Model model) {
        model.addAttribute("listPrescriptions", prescriptionService.getAllPrescriptions());
        return "list";
    }

    @GetMapping("/api/v1/report")
    public String countPage(Model model) {
        model.addAttribute("list_Date_wise_Prescriptions", prescriptionService.getPrescriptionCountByDay());
        return "report";
    }

    @GetMapping("/showNewPrescriptionForm")
    public String showNewPrescriptionForm(Model model) {
        // create model attribute to bind form data
        Prescription prescription = new Prescription();
        model.addAttribute("prescription", prescription);
        return "new_prescription";
    }

    @PostMapping("/savePrescription")
    public String savePrescription(@Valid @ModelAttribute("prescription") Prescription prescription, BindingResult
            bindingResult) {

        if (bindingResult.hasErrors()) {
            return "new_prescription";
        }
        // save prescription to database
        else
            {
                prescriptionService.savePrescription(prescription);
                return "redirect:/";
            }
    }

    @PostMapping("/saveupdatedPrescription")
    public String saveUpdatedPrescription(@Valid @ModelAttribute("prescription") Prescription prescription, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "update_prescription";
        }
        // save prescription to database
        else
            {
                prescriptionService.savePrescription(prescription);
                return "redirect:/";
            }
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {

        // get prescription from the service
        Prescription prescription = prescriptionService.getPrescriptionById(id);

        // set prescription as a model attribute to pre-populate the form
        model.addAttribute("prescription", prescription);
        return "update_prescription";
    }

    @GetMapping("/deletePrescription/{id}")
    public String deletePrescription(@PathVariable (value = "id") long id) {

        // call delete prescription method
        this.prescriptionService.deletePrescriptionById(id);
        return "redirect:/";
    }
}
