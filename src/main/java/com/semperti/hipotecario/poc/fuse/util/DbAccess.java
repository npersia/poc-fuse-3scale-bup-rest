package com.semperti.hipotecario.poc.fuse.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import com.semperti.hipotecario.poc.fuse.model.Persona;
import com.semperti.hipotecario.poc.fuse.model.Personas;
import com.semperti.hipotecario.poc.fuse.model.Telefono;
import com.semperti.hipotecario.poc.fuse.model.Telefonos;
import com.semperti.hipotecario.poc.fuse.model.Domicilio;
import com.semperti.hipotecario.poc.fuse.model.Domicilios;

import java.util.List;
import java.util.ArrayList;

import java.lang.IllegalStateException;

public class DbAccess {
	private static DbAccess instance;
	private DataSource db;

	public static DbAccess getInstance() {
		if (instance == null) {
			instance = new DbAccess();
		}

		return instance;
	}

	public void setDb(DataSource ds) {
		db = ds;
	}

	protected Connection getConnection() throws SQLException {
		if (db == null)
			throw new IllegalStateException("Base de datos no seteada");
		return db.getConnection();
	}

	public Personas getPersonas() throws SQLException {
		List<Persona> personasList = new ArrayList<Persona>();
		try (Connection con = getConnection()) {
			try (Statement st = con.createStatement()) {
				try (ResultSet rs = st.executeQuery("CALL obtenerPersonas()")) {
					while(rs.next()) {
						personasList.add(getPersonaFromResultSet(rs));
					}
				}
			}
		}

		return new Personas(personasList);
	}

	public Persona getPersona(Integer id) throws SQLException {
		try (Connection con = getConnection()) {
			try (PreparedStatement st = con.prepareStatement("CALL obtenerPersona(?)")) {
				st.setInt(1, id);
				try (ResultSet rs = st.executeQuery()) {
					while(rs.next()) {
						return getPersonaFromResultSet(rs);
					}
				}
			}
		}

		return null;
	}

	private Persona getPersonaFromResultSet(ResultSet rs) throws SQLException {
		Persona result = new Persona();

		result.setId(rs.getInt("id"));
		result.setIdNumeroDocumento(rs.getInt("idNumeroDocumento"));
		result.setNumeroDocumento(rs.getInt("numeroDocumento"));
		result.setIdSexo(rs.getInt("idSexo"));
		result.setApellidos(rs.getString("apellidos"));
		result.setNombres(rs.getString("nombres"));
		result.setEsPersonaFisica(rs.getBoolean("esPersonaFisica"));
		result.setEsPersonaJuridica(rs.getBoolean("esPersonaJuridica"));
		result.setValorLealtadCliente(rs.getString("valorLealtadCliente"));

		return result;
	}

	public Telefonos getTelefonos() throws SQLException {
		List<Telefono> telefonosList = new ArrayList<Telefono>();
		try (Connection con = getConnection()) {
			try (Statement st = con.createStatement()) {
				try (ResultSet rs = st.executeQuery("CALL obtenerTelefonos()")) {
					while(rs.next()) {
						telefonosList.add(getTelefonoFromResultSet(rs));
					}
				}
			}
		}

		return new Telefonos(telefonosList);
	}

	public Telefono getTelefono(Integer id) throws SQLException {
		try (Connection con = getConnection()) {
			try (PreparedStatement st = con.prepareStatement("CALL obtenerTelefono(?)")) {
				st.setInt(1, id);
				try (ResultSet rs = st.executeQuery()) {
					while(rs.next()) {
						return getTelefonoFromResultSet(rs);
					}
				}
			}
		}

		return null;
	}

	public Telefonos getTelefonosDePersona(Integer id) throws SQLException {
		List<Telefono> telefonosList = new ArrayList<Telefono>();
		try (Connection con = getConnection()) {
			try (PreparedStatement st = con.prepareStatement("CALL obtenerTelefonosDePersona(?)")) {
				st.setInt(1, id);
				try (ResultSet rs = st.executeQuery()) {
					while(rs.next()) {
						telefonosList.add(getTelefonoFromResultSet(rs));
					}
				}
			}
		}

		return new Telefonos(telefonosList);
	}

	private Telefono getTelefonoFromResultSet(ResultSet rs) throws SQLException {
		Telefono result = new Telefono();

		result.setId(rs.getInt("id"));
		result.setIdTipoTelefono(rs.getInt("idTipoTelefono"));
		result.setCodigoPais(rs.getInt("codigoPais"));
		result.setCodigoArea(rs.getInt("codigoArea"));
		result.setPrefijo(rs.getInt("prefijo"));
		result.setCaracteristica(rs.getInt("caracteristica"));
		result.setNumero(rs.getInt("numero"));
		result.setInterno(rs.getInt("interno"));
		result.setPrioridad(rs.getInt("prioridad"));
		result.setEsListaNegra(rs.getBoolean("esListaNegra"));
		result.setNumeroNormalizado(rs.getString("numeroNormalizado"));
		result.setIdPersona(rs.getInt("idPersona"));

		return result;
	}

	public Domicilios getDomicilios() throws SQLException {
		List<Domicilio> domiciliosList = new ArrayList<Domicilio>();
		try (Connection con = getConnection()) {
			try (Statement st = con.createStatement()) {
				try (ResultSet rs = st.executeQuery("CALL obtenerDomicilios()")) {
					while(rs.next()) {
						domiciliosList.add(getDomicilioFromResultSet(rs));
					}
				}
			}
		}

		return new Domicilios(domiciliosList);
	}

	public Domicilio getDomicilio(Integer id) throws SQLException {
		try (Connection con = getConnection()) {
			try (PreparedStatement st = con.prepareStatement("CALL obtenerDomicilio(?)")) {
				st.setInt(1, id);
				try (ResultSet rs = st.executeQuery()) {
					while(rs.next()) {
						return getDomicilioFromResultSet(rs);
					}
				}
			}
		}

		return null;
	}

	public Domicilios getDomiciliosDePersona(Integer id) throws SQLException {
		List<Domicilio> domiciliosList = new ArrayList<Domicilio>();
		try (Connection con = getConnection()) {
			try (PreparedStatement st = con.prepareStatement("CALL obtenerDomicilioDePersona(?)")) {
				st.setInt(1, id);
				try (ResultSet rs = st.executeQuery()) {
					while(rs.next()) {
						domiciliosList.add(getDomicilioFromResultSet(rs));
					}
				}
			}
		}

		return new Domicilios(domiciliosList);
	}

	private Domicilio getDomicilioFromResultSet(ResultSet rs) throws SQLException {
		Domicilio result = new Domicilio();

		result.setId(rs.getInt("id"));
		result.setIdTipoDomicilio(rs.getInt("idTipoDomicilio"));
		result.setCalle(rs.getString("calle"));
		result.setNumero(rs.getInt("numero"));
		result.setPiso(rs.getString("piso"));
		result.setDepartamento(rs.getString("departamento"));
		result.setCalleEntre1(rs.getString("calleEntre1"));
		result.setCalleEntre2(rs.getString("calleEntre2"));
		result.setIdCodigoPostal(rs.getInt("idCodigoPostal"));
		result.setIdCiudad(rs.getInt("idCiudad"));
		result.setIdProvincia(rs.getInt("idProvincia"));
		result.setIdPais(rs.getInt("idPais"));
		result.setUbicacion(rs.getString("ubicacion"));
		result.setBarrio(rs.getString("barrio"));
		result.setLatitud(rs.getString("latitud"));
		result.setLongitud(rs.getString("longitud"));
		result.setIdPersona(rs.getInt("idPersona"));

		return result;
	}
}
