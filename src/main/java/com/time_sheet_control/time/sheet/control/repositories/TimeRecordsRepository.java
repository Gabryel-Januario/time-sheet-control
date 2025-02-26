package com.time_sheet_control.time.sheet.control.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.time_sheet_control.time.sheet.control.models.timerecords.TimeRecords;

public interface TimeRecordsRepository extends JpaRepository<TimeRecords, String>{
    
}
