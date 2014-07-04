/*-------------------------------------------------------------------*/
/*                                                                   */
/* Copyright IBM Corp. 2013 All Rights Reserved                      */
/*                                                                   */
/*-------------------------------------------------------------------*/
/*                                                                   */
/*        NOTICE TO USERS OF THE SOURCE CODE EXAMPLES                */
/*                                                                   */
/* The source code examples provided by IBM are only intended to     */
/* assist in the development of a working software program.          */
/*                                                                   */
/* International Business Machines Corporation provides the source   */
/* code examples, both individually and as one or more groups,       */
/* "as is" without warranty of any kind, either expressed or         */
/* implied, including, but not limited to the warranty of            */
/* non-infringement and the implied warranties of merchantability    */
/* and fitness for a particular purpose. The entire risk             */
/* as to the quality and performance of the source code              */
/* examples, both individually and as one or more groups, is with    */
/* you. Should any part of the source code examples prove defective, */
/* you (and not IBM or an authorized dealer) assume the entire cost  */
/* of all necessary servicing, repair or correction.                 */
/*                                                                   */
/* IBM does not warrant that the contents of the source code         */
/* examples, whether individually or as one or more groups, will     */
/* meet your requirements or that the source code examples are       */
/* error-free.                                                       */
/*                                                                   */
/* IBM may make improvements and/or changes in the source code       */
/* examples at any time.                                             */
/*                                                                   */
/* Changes may be made periodically to the information in the        */
/* source code examples; these changes may be reported, for the      */
/* sample code included herein, in new editions of the examples.     */
/*                                                                   */
/* References in the source code examples to IBM products, programs, */
/* or services do not imply that IBM intends to make these           */
/* available in all countries in which IBM operates. Any reference   */
/* to the IBM licensed program in the source code examples is not    */
/* intended to state or imply that IBM's licensed program must be    */
/* used. Any functionally equivalent program may be used.            */
/*-------------------------------------------------------------------*/
package com.ibm.bluemix.samples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class PostgreSQLClient {

	public PostgreSQLClient() {
		try {
			createTable();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

	/**
	 * Grab text from PostgreSQL
	 * 
	 * @return List of Strings of text from PostgreSQL
	 * @throws Exception
	 */
	public List<String> getResults() throws Exception {
		String sql = "SELECT text FROM posts ORDER BY id DESC";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			results = statement.executeQuery();
			List<String> texts = new ArrayList<String>();

			while (results.next()) {
				texts.add(results.getString("text"));
			}

			return texts;
		} finally {
			if (results != null) {
				results.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}
		}
	}

	/**
	 * Grab text from PostgreSQL
	 * 
	 * @return List of Strings of text from PostgreSQL
	 * @throws Exception
	 */
	public EntityProfile getPassword(String notesID) throws Exception {
		String sql = String.format(
				"SELECT Password, RoleID FROM profile where NotesID = '%s'", notesID);
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		EntityProfile profile = new EntityProfile();

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			results = statement.executeQuery();

			if (results.next()) {
				profile.setNotesID(notesID);
				profile.setPassword(results.getString("Password"));
				profile.setRoleID(results.getString("RoleID"));
				return profile;
			}
			return null;
		} finally {
			if (results != null) {
				results.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}
		}
	}
	
	/**
	 * Grab text from PostgreSQL
	 * 
	 * @return List of Strings of text from PostgreSQL
	 * @throws Exception
	 */
	public List<EntityProfile> getProfileList() throws Exception {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT * FROM profile");
		sqlBuilder.append(" order by NotesID");
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		EntityProfile profile = null;
		List<EntityProfile> profileList = new ArrayList<EntityProfile>();

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sqlBuilder.toString());
			results = statement.executeQuery();

			while (results.next()) {
				profile = new EntityProfile();
				if (results.getString("NotesID") != null) {
					profile.setNotesID(results.getString("NotesID"));
				}
				if (results.getString("Name") != null) {
					profile.setName(results.getString("Name"));
				}
				if (results.getString("PeMID") != null) {
					profile.setPemDisp(CommMethod.changePem(results.getString("PeMID")));
				}
				if (results.getString("ILID") != null) {
					profile.setIlDisp(results.getString("ILID"));
				}
				if (results.getString("TechDomain") != null) {
					profile.setTechDisp(CommMethod.changeTechnology(results.getString("TechDomain"), results.getString("TechOther")));
				}
				if (results.getString("Utilization") != null) {
					profile.setUtilization(results.getDouble("Utilization"));
				}
				if (results.getString("Location") != null) {
					profile.setLocation(results.getString("Location"));
				}
				if (results.getString("OnSiteFlag") != null) {
					profile.setOnSiteFlagDisp(CommMethod.changeFlag(results.getString("OnSiteFlag")));
				}
				if (results.getString("OnBenchFlag") != null) {
					profile.setOnBenchFlagDisp(CommMethod.changeFlag(results.getString("OnBenchFlag")));
				}
				if (results.getString("RegiesteredFlag") != null) {
					profile.setRegiesteredFlagDisp(CommMethod.changeFlag(results.getString("RegiesteredFlag")));
				}

				profileList.add(profile);
			}
			return profileList;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (results != null) {
				results.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}
		}
	}

	/**
	 * Grab text from PostgreSQL
	 * 
	 * @return List of Strings of text from PostgreSQL
	 * @throws Exception
	 */
	public EntityProfile getProfile(String notesID) throws Exception {
		String sql = String.format("SELECT * FROM profile where NotesID = '%s'",
				notesID);
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		EntityProfile profile = new EntityProfile();
		
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			results = statement.executeQuery();

			if (results.next()) {
				profile.setNotesID(notesID);
				profile.setPassword(results.getString("Password"));
				profile.setName(results.getString("Name"));
				profile.setPemID(results.getString("PeMID"));
				profile.setIlID(results.getString("ILID"));
				profile.setTechDomain(results.getString("TechDomain"));
				profile.setTechOther(results.getString("TechOther"));
				profile.setUtilization(results.getDouble("Utilization"));
				profile.setLocation(results.getString("Location"));
				profile.setOnSiteFlag(results.getString("OnSiteFlag"));
				profile.setOnBenchFlag(results.getString("OnBenchFlag"));
				profile.setRegiesteredFlag(results.getString("RegiesteredFlag"));
				profile.setRoleID(results.getString("RoleID"));
				return profile;
			}
			return null;
		} finally {
			if (results != null) {
				results.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}
		}
	}

	/**
	 * Insert text into PostgreSQL
	 * 
	 * param posts List of Strings of text to insert
	 * 
	 * @return number of rows affected
	 * @throws Exception
	 * @throws Exception
	 */
	public int addPosts(List<String> posts) throws Exception {
		String sql = "INSERT INTO posts (text) VALUES (?)";
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql);

			for (String s : posts) {
				statement.setString(1, s);
				statement.addBatch();
			}
			int[] rows = statement.executeBatch();
			connection.commit();

			return rows.length;
		} catch (SQLException e) {
			SQLException next = e.getNextException();

			if (next != null) {
				throw next;
			}

			throw e;
		} finally {
			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}
		}
	}

	/**
	 * Insert text into PostgreSQL
	 * 
	 * param posts List of Strings of text to insert
	 * 
	 * @return number of rows affected
	 * @throws Exception
	 * @throws Exception
	 */
	public int addProfile(String notesID, String password, String name,
			String pemID, String ilID, String techDomain, String techOther,
			double utilization, String location, String onSiteFlag,
			String onBenchFlag, String regiesteredFlag) throws Exception {

		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("INSERT INTO profile ( ");
		sqlBuilder.append("NotesID ");
		sqlBuilder.append(",Password ");
		sqlBuilder.append(",Name ");
		sqlBuilder.append(",PeMID ");
		sqlBuilder.append(",ILID ");
		sqlBuilder.append(",TechDomain ");
		sqlBuilder.append(",TechOther ");
		sqlBuilder.append(",Utilization ");
		sqlBuilder.append(",Location ");
		sqlBuilder.append(",OnSiteFlag ");
		sqlBuilder.append(",OnBenchFlag ");
		sqlBuilder.append(",RegiesteredFlag ");
		sqlBuilder.append(",RoleID ");
		sqlBuilder.append(") VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?,? )");

		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sqlBuilder.toString());

			statement.setString(1, notesID);
			statement.setString(2, password);
			statement.setString(3, name);
			statement.setString(4, pemID);
			statement.setString(5, ilID);
			statement.setString(6, techDomain);
			statement.setString(7, techOther);
			statement.setDouble(8, utilization);
			statement.setString(9, location);
			statement.setString(10, onSiteFlag);
			statement.setString(11, onBenchFlag);
			statement.setString(12, regiesteredFlag);
			System.out.println(notesID);
			if (notesID.equals("notes@cn.ibm.com")) {
				System.out.println("true");
				statement.setString(13, "1000");
			}
			else {
				System.out.println("False");
				statement.setString(13, "1001");
			}

			return statement.executeUpdate();
		} catch (SQLException e) {
			SQLException next = e.getNextException();

			if (next != null) {
				throw next;
			}

			throw e;
		} finally {
			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}
		}
	}

	public int updateProfile(String notesID, String pemID, String ilID,
							 String techDomain, String techOther, double utilization,
							 String location, String onSiteFlag, String onBenchFlag,
							 String regiesteredFlag) throws Exception {
		
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("UPDATE profile SET ");
		sqlBuilder.append("PeMID = ?, ");
		sqlBuilder.append("ILID = ?, ");
		sqlBuilder.append("TechDomain = ?, ");
		sqlBuilder.append("TechOther = ?, ");
		sqlBuilder.append("Utilization = ?, ");
		sqlBuilder.append("Location = ?, ");
		sqlBuilder.append("OnSiteFlag = ?, ");
		sqlBuilder.append("OnBenchFlag = ?, ");
		sqlBuilder.append("RegiesteredFlag = ? ");
		sqlBuilder.append("WHERE NotesID = ?");

		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sqlBuilder.toString());

			statement.setString(1, pemID);
			statement.setString(2, ilID);
			statement.setString(3, techDomain);
			statement.setString(4, techOther);
			statement.setDouble(5, utilization);
			statement.setString(6, location);
			statement.setString(7, onSiteFlag);
			statement.setString(8, onBenchFlag);
			statement.setString(9, regiesteredFlag);
			statement.setString(10, notesID);
			
			return statement.executeUpdate();
		} catch (SQLException e) {
			SQLException next = e.getNextException();

			if (next != null) {
				throw next;
			}

			throw e;
		} finally {
			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}
		}
	}
	
	/**
	 * Delete all rows from PostgreSQL
	 * 
	 * @return number of rows affected
	 * @throws Exception
	 */
	public int deleteAll() throws Exception {
		String sql = "DELETE FROM posts WHERE TRUE";
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			return statement.executeUpdate();
		} finally {
			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}
		}
	}

	/**
	 * Grab text from PostgreSQL
	 * 
	 * @return List of Strings of text from PostgreSQL
	 * @throws Exception
	 */
	public List<EntityTrack> getTrackList(String notesID, String roleID) throws Exception {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT * FROM track");
		if (!roleID.equals("1000"))
		{
			String str = String.format(" where NotesID='%s'", notesID);
			sqlBuilder.append(str);
		}
		sqlBuilder.append(" order by AddDate desc");
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		EntityTrack track = null;
		List<EntityTrack> trackList = new ArrayList<EntityTrack>();

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sqlBuilder.toString());
			results = statement.executeQuery();

			while (results.next()) {
				track = new EntityTrack();
				if (results.getString("NotesID") != null) {
					track.setNotesID(results.getString("NotesID"));
				}
				if (results.getString("LiquidID") != null) {
					track.setLiquidID(results.getString("LiquidID"));
				}
				if (results.getString("Name") != null) {
					track.setName(results.getString("Name"));
				}
				if (results.getString("TechDomain") != null) {
					track.setTechDomain(results.getString("TechDomain"));
					track.setTechDisp(CommMethod.changeTechnology(results.getString("TechDomain"), results.getString("TechOther")));
				}
				if (results.getString("TechOther") != null) {
					track.setTechOther(results.getString("TechOther"));
				}
				if (results.getString("EventType") != null) {
					track.setEventType(results.getString("EventType"));
					track.setEventDisp(CommMethod.changeEvent(results.getString("EventType"), results.getString("EventOther")));
				}
				if (results.getString("EventOther") != null) {
					track.setEventOther(results.getString("EventOther"));
				}
				if (results.getString("RegisterDate") != null) {
					track.setRegisterDate(results.getString("RegisterDate"));
				}
				if (results.getString("CompleteDate") != null) {
					track.setCompleteDate(results.getString("CompleteDate"));
				}
				if (results.getString("Status") != null) {
					track.setStatus(results.getString("Status"));
					track.setStatusDisp(CommMethod.changeStatus(results.getString("Status")));
				}
				if (results.getString("IsFirst") != null) {
					track.setIsFirst(results.getString("IsFirst"));
					track.setIsFirstDisp(CommMethod.changeFlag(results.getString("IsFirst")));
				}
				if (results.getString("IsSecond") != null) {
					track.setIsSecond(results.getString("IsSecond"));
					track.setIsSecondDisp(CommMethod.changeFlag(results.getString("IsSecond")));
				}

				track.setWinDollar(results.getDouble("WinDollar"));
				track.setWinHour(results.getDouble("WinHour"));
				track.setWinPoint(results.getDouble("WinPoint"));
				trackList.add(track);
			}
			return trackList;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (results != null) {
				results.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}
		}
	}
	
	/**
	 * Grab text from PostgreSQL
	 * 
	 * @return List of Strings of text from PostgreSQL
	 * @throws Exception
	 */
	public List<EntityTrackSum> getTrackListSum(String notesID, String roleID) throws Exception {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT ");
		sqlBuilder.append(" T1.NotesID,");
		sqlBuilder.append(" T2.PeMID,");
		sqlBuilder.append(" T2.Name,");
		sqlBuilder.append(" T1.RegisterDate,");
		sqlBuilder.append(" COUNT('X') AS LiquidCount,");
		sqlBuilder.append(" SUM(T1.WinDollar) AS WinDollar,");
		sqlBuilder.append(" SUM(T1.WinHour) AS WinHours");
		sqlBuilder.append(" FROM track T1,profile T2");
		sqlBuilder.append(" WHERE T1.NotesID = T2.NotesID");
		sqlBuilder.append(" GROUP BY T1.NotesID,T2.PeMID,T2.Name,T1.RegisterDate");
		sqlBuilder.append(" ORDER BY T2.PeMID,T1.NotesID,T1.RegisterDate");
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		EntityTrackSum trackSum = null;
		List<EntityTrackSum> trackSumList = new ArrayList<EntityTrackSum>();

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sqlBuilder.toString());
			results = statement.executeQuery();

			while (results.next()) {
				trackSum = new EntityTrackSum();
				if (results.getString("NotesID") != null) {
					trackSum.setNotesID(results.getString("NotesID"));
				}
				if (results.getString("LiquidCount") != null) {
					trackSum.setLiquidCount(results.getString("LiquidCount"));
				}
				if (results.getString("WinDollar") != null) {
					trackSum.setWinDollar(results.getString("WinDollar"));
				}
				if (results.getString("WinHours") != null) {
					trackSum.setWinHours(results.getString("WinHours"));
				}
				if (results.getString("PeMID") != null) {
					trackSum.setPeMID(CommMethod.changePem(results.getString("PeMID")));
				}
				if (results.getString("Name") != null) {
					trackSum.setName(results.getString("Name"));
				}
				if (results.getString("RegisterDate") != null) {
					trackSum.setRegisterDate(results.getString("RegisterDate"));
				}

				trackSumList.add(trackSum);
			}
			return trackSumList;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (results != null) {
				results.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}
		}
	}
	
	/**
	 * Grab text from PostgreSQL
	 * 
	 * @return List of Strings of text from PostgreSQL
	 * @throws Exception
	 */
	public EntityTrack getTrack(String notesID, String liquidID) throws Exception {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT * FROM track");
		String str = String.format(" where NotesID='%s' and LiquidID='%s'", notesID, liquidID);
		sqlBuilder.append(str);
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		EntityTrack track = null;

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sqlBuilder.toString());
			results = statement.executeQuery();

			while (results.next()) {
				track = new EntityTrack();
				if (results.getString("NotesID") != null) {
					track.setNotesID(results.getString("NotesID"));
				}
				if (results.getString("LiquidID") != null) {
					track.setLiquidID(results.getString("LiquidID"));
				}
				if (results.getString("Name") != null) {
					track.setName(results.getString("Name"));
				}
				if (results.getString("TechDomain") != null) {
					track.setTechDomain(results.getString("TechDomain"));
				}
				if (results.getString("TechOther") != null) {
					track.setTechOther(results.getString("TechOther"));
				}
				if (results.getString("EventType") != null) {
					track.setEventType(results.getString("EventType"));
				}
				if (results.getString("EventOther") != null) {
					track.setEventOther(results.getString("EventOther"));
				}
				if (results.getString("RegisterDate") != null) {
					track.setRegisterDate(results.getString("RegisterDate"));
				}
				if (results.getString("CompleteDate") != null) {
					track.setCompleteDate(results.getString("CompleteDate"));
				}
				if (results.getString("Status") != null) {
					track.setStatus(results.getString("Status"));
				}
				if (results.getString("IsFirst") != null) {
					track.setIsFirst(results.getString("IsFirst"));
				}
				if (results.getString("IsSecond") != null) {
					track.setIsSecond(results.getString("IsSecond"));
				}

				track.setWinDollar(results.getDouble("WinDollar"));
				track.setWinHour(results.getDouble("WinHour"));
				track.setWinPoint(results.getDouble("WinPoint"));
			}

			return track;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (results != null) {
				results.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}
		}
	}
	
	/**
	 * Insert text into PostgreSQL
	 * 
	 * param posts List of Strings of text to insert
	 * 
	 * @return number of rows affected
	 * @throws Exception
	 * @throws Exception
	 */
	public int addTrack(String notesID, String liquidID, String name,
			String techDomain, String techOther, String eventType,
			String eventOther, String registerDate, String completeDate,
			String status, String isFirst, String isSecond, double winDollar,
			double winHour, double winPoint) throws Exception {

		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("INSERT INTO track ( ");
		sqlBuilder.append("NotesID ");
		sqlBuilder.append(",LiquidID ");
		sqlBuilder.append(",Name ");
		sqlBuilder.append(",TechDomain ");
		sqlBuilder.append(",TechOther ");
		sqlBuilder.append(",EventType ");
		sqlBuilder.append(",EventOther ");
		sqlBuilder.append(",RegisterDate ");
		sqlBuilder.append(",CompleteDate ");
		sqlBuilder.append(",Status ");
		sqlBuilder.append(",IsFirst ");
		sqlBuilder.append(",IsSecond ");
		sqlBuilder.append(",WinDollar ");
		sqlBuilder.append(",WinHour ");
		sqlBuilder.append(",WinPoint ");
		sqlBuilder.append(",AddDate ");
		sqlBuilder.append(") VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now() )");

		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sqlBuilder.toString());

			statement.setString(1, notesID);
			statement.setString(2, liquidID);
			statement.setString(3, name);
			statement.setString(4, techDomain);
			statement.setString(5, techOther);
			statement.setString(6, eventType);
			statement.setString(7, eventOther);
			statement.setString(8, registerDate);
			statement.setString(9, completeDate);
			statement.setString(10, status);
			statement.setString(11, isFirst);
			statement.setString(12, isSecond);
			statement.setDouble(13, winDollar);
			statement.setDouble(14, winHour);
			statement.setDouble(15, winPoint);

			return statement.executeUpdate();
		} catch (SQLException e) {
			SQLException next = e.getNextException();

			if (next != null) {
				throw next;
			}

			throw e;
		} finally {
			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}
		}
	}

	/**
	 * Insert text into PostgreSQL
	 * 
	 * param posts List of Strings of text to insert
	 * 
	 * @return number of rows affected
	 * @throws Exception
	 * @throws Exception
	 */
	public int updateTrack(String notesID, String liquidID, String completeDate,
			String status, String isFirst, String isSecond, double winDollar,
			double winHour, double winPoint) throws Exception {

		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("UPDATE track SET ");
		sqlBuilder.append("CompleteDate = ?, ");
		sqlBuilder.append("Status = ?, ");
		sqlBuilder.append("IsFirst = ?, ");
		sqlBuilder.append("IsSecond = ?, ");
		sqlBuilder.append("WinDollar = ?, ");
		sqlBuilder.append("WinHour = ?, ");
		sqlBuilder.append("WinPoint = ?, ");
		sqlBuilder.append("AddDate = now() ");
		sqlBuilder.append("WHERE NotesID = ? AND LiquidID = ?");

		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sqlBuilder.toString());

			statement.setString(1, completeDate);
			statement.setString(2, status);
			statement.setString(3, isFirst);
			statement.setString(4, isSecond);
			statement.setDouble(5, winDollar);
			statement.setDouble(6, winHour);
			statement.setDouble(7, winPoint);
			statement.setString(8, notesID);
			statement.setString(9, liquidID);
			
			return statement.executeUpdate();
		} catch (SQLException e) {
			SQLException next = e.getNextException();

			if (next != null) {
				throw next;
			}

			throw e;
		} finally {
			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}
		}
	}
	
	public int deleteTrack(String notesID, String liquidID) throws Exception {

		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("delete from track ");
		sqlBuilder.append("WHERE NotesID = ? AND LiquidID = ?");

		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sqlBuilder.toString());
			
			statement.setString(1, notesID);
			statement.setString(2, liquidID);
			
			return statement.executeUpdate();
		} catch (SQLException e) {
			SQLException next = e.getNextException();

			if (next != null) {
				throw next;
			}

			throw e;
		} finally {
			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}
		}
	}
	
	private static Connection getConnection() throws Exception {
		
		String url = "jdbc:postgresql://localhost:5432/liquidInfo";
		String user = "postgres";
		String password = "xiecb";
		
		Map<String, String> env = System.getenv();

		if (env.containsKey("VCAP_SERVICES")) {
			// we are running on cloud foundry, let's grab the service details
			// from vcap_services
			JSONParser parser = new JSONParser();
			JSONObject vcap = (JSONObject) parser.parse(env
					.get("VCAP_SERVICES"));
			JSONObject service = null;

			// We don't know exactly what the service is called, but it will
			// contain "postgresql"
			for (Object key : vcap.keySet()) {
				String keyStr = (String) key;
				if (keyStr.toLowerCase().contains("postgresql")) {
					service = (JSONObject) ((JSONArray) vcap.get(keyStr))
							.get(0);
					break;
				}
			}

			if (service != null) {
				JSONObject creds = (JSONObject) service.get("credentials");
				String name = (String) creds.get("name");
				String host = (String) creds.get("host");
				Long port = (Long) creds.get("port");
				user = (String) creds.get("user");
				password = (String) creds.get("password");

				url = "jdbc:postgresql://" + host + ":" + port + "/"
						+ name;
			}
		}

		return DriverManager.getConnection(url, user, password);
	}

	/**
	 * Create the posts table if it doesn't already exist
	 * 
	 * @throws Exception
	 */
	private void createTable() throws Exception {
		StringBuilder sqlBuilder = new StringBuilder();

		StringBuilder profileBuilder = new StringBuilder();
		profileBuilder.append("CREATE TABLE IF NOT EXISTS profile ( ");
		profileBuilder.append("NotesID			TEXT PRIMARY KEY, ");
		profileBuilder.append("Password			TEXT NOT NULL, ");
		profileBuilder.append("Name				TEXT NOT NULL, ");
		profileBuilder.append("PeMID			TEXT NOT NULL, ");
		profileBuilder.append("ILID				TEXT NOT NULL, ");
		profileBuilder.append("TechDomain		TEXT NOT NULL, ");
		profileBuilder.append("TechOther		TEXT, ");
		profileBuilder.append("Utilization		NUMERIC NOT NULL, ");
		profileBuilder.append("Location 		TEXT NOT NULL, ");
		profileBuilder.append("OnSiteFlag		TEXT NOT NULL, ");
		profileBuilder.append("OnBenchFlag		TEXT NOT NULL, ");
		profileBuilder.append("RegiesteredFlag	TEXT NOT NULL, ");
		profileBuilder.append("RoleID			TEXT NOT NULL ");
		profileBuilder.append("); ");

		StringBuilder trackBuilder = new StringBuilder();
		trackBuilder.append("CREATE TABLE IF NOT EXISTS track ( ");
		trackBuilder.append("NotesID		TEXT NOT NULL, ");
		trackBuilder.append("LiquidID		TEXT NOT NULL, ");
		trackBuilder.append("Name			TEXT NOT NULL, ");
		trackBuilder.append("TechDomain		TEXT NOT NULL, ");
		trackBuilder.append("TechOther		TEXT, ");
		trackBuilder.append("EventType		TEXT NOT NULL, ");
		trackBuilder.append("EventOther		TEXT, ");
		trackBuilder.append("RegisterDate	TEXT NOT NULL, ");
		trackBuilder.append("CompleteDate	TEXT, ");
		trackBuilder.append("Status			TEXT NOT NULL, ");
		trackBuilder.append("IsFirst		TEXT NOT NULL, ");
		trackBuilder.append("IsSecond		TEXT NOT NULL, ");
		trackBuilder.append("WinDollar		NUMERIC, ");
		trackBuilder.append("WinHour		NUMERIC, ");
		trackBuilder.append("WinPoint		NUMERIC, ");
		trackBuilder.append("AddDate		TIMESTAMP NOT NULL, ");
		trackBuilder
				.append("CONSTRAINT pk_idLiquid PRIMARY KEY(NotesID,LiquidID) ");
		trackBuilder.append("); ");

		sqlBuilder.append(profileBuilder.toString());
		sqlBuilder.append(trackBuilder.toString());
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = getConnection();
			statement = connection.prepareStatement(sqlBuilder.toString());
			statement.executeUpdate();
		} catch (SQLException e) {
			SQLException next = e.getNextException();

			if (next != null) {
				throw next;
			}

			throw e;
		} finally {
			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}
		}
	}
}
