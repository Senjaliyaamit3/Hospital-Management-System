package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {
	private Connection connection;
	
	public Doctor(Connection connection)
	{
		this.connection=connection;
	}
	
	public void viewDoctors()
	{
		String query="select * from doctors";
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet=preparedStatement.executeQuery();
			System.out.println("Doctors: ");
			System.out.println("+------------+---------------+-------------------+");
			System.out.println("| Doctor Id  |     Name      |   Specialization  |");
			
			System.out.println("+------------+---------------+---------+---------+");
			
			while(resultSet.next())
			{
				int id=resultSet.getInt("id");
				String name=resultSet.getString("name");
				String specialization=resultSet.getString("specialization");
				System.out.printf("|%-12s|%-15s|%-19s|\n",id,name,specialization);
				System.out.println("+------------+---------------+---------+---------+");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean getDoctorById(int id)
	{
		String query="SELECT * FROM doctors WHERE id = ?";
		try
		{
			PreparedStatement pr=connection.prepareStatement(query);
			
			pr.setInt(1, id);
			ResultSet resultSet = pr.executeQuery();
			if(resultSet.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
