package com.ghostnet.ghostnet.service;

import com.ghostnet.ghostnet.model.GhostNet;
import com.ghostnet.ghostnet.model.Person;
import com.ghostnet.ghostnet.repository.GhostNetRepository;
import com.ghostnet.ghostnet.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GhostNetService {

    @Autowired
    private GhostNetRepository ghostNetRepository;

    @Autowired
    private PersonRepository personRepository;

    public List<GhostNet> getAllNets() {
        return ghostNetRepository.findAll();
    }

    public List<GhostNet> getNetsToRecover() {
        return ghostNetRepository.findAll().stream()
                .filter(net -> net.getStatus() == GhostNet.Status.GEMELDET
                        || net.getStatus() == GhostNet.Status.BERGUNG_BEVORSTEHEND)
                .collect(Collectors.toList());
    }

    public List<Person> getBergendePersons() {
        return personRepository.findAll().stream()
                .filter(person -> person.getPersonType() == Person.PersonType.BERGEND)
                .collect(Collectors.toList());
    }

    public void reportNet(GhostNet ghostNet) {
        ghostNet.setStatus(GhostNet.Status.GEMELDET);
        ghostNetRepository.save(ghostNet);
    }

    public void assignPerson(Long netId, Long personId) {
        GhostNet net = ghostNetRepository.findById(netId).orElseThrow();
        Person person = personRepository.findById(personId).orElseThrow();
        net.setAssignedPerson(person);
        net.setStatus(GhostNet.Status.BERGUNG_BEVORSTEHEND);
        ghostNetRepository.save(net);
    }

    public void markAsRecovered(Long netId) {
        GhostNet net = ghostNetRepository.findById(netId).orElseThrow();
        net.setStatus(GhostNet.Status.GEBORGEN);
        ghostNetRepository.save(net);
    }

    public void markAsMissing(Long netId, String reporterName, String reporterPhone) {
        GhostNet net = ghostNetRepository.findById(netId).orElseThrow();
        net.setStatus(GhostNet.Status.VERSCHOLLEN);
        net.setMissingReporterName(reporterName);
        net.setMissingReporterPhone(reporterPhone);
        ghostNetRepository.save(net);
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public void savePerson(Person person) {
        personRepository.save(person);
    }
}