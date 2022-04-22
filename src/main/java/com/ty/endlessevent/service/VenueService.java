package com.ty.endlessevent.service;

import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ty.endlessevent.dao.ResponseStructure;
import com.ty.endlessevent.dao.VenueDao;
import com.ty.endlessevent.dto.User;
import com.ty.endlessevent.dto.Venue;
import com.ty.endlessevent.exception.NoIdFoundException;

@Service
public class VenueService {

	@Autowired
	private VenueDao venueDao;

	public ResponseEntity<ResponseStructure<Venue>> saveVenue(int id, Venue venue) {
		Venue venue1 = venueDao.saveVenue(id, venue);
		ResponseStructure<Venue> responseStructure = new ResponseStructure<Venue>();
		if (venue1 != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(venue);
			ResponseEntity<ResponseStructure<Venue>> entity = new ResponseEntity<ResponseStructure<Venue>>(
					responseStructure, HttpStatus.OK);
			return entity;
		} else {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("not found");
			responseStructure.setData(venue1);
			ResponseEntity<ResponseStructure<Venue>> entity = new ResponseEntity<ResponseStructure<Venue>>(
					responseStructure, HttpStatus.NOT_FOUND);
			return entity;
		}
	}

	public ResponseEntity<ResponseStructure<Boolean>> checkDateAndTime1(LocalDate date, LocalTime time) {
		ResponseStructure<Boolean> responseStructure = new ResponseStructure<Boolean>();
		boolean status = venueDao.checkDateAndTime(date, time);
		if (status) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(status);
			ResponseEntity<ResponseStructure<Boolean>> entity = new ResponseEntity<ResponseStructure<Boolean>>(
					responseStructure, HttpStatus.NOT_FOUND);
			return entity;
		}
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("cannot find ");
		responseStructure.setData(status);
		ResponseEntity<ResponseStructure<Boolean>> entity = new ResponseEntity<ResponseStructure<Boolean>>(
				responseStructure, HttpStatus.NOT_FOUND);
		return entity;
	}

	public ResponseEntity<ResponseStructure<Venue>> getVenueById(int id) {
		Venue venue = venueDao.getVenueById(id);
		ResponseStructure<Venue> responseStructure = new ResponseStructure<Venue>();
		if (venue == null) {
			throw new NoIdFoundException("Given id " + id + " not Exist");
		} else {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(venue);
			ResponseEntity<ResponseStructure<Venue>> entity = new ResponseEntity<ResponseStructure<Venue>>(
					responseStructure, HttpStatus.NOT_FOUND);
			return entity;
		}
	}

	public ResponseEntity<ResponseStructure<Venue>> updateVenueById1(int id, Venue venue) {
		ResponseStructure<Venue> responseStructure = new ResponseStructure<Venue>();
		Venue venue1 = venueDao.updateVenueById(id, venue);
		if (venue1 == null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(venue);
			ResponseEntity<ResponseStructure<Venue>> entity = new ResponseEntity<ResponseStructure<Venue>>(
					responseStructure, HttpStatus.NOT_FOUND);
			return entity;
		}
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("cannot find ");
		responseStructure.setData(venue);
		ResponseEntity<ResponseStructure<Venue>> entity = new ResponseEntity<ResponseStructure<Venue>>(
				responseStructure, HttpStatus.NOT_FOUND);
		return entity;
	}

	public ResponseEntity<ResponseStructure<Boolean>> deleteVenueById(int id) {
		ResponseStructure<Boolean> responseStructure = new ResponseStructure<Boolean>();
		boolean status = venueDao.deleteVenue(id);
		if (status) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("success");
			responseStructure.setData(status);
			ResponseEntity<ResponseStructure<Boolean>> entity = new ResponseEntity<ResponseStructure<Boolean>>(
					responseStructure, HttpStatus.OK);
			return entity;
		}
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("cannot find ");
		responseStructure.setData(status);
		ResponseEntity<ResponseStructure<Boolean>> entity = new ResponseEntity<ResponseStructure<Boolean>>(
				responseStructure, HttpStatus.OK);
		return entity;
	}
}
