package com.semperti.hipotecario.poc.fuse.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.semperti.hipotecario.poc.fuse.model.Persona;
import com.semperti.hipotecario.poc.fuse.model.Telefono;
import com.semperti.hipotecario.poc.fuse.model.BupRestService;

import com.semperti.hipotecario.poc.fuse.util.DbAccess;

import java.sql.SQLException;

public class BupRestServiceImpl implements BupRestService {
	private static final Logger logger = LoggerFactory.getLogger(BupRestServiceImpl.class);
	private DbAccess db;

	public BupRestServiceImpl() {
		db = DbAccess.getInstance();
	}

	public Response obtenerDomicilios() throws SQLException {
		return Response.ok(db.getDomicilios()).build();
	}

	public Response obtenerDomicilio(Integer id) throws SQLException {
		return Response.ok(db.getDomicilio(id)).build();
	}

	public Response obtenerPersonas() throws SQLException {
		return Response.ok(db.getPersonas()).build();
	}

	public Response obtenerDomiciliosDePersona(Integer id) throws SQLException {
		return Response.ok(db.getDomiciliosDePersona(id)).build();
	}

	public Response obtenerPersona(Integer id) throws SQLException {
		return Response.ok(db.getPersona(id)).build();
	}

	public Response obtenerTelefonosDePersona(Integer id) throws SQLException {
		return Response.ok(db.getTelefonosDePersona(id)).build();
	}

	public Response obtenerTelefonos() throws SQLException {
		return Response.ok(db.getTelefonos()).build();
	}

	public Response obtenerTelefono(Integer id) throws SQLException {
		return Response.ok(db.getTelefono(id)).build();
	}
}
