package com.ty.endlessevent.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.endlessevent.dto.Event;
import com.ty.endlessevent.dto.Venue;
import com.ty.endlessevent.respositary.VenueRepositary;

@Repository
public class VenueDao {

	@Autowired
	private VenueRepositary repositary;

	@Autowired
	private EventDao dao;

	public Venue saveVenue(int e_id, Venue venue) {
		Event event = dao.getEvent(e_id);
		if (event != null) {
			Boolean b = checkDateAndTime(venue.getDate(), venue.getTime());
			if (b == false) {
				venue.setEvent(event);
				return repositary.save(venue);
			}
		}
		return null;
	}

	public Boolean checkDateAndTime(LocalDate date, LocalTime time) {
		Venue venue = repositary.checkDateAndTime(date, time);
		if (venue != null) {
			return true;
		}
		return false;
	}

	public Venue getVenueById(int v_id) {
		Optional<Venue> optional = repositary.findById(v_id);
		if (optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}

	public Venue updateVenueById(int v_id, Venue venue) {
		Venue v = getVenueById(v_id);
		if (v != null) {
			return repositary.save(venue);
		}
		return null;
	}

	public Boolean deleteVenue(int id) {
		Venue v = getVenueById(id);
		if (v != null) {
			repositary.delete(v);
			return true;
		}
		return false;
	}

}
