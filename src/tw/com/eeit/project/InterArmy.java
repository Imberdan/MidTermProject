package tw.com.eeit.project;

import java.sql.SQLException;


public interface InterArmy {
	public void add(ArmyMember m) throws SQLException;
	public void update(ArmyMember m) throws SQLException;
	public void deleteById(int id) throws SQLException;
	public ArmyMember findById(int id) throws SQLException;
	public void createConn() throws Exception; 
	public void closeConn() throws SQLException;
}
