package com.internship.registration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDAO {
	private Connection con;
	public RegistrationDAO(Connection con) {
		this.con = con;
	}
	public void createRecord(Registration registration) throws ClassNotFoundException {
		try {
            if (con == null ) {
                this.con = DbConnection.getConnection();
            }
		try (
				PreparedStatement pst = con.prepareStatement(
						"INSERT INTO Registration (Name, Email, ContactNumber) VALUES (?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS)) {

			pst.setString(1, registration.getName());
			pst.setString(2, registration.getEmail());
			pst.setString(3, registration.getContactNumber());
			int affectedRows = pst.executeUpdate();

			if (affectedRows > 0) {
				try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						registration.setId(generatedKeys.getInt(1));
						System.out.println("Record created successfully! ID: " + registration.getId());
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Registration> readRecords() throws ClassNotFoundException {
		List<Registration> registrations = new ArrayList<>();
		 try {
	            if (con == null) {
	                this.con = DbConnection.getConnection();
	            }
		try (
				Statement statement = con.createStatement();
				ResultSet rs = statement.executeQuery("SELECT * FROM Registration")) {

			while (rs.next()) {
				Registration registration = new Registration();
				registration.setId(rs.getInt("ID"));
				registration.setName(rs.getString("Name"));
				registration.setEmail(rs.getString("Email"));
				registration.setContactNumber(rs.getString("ContactNumber"));
				registrations.add(registration);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}catch (SQLException e) {
		e.printStackTrace();
	}
			return registrations;

	}

	public void updateRecord(Registration registration) throws ClassNotFoundException {
		 try {
	            if (con == null) {
	                this.con = DbConnection.getConnection();
	            }
		try (
				PreparedStatement preparedStatement = con
						.prepareStatement("UPDATE Registration SET Name=?, Email=?, ContactNumber=? WHERE ID=?")) {

			preparedStatement.setString(1, registration.getName());
			preparedStatement.setString(2, registration.getEmail());
			preparedStatement.setString(3, registration.getContactNumber());
			preparedStatement.setInt(4, registration.getId());

			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows > 0) {
				System.out.println("Record updated successfully!");
			} else {
				System.out.println("Record not found for update.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}catch (SQLException e) {
		e.printStackTrace();
	}
	}

	public void deleteRecord(int recordId) throws ClassNotFoundException {
		 try {
	            if (con == null ) {
	                this.con = DbConnection.getConnection();
	            }
		try (
				PreparedStatement preparedStatement = con
						.prepareStatement("DELETE FROM Registration WHERE ID=?")) {

			preparedStatement.setInt(1, recordId);

			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows > 0) {
				System.out.println("Record deleted successfully!");
			} else {
				System.out.println("Record not found for deletion.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}catch (SQLException e) {
		e.printStackTrace();
	}
}
}
