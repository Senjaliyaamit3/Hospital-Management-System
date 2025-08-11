package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class HospitalManagementSystem {
	
	private static final String url="jdbc:mysql://localhost:3306/hospital";
	private static final String username="root";
	private static final String password="amitSenjaliya@333";
	
	public static void main(String[] args)
	{		
		Scanner sc=new Scanner(System.in);
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,username,password);
			Patient p=new Patient(con, sc);
			Doctor doctor=new Doctor(con);
			
			while(true)
			{
				System.out.println("HOSPITAL MANAGEMENT SYSTEM");
				System.out.println("1. Add Patient");
				System.out.println("2. View Patients");
				System.out.println("3. View Doctors");
				System.out.println("4. Book Appointments");
				System.out.println("5. Exit");
				
				
				System.out.println("Enter your choice: ");
				int choice=sc.nextInt();
				
				switch(choice)
				{
					case 1:
						// Add Patient
						p.addPatient();
						System.out.println();
						break;
					case 2:
						// view Patient
						p.viewPatients();
						System.out.println();
						break;
					case 3:
						// view Doctor
						doctor.viewDoctors();
						System.out.println();
						break;
					case 4:
						//Book Appointment
						bookAppointment(p, doctor, con, sc);
						System.out.println();
						break;
					case 5:
					    try {
					        sc.close();
					        con.close();
					        System.out.println("Resources closed. Exiting...");
					    } catch (SQLException e) {
					        e.printStackTrace();
					    }
					    return;

					default:
						System.out.println("Enter Valid choice !!");
				}			
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	public static void bookAppointment(Patient pateint,Doctor doctor,Connection con,Scanner sc)
	{
		System.out.print("Enter Patient id");
		int patient_id=sc.nextInt();
		System.out.print("Enter Doctor id: ");
		int Doctor_id =sc.nextInt();
		System.out.println("Enter appointment date (YYYY-MM-DD):");
		String Appoint_date=sc.next();
		
		if(pateint.getPatientById(patient_id) && doctor.getDoctorById(Doctor_id))
		{
			if(checkDoctorAvailability(Doctor_id,Appoint_date,con))
			{
				String appointmentQuery = "Insert into appointments(patient_id,doctor_id,appointment_date)VALUES(?,?,?)";
				try
				{
					PreparedStatement ps=con.prepareStatement(appointmentQuery);
					ps.setInt(1,patient_id);
					ps.setInt(2,Doctor_id);
					ps.setString(3,Appoint_date);
					int rowsAffected = ps.executeUpdate();
					if(rowsAffected > 0)
					{
						System.out.println("Appointment Booked !");
					}
					else
					{
						System.out.println("Failed to Book Appointment");
					}
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
				
			}
			else
			{
				System.out.println("Doctor not Available on this date !!");
			}
		}
		else
		{
			System.out.println("Either Doctor or patient doesn't exist!!!");
		}
	}
	public static boolean checkDoctorAvailability(int doctorId,String appointmentDate,Connection connection)
	{
		String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appointment_date = ?";
		
		try
		{
			PreparedStatement ps= connection.prepareStatement(query);
			
			ps.setInt(1, doctorId);
			ps.setString(2, appointmentDate);
			
			ResultSet resultSet = ps.executeQuery();
			
			if(resultSet.next())
			{
				int count = resultSet.getInt(1);
				if(count == 0)
				{
					return true;
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
}