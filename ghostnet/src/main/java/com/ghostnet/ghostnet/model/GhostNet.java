package com.ghostnet.ghostnet.model;

import jakarta.persistence.*;

@Entity
public class GhostNet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;
    private String size;
    private boolean reportedAnonymously;
    private String reporterName;
    private String reporterPhone;
    private String missingReporterName;
    private String missingReporterPhone;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person assignedPerson;

    public enum Status {
        GEMELDET,
        BERGUNG_BEVORSTEHEND,
        GEBORGEN,
        VERSCHOLLEN
    }

    // Getters und Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public boolean isReportedAnonymously() { return reportedAnonymously; }
    public void setReportedAnonymously(boolean reportedAnonymously) { this.reportedAnonymously = reportedAnonymously; }

    public String getReporterName() { return reporterName; }
    public void setReporterName(String reporterName) { this.reporterName = reporterName; }

    public String getReporterPhone() { return reporterPhone; }
    public void setReporterPhone(String reporterPhone) { this.reporterPhone = reporterPhone; }

    public String getMissingReporterName() { return missingReporterName; }
    public void setMissingReporterName(String missingReporterName) { this.missingReporterName = missingReporterName; }

    public String getMissingReporterPhone() { return missingReporterPhone; }
    public void setMissingReporterPhone(String missingReporterPhone) { this.missingReporterPhone = missingReporterPhone; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Person getAssignedPerson() { return assignedPerson; }
    public void setAssignedPerson(Person assignedPerson) { this.assignedPerson = assignedPerson; }
}