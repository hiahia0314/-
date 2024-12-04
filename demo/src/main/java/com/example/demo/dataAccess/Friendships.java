package com.example.demo.dataAccess;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="friendships")
public class Friendships {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="receiver")
    private String receiver;

    @Column(name = "applicant")
    private String applicant;

    @Column(name = "status")
    private String status;

    @Column(name = "time")
    private LocalDate time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }
}
