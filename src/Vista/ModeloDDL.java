package Vista;
import java.sql.*;
public class ModeloDDL {
	
	private static String misTablas[]= {"vehiculos","clientes","ventas"};
	private static boolean  prueba[]=new boolean[misTablas.length];
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/","root","12345678");
			System.out.println(comprobarDB(con));
			crearDatabase(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean comprobarDB(Connection con) {
		try {
			DatabaseMetaData dbmd=con.getMetaData();
			String tipos[]= {"TABLE"};
			ResultSet re=dbmd.getTables(null, null, null, tipos);
			
			while(re.next()) {
				
				for(int i=0;i<misTablas.length;i++) {
					if(re.getString("TABLE_NAME").equals(misTablas[i])) {
						prueba[i]=true;
					}
				}
			}
			
			for(int i=0; i<prueba.length; i++) {
				if(prueba[i]==false) {
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	public static boolean crearDatabase(Connection con) {
		String sql="create database if not exists TALLER";
		Statement consulta;
		try {
			consulta=con.createStatement();
			consulta.executeUpdate(sql);
			sql="USE TALLER";
			consulta.execute(sql);
			
			sql="Create table clientes("
					+"dni varchar (10),"
					+"nombre varchar (20),"
					+"apellidos varchar (20),"
					+"constraint clientesPK primary key(dni)"
					+");";
			consulta.executeUpdate(sql);
			
			sql="Create table vehiculo("
					+"matricula varchar (10),"
					+"marca varchar (20),"
					+"modelo varchar (20),"
					+"precio double,"
					+"constraint vehiculo primary key(matricula)"
					+");";
			consulta.executeUpdate(sql);
			
			sql="Create table ventas("
					+"id int(2),"
					+"matricula varchar (10),"
					+"nifCliente varchar (10),"
					+"descuento double,"
					+"motivoDescuento varchar (100),"
					+"fecha date,"
					+"constraint ventas primary key(matricula)"
					+");";
			consulta.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

}
