package Controller;

import java.sql.*;
import java.util.ArrayList;
import Vista.Main;
import Model.Vehiculo;



   public class VehiculoController {
	private Connection con;
	
	public VehiculoController() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.con=DriverManager.getConnection(Main.url,Main.user,Main.password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean insertarVehiculo(Vehiculo v) {
		
		String matr=v.getMatricula();
		String marca=v.getMarca();
		String modelo=v.getModelo();
		double precio=v.getPrecioVenta();
		try {
			String insert= "insert into vehiculo values(?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(insert);
			ps.setString(1, matr);
			ps.setString(2, marca);
			ps.setString(3, modelo);
			ps.setDouble(4, precio);
			
			if(ps.executeUpdate()==1) {
				ps.close();
				return true;
				
			}else {
				ps.close();
				return false;
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			e1.getErrorCode();
		}
		return false;

	}
	
	public boolean modificarVehiculos(Vehiculo v,String matricula) {
	
		String mat=v.getMatricula();
		String mar=v.getMarca();
		String mo=v.getModelo();
		double pre=v.getPrecioVenta();
		
		try {
			String modificar= "update vehiculo set matricula=?,marca=?,modelo=?,precio=? where vehiculo.matricula=?";
			PreparedStatement ps=con.prepareStatement(modificar);
			ps.setString(1,mat);
			ps.setString(2,mar);
			ps.setString(3,mo);
			ps.setDouble(4,pre);
			ps.setString(5,matricula);
			
			int va=ps.executeUpdate();
			if(va>=1) {
				ps.close();
				return true;
				
			}else {
				ps.close();
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
	
	public Vehiculo informacionDelVehiculo(String m) {
		String sql="select * from vehiculo where vehiculo.matricula=?";
		try {
			PreparedStatement sentencia=con.prepareStatement(sql);
			sentencia.setString(1, m);
			ResultSet resul=sentencia.executeQuery();
			Vehiculo ve=null;
			while(resul.next()) {
				String matricula=resul.getString("matricula");
				String marca=resul.getString("marca");
				String modelo=resul.getString("modelo");
				double precio=resul.getDouble("precio");
				ve=new Vehiculo(matricula,marca,modelo,precio);
			}
			sentencia.close();
			return ve;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Vehiculo> mostrarVehiculos(String marcaB) {
		String sql="select * from vehiculo where vehiculo.marca=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, marcaB);
			ResultSet resul=ps.executeQuery();
			
			ArrayList <Vehiculo> vehiculos=new ArrayList<Vehiculo>();
			while(resul.next()) {
				String matricula=resul.getString("matricula");
				String marca=resul.getString("marca");
				String modelo=resul.getString("modelo");
				double precio=resul.getDouble("precio");
				vehiculos.add(new Vehiculo(matricula,marca,modelo,precio));
			}
			resul.close();
			return vehiculos;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
