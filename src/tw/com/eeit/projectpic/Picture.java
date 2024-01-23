package tw.com.eeit.projectpic;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import tw.oliver.jdbc.util.JdbcConnUtil;

public class Picture {

	private static Connection conn;

	public void processAction() throws Exception {

		JdbcConnUtil util = new JdbcConnUtil();
		conn = util.createConn();

		imageStoreProcess();

		imageRetrieveProcess();

		//util.closeConn();

	}
	
	public void closeAction() throws Exception {

		JdbcConnUtil util = new JdbcConnUtil();
		util.closeConn();

	}
	
	private void imageRetrieveProcess() throws SQLException, IOException {
		String sqlstr = "select * from Gallery where galleryId=?";
		PreparedStatement state = conn.prepareStatement(sqlstr);
		state.setInt(1, 1);
		ResultSet rs = state.executeQuery();

		if (rs.next()) {
			Blob blob = rs.getBlob(3);
			int length = (int) blob.length();
			System.out.println("length:" + length);

			BufferedOutputStream bos1 = new BufferedOutputStream(new FileOutputStream("c:/temp/myImages01.jpg"));
			bos1.write(blob.getBytes(1, length));
			bos1.flush();
			bos1.close();

			rs.close();
			state.close();
		}
	}

	private void imageStoreProcess() throws FileNotFoundException, SQLException {
		FileInputStream fis1 = new FileInputStream("c:/temp/mhwilds.jpg");

		String sqlstr = "insert into Picture(FileName, FileContent) values(?,?)";
		PreparedStatement state = conn.prepareStatement(sqlstr);
		state.setString(1, "mhwilds.jpg");
		state.setBinaryStream(2, fis1);
		state.executeUpdate();
		state.close();
		// System.out.println("File Stored");
	}

	public static void main(String[] args) throws Exception {
		Picture pic = new Picture();
		pic.processAction();
		
		Scanner scanner = new Scanner(System.in);

		System.out.print("輸入圖片id:");
		int imageId = scanner.nextInt();

		String sqlstr = "Select * from Picture where FILEID=?";
		PreparedStatement state = conn.prepareStatement(sqlstr);
		state.setInt(1, imageId);
		ResultSet rs = state.executeQuery();
		
		if (rs.next()) {
            // Get the image data as a byte array
			byte[] imageData1 = rs.getBytes("FILEID");
			byte[] imageData2 = rs.getBytes("FILENAME");
			byte[] imageData3 = rs.getBytes("FILECONTENT");

            // Write the image data to a text file on the desktop
            String filePath = "c:/temp/" + "retrieved_image_" + imageId + ".txt";
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(new String(imageData1)); 
                fileWriter.write(new String(imageData2));
                fileWriter.write(new String(imageData3));
                System.out.println("Image data saved to: " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Image with ID " + imageId + " not found.");
        }
		
		
		scanner.close();
		state.close();
		pic.closeAction();
	}

}
