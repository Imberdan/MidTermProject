package tw.com.eeit.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArmyImp implements InterArmy{

	private Connection conn;

	@Override
	public void add(ArmyMember am) throws SQLException {
		String sqlstr = "Insert into Army(BMIRange, ArmyType, Number) Values(?, ?, ?)";
		PreparedStatement state = conn.prepareStatement(sqlstr);
		state.setString(1, am.getBMIRange());
		state.setString(2, am.getArmyType());
		state.setInt(3, am.getNumber());
		state.setString(4, am.getPer());
		state.executeUpdate();
		state.close();
		
	}

	@Override
	public void update(ArmyMember am) throws SQLException {
		String sqlstr = "Update into Army(BMIRange, ArmyType, Number) Values(?, ?, ?)";
		PreparedStatement state = conn.prepareStatement(sqlstr);
		state.setString(1, am.getBMIRange());
		state.setString(2, am.getArmyType());
		state.setInt(3, am.getNumber());
		state.setString(4, am.getPer());
		state.executeUpdate();
		state.close();
		
	}

	@Override
	public void deleteById(int id) throws SQLException {
		String sqlstr = "Delete from Army where id=?";
		PreparedStatement state = conn.prepareStatement(sqlstr);
		state.setInt(1, id);
		state.executeUpdate();
		state.close();
		
	}

	@Override
	public ArmyMember findById(int id) throws SQLException {
		String sqlstr = "Select * from Army where id=?";
		PreparedStatement state = conn.prepareStatement(sqlstr);
		state.setInt(1, id);
		ResultSet rs = state.executeQuery();
		ArmyMember am = null;
		if(rs.next()) {
			am = new ArmyMember(rs.getInt("Id"), rs.getString("BMIRange"), rs.getString("ArmyType"), rs.getInt("Number"), rs.getString("Per"));
		}
		rs.close();
		state.close();
		return am;
	}

	@Override
	public void createConn() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String urlstr = "jdbc:sqlserver://localhost:1433;databaseName=OliverLiu;user=watcher;password=P@ssw0rd;encrypt=true;trustServerCertificate=true";
		conn = DriverManager.getConnection(urlstr);
		System.out.println("Connection Status:" + !conn.isClosed());
		
	}

	@Override
	public void closeConn() throws SQLException {
		if(conn==null) {
			conn.close();
		}
		
	}

}
