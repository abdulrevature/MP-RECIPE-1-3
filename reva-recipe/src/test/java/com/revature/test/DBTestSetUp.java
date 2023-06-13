package com.revature.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DBTestSetUp {

	private static Connection conn;

	static {
		try {
			conn = DriverManager.getConnection("jdbc:h2:./h2/db", "sa", "sa");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection GET_CONNECTION() {
		return conn;
	}

	private static StringBuilder ddlScript;

	static {
		try {
			Scanner sc = new Scanner(new FileInputStream("./src/main/resources/ddl.sql"));
			while (sc.hasNextLine()) {
				ddlScript.append(sc.nextLine() + " ");
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static StringBuilder dmlScript;

	static {
		try {
			Scanner sc = new Scanner(new FileInputStream("./src/test/resources/dml.sql"));
			while (sc.hasNextLine()) {
				dmlScript.append(sc.nextLine() + " ");
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void RUN_DDL() {
		try {
			conn.prepareStatement(ddlScript.toString()).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void RUN_DML() {
		try {
			conn.prepareStatement(dmlScript.toString()).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
