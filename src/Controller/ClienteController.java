package Controller;
import java.sql.*;
import java.util.ArrayList;
import Vista.Main;
import Model.Vehiculo;
import Model.Cliente;
public class ClienteController {
	private Connection con;
	
	public ClienteController() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.con=DriverManager.getConnection(Main.url,Main.user,Main.password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean insertarCliente(Cliente c) {
		String d=c.getDni();
		String n=c.getNombre();
		String a=c.getApellidos();
		
		String nuevoCliente="insert into clientes values(?,?,?)";
		try {
			PreparedStatement p=con.prepareStatement(nuevoCliente);
			p.setString(1, d);
			p.setString(2, n);
			p.setString(3, a);
			if(p.executeUpdate()==1) {
				p.close();
				return true;
			}else{
				p.close();
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	public boolean modificarCliente(Cliente c,String dni) {
		String d=c.getDni();
		String n=c.getNombre();
		String a=c.getApellidos();

		String modifCliente="update clientes set dni=?,nombre=?,apellidos=? where clientes.dni=? ";
		try {
			PreparedStatement ps=con.prepareStatement(modifCliente);
			ps.setString(1, d);
			ps.setString(2, n);
			ps.setString(3, a);
			ps.setString(4, dni);
			
			if(ps.executeUpdate()>=1) {
				ps.close();
				return true;
			}
			else {
				ps.close();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	public ArrayList<Cliente> mostrarClientes() {
		String sql="select * from clientes";
		try {
			Statement miStatement=con.createStatement();
			ResultSet rs = miStatement.executeQuery(sql);
			ArrayList <Cliente> clientes=new ArrayList<Cliente>();
			while(rs.next()) {
				String d=rs.getString("dni");
				String n=rs.getString("nombre");
				String ap=rs.getString("apellidos");
				
				clientes.add(new Cliente(d,n,ap));
			}
			rs.close();
			return clientes;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Cliente>mostrarClientePorDni(String dniB){
			String sql="select * from clientes where clientes.dni=?";
			try {
				PreparedStatement pre=con.prepareStatement(sql);
				pre.setString(1, dniB);
				ResultSet r=pre.executeQuery();
				ArrayList<Cliente>clientes=new ArrayList<Cliente>();
				while(r.next()) {
					String d=r.getNString("dni");
					String n=r.getString("nombre");
					String a=r.getString("apellidos");
					clientes.add(new Cliente(d,n,a));
					
				}
				r.close();
				
				return clientes;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return null;
		
	}

}
