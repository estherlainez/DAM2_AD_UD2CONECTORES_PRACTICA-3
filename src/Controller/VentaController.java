package Controller;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import Model.Vehiculo;
import Model.Venta;
import Vista.Main;

public class VentaController {
	private Connection con;
	public VentaController() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.con=DriverManager.getConnection(Main.url,Main.user,Main.password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean insertarVenta(Venta v) {
		int id=v.getId();
		String matricula=v.getMatricula();
		String nifCliente=v.getDniCliente();
		double desc=v.getDescuento();
		String motDes=v.getMotivoDes();
		LocalDate fecha=v.getFecha();
		
		String nuevaVenta="insert into ventas values(?,?,?,?,?,?)";
		try {
			PreparedStatement p=con.prepareStatement(nuevaVenta);
			p.setInt(1, id);
			p.setString(2, matricula);
			p.setString(3,nifCliente );
			p.setDouble(4, desc);
			p.setString(5,motDes );
			p.setDate(6, java.sql.Date.valueOf(fecha));
			
			if(p.executeUpdate()==1) {
				p.close();
				return true;
			}else{
				p.close();
				return false;
			}
		
		}catch(MySQLIntegrityConstraintViolationException e){
			e.getErrorCode();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return false;
	}

	public ArrayList<Venta> listadoVentas(LocalDate fecha) {
		String sql="select * from ventas where ventas.fecha=?";
		try {
			PreparedStatement sentencia=con.prepareStatement(sql);
			sentencia.setDate(1, java.sql.Date.valueOf(fecha));
			
			ResultSet resul=sentencia.executeQuery();
			
			ArrayList <Venta> ventas=new ArrayList<Venta>();
			while(resul.next()) {
				int id=resul.getInt("id");
				String matricula=resul.getString("matricula");
				String nif=resul.getString("nifCliente");
				double  descuento=resul.getDouble("descuento");
				String motivo=resul.getString("motivoDescuento");
				fecha=LocalDate.parse(resul.getString("fecha"));
				
				ventas.add(new Venta(id,matricula,nif,descuento,motivo,fecha));
			}
			resul.close();
			return ventas;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Venta> listarVentas() {
		String sql="select * from ventas ";
		try {
			Statement st=con.createStatement();
			ResultSet resul=st.executeQuery(sql);
			
			ArrayList <Venta> ventas=new ArrayList<Venta>();
			while(resul.next()) {
				int id=resul.getInt("id");
				String matricula=resul.getString("matricula");
				String nif=resul.getString("nifCliente");
				double  descuento=resul.getDouble("descuento");
				String motivo=resul.getString("motivoDescuento");
				LocalDate fecha=LocalDate.parse(resul.getString("fecha"));
				
				ventas.add(new Venta(id,matricula,nif,descuento,motivo,fecha));
			}
			resul.close();
			return ventas;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public int verificarMatriculaListaVentas(String ma) {
		String sql="select count(*) from ventas where ventas.matricula= ?";
		int n=0;
		try {
	
			PreparedStatement sentencia=con.prepareStatement(sql);
			sentencia.setString(1, ma);
			
			ResultSet re=sentencia.executeQuery();
			if(re.next()) {
				n=re.getInt(1);
				
			}
			sentencia.close();
		
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
		
	}
	
	public int verificarMatriculaVehiculos(String ma) {
		String sql="select count(*) from vehiculo where vehiculo.matricula= ?";
		int n=0;
		try {
	
			PreparedStatement sentencia=con.prepareStatement(sql);
			sentencia.setString(1, ma);
			
			ResultSet re=sentencia.executeQuery();
			if(re.next()) {
				n=re.getInt(1);
				
			}
			sentencia.close();
		
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
		
	}
	public double sumaPreciosVenta() {
		
		return 0;
		
	}
}
