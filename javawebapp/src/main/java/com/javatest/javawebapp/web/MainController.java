package com.javatest.javawebapp.web;

import com.javatest.javawebapp.Service.ParsingService;
import com.javatest.javawebapp.Service.PrescriptionService;
import com.javatest.javawebapp.model.Prescription;
import com.javatest.javawebapp.model.api;
import org.assertj.core.util.Arrays;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;

@Controller
public class MainController {


    private final PrescriptionService prescriptionService;

    private final ParsingService parsingService;

    private static final String url = "https://rxnav.nlm.nih.gov/REST/interaction/interaction.json?rxcui=341248";

    public MainController(PrescriptionService prescriptionService, ParsingService parsingService) {
        super();
        this.prescriptionService = prescriptionService;
        this.parsingService = parsingService;
    }

    @GetMapping("/api/v1/consume_api")
    public String getApi(Model model) {
        api consume_api = (api) parsingService.parse(url);
        model.addAttribute("api_list", consume_api);
        return "api";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        model.addAttribute("listPrescriptions", prescriptionService.getAllPrescriptionsByDate(year, month));
        return "index";
    }

    @GetMapping("/showPrescription")
    public String showPage(Model model, @RequestParam Integer years, @RequestParam Integer months) {
        model.addAttribute("listPrescriptions", prescriptionService.getAllPrescriptionsByDate(years, months));
        return "index";
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
