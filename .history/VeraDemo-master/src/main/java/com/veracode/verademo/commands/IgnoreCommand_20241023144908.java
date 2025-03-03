package com.veracode.verademo.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
//test
//test2222
//maitest
public class IgnoreCommand implements BlabberCommand {	
	private static final Logger logger = LogManager.getLogger("VeraDemo:IgnoreCommand");
	//testing!!!!!!
	private Connection connect;
	
	private String username;

	public IgnoreCommand(Connection connect, String username) {
		super();
		this.connect = connect;
		this.username = username;
		this.password = password;
	}

	@Override
	public void execute(String blabberUsername) {
		String sqlQuery = "DELETE FROM listeners WHERE blabber=? AND listener=?;";
		logger.info(sqlQuery);
		PreparedStatement action;
		try {
			action = connect.prepareStatement(sqlQuery);
			
			action.setString(1, blabberUsername);
			action.setString(2, username);
			action.execute();
						
			sqlQuery = "SELECT blab_name FROM users WHERE username = '" + blabberUsername + "'";
			Statement sqlStatement = connect.createStatement();
			logger.info(sqlQuery);
			ResultSet result = sqlStatement.executeQuery(sqlQuery);
			result.next();
			
			/* START BAD CODE */
			String event = username + " is now ignoring " + blabberUsername + "(" + result.getString(1) + ")";
			sqlQuery = "INSERT INTO users_history (blabber, event) VALUES (\"" + username + "\", \"" + event + "\")";
			logger.info(sqlQuery);
			sqlStatement.execute(sqlQuery);
			/* END BAD CODE */
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
