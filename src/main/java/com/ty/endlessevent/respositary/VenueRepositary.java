package com.ty.endlessevent.respositary;

import java.time.LocalDate;
import java.time.LocalTime;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.endlessevent.dto.Venue;

public interface VenueRepositary extends JpaRepository<Venue, Integer> {
	@Query("select v From Venue v where v.date=?1 AND v.time=?2")
	public Venue checkDateAndTime(LocalDate date,LocalTime time);
}
