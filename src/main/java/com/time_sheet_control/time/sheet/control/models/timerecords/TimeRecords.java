package com.time_sheet_control.time.sheet.control.models.timerecords;


import java.math.BigDecimal;
import java.sql.Timestamp;

import com.time_sheet_control.time.sheet.control.models.users.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity(name = "timerecords")
@Table(name = "timerecords")
public class TimeRecords {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "check_in")
    private Timestamp checkIn;

    @Column(name = "check_out")
    private Timestamp checkOut;

    @Column(name = "hours_worked")
    private BigDecimal hours_worked;

}
