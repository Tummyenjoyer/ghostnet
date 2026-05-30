package com.ghostnet.ghostnet.controller;

import com.ghostnet.ghostnet.model.GhostNet;
import com.ghostnet.ghostnet.model.Person;
import com.ghostnet.ghostnet.service.GhostNetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
public class GhostNetController {

    @Autowired
    private GhostNetService ghostNetService;

    // Login
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam String name,
                        @RequestParam String role,
                        HttpSession session) {
        session.setAttribute("userName", name);
        session.setAttribute("userRole", role);

        if (role.equals("MELDEND")) {
            return "redirect:/melden";
        } else {
            return "redirect:/bergung";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    // Meldende Person
    @GetMapping("/melden")
    public String melden(HttpSession session, Model model) {
        if (session.getAttribute("userName") == null) return "redirect:/";
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("userRole", session.getAttribute("userRole"));
        return "melden";
    }

    @PostMapping("/melden/submit")
    public String meldenSubmit(@RequestParam String location,
                               @RequestParam String size,
                               @RequestParam(required = false) String anonymous,
                               @RequestParam(required = false) String reporterPhone,
                               HttpSession session) {
        GhostNet net = new GhostNet();
        net.setLocation(location);
        net.setSize(size);
        net.setReportedAnonymously(anonymous != null);

        String name = (String) session.getAttribute("userName");
        net.setReporterName(name);

        if (anonymous != null) {
            net.setReporterPhone("Anonym");
        } else {
            net.setReporterPhone(reporterPhone);
            Person person = new Person();
            person.setName(name);
            person.setPhone(reporterPhone);
            person.setPersonType(Person.PersonType.MELDEND);
            ghostNetService.savePerson(person);
        }

        ghostNetService.reportNet(net);
        return "redirect:/melden";
    }

    // Bergende Person
    @GetMapping("/bergung")
    public String bergung(HttpSession session, Model model) {
        if (session.getAttribute("userName") == null) return "redirect:/";
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("userRole", session.getAttribute("userRole"));
        model.addAttribute("nets", ghostNetService.getNetsToRecover());
        model.addAttribute("bergendePersons", ghostNetService.getBergendePersons());
        return "bergung";
    }

    @PostMapping("/bergung/person/add")
    public String addBergendePerson(@RequestParam(required = false) String phone,
                                    HttpSession session) {
        String name = (String) session.getAttribute("userName");
        Person person = new Person();
        person.setName(name);
        person.setPhone(phone != null && !phone.isEmpty() ? phone : "Anonym");
        person.setPersonType(Person.PersonType.BERGEND);
        ghostNetService.savePerson(person);
        return "redirect:/bergung";
    }

    @PostMapping("/bergung/assign")
    public String assignPerson(@RequestParam Long netId,
                               @RequestParam Long personId) {
        ghostNetService.assignPerson(netId, personId);
        return "redirect:/bergung";
    }

    @PostMapping("/bergung/recover")
    public String markAsRecovered(@RequestParam Long netId) {
        ghostNetService.markAsRecovered(netId);
        return "redirect:/bergung";
    }

    // Verschollen melden - kein Login nötig
    @GetMapping("/verschollen")
    public String verschollen(HttpSession session, Model model) {
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
        model.addAttribute("userRole", session.getAttribute("userRole"));
        model.addAttribute("nets", ghostNetService.getAllNets());
        return "verschollen";
    }

    @PostMapping("/verschollen/submit")
    public String verschollenSubmit(@RequestParam Long netId,
                                    @RequestParam String reporterName,
                                    @RequestParam String reporterPhone) {
        ghostNetService.markAsMissing(netId, reporterName, reporterPhone);
        return "redirect:/";
    }

    // Übersicht - kein Login nötig
    @GetMapping("/uebersicht")
    public String uebersicht(HttpSession session, Model model) {
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("userName", userName);
        model.addAttribute("userRole", session.getAttribute("userRole"));
        model.addAttribute("nets", ghostNetService.getAllNets());
        return "uebersicht";
    }
}