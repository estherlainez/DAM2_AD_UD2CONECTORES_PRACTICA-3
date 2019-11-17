package Vista;
import java.time.LocalDate;
import java.util.*;
import Controller.ClienteController;
import Controller.VehiculoController;
import Controller.VentaController;
import Model.Cliente;
import Model.Vehiculo;
import Model.Venta;
public class Main {
	public static String  url="jdbc:mysql://localhost/initiald";
	public static String user="root";
	//public static String password="";
	public static String password="12345678";
	public static void main(String[] args) {
		
		Scanner teclado=new Scanner(System.in);
		int opcion;
		String ma="",mar="",mo="",dni="",nombre="",apellidos="";
		VehiculoController vc=new VehiculoController();
		ClienteController cc=new ClienteController();
		VentaController vcon=new VentaController();
		
		double p=0;
		do {
			System.out.println("TALLER DE VEHICULOS");
			System.out.println("1.Insertar Vehiculo");
			System.out.println("2.Modificar Vehiculo");
			System.out.println("3.Mostar datos de un vehiculo");
			System.out.println("4.Mostar vehiculos de una marca");
			System.out.println("5.Insertar Cliente");
			System.out.println("6.Modificar Cliente");
			System.out.println("7.Mostar Clientes");
			System.out.println("8.Mostar un cliente segun su Dni");
			System.out.println("9.Realizar una venta");
			System.out.println("10.Mostrar ventas");
			System.out.println("11.Mostrar las ventas de un dia concreto y total de venta del dia");
			System.out.println("12.Salir");
		
			opcion=teclado.nextInt();
			
			switch (opcion) {
			case 1:
				System.out.println("Nuevo vehiculo");
				teclado.nextLine();
				System.out.println("Matricula:");
				ma=teclado.nextLine();
				System.out.println("Marca:");
				mar=teclado.nextLine();
				System.out.println("Modelo:");
				mo=teclado.nextLine();
				System.out.println("Precio");
				p=teclado.nextDouble();
				Vehiculo v=new Vehiculo(ma,mar,mo,p);
				vc=new VehiculoController();
				if(vc.insertarVehiculo(v)) {
					System.out.println("Vehiculo guardado");
				}else {
					System.out.println("Fallo al insertar");
				}
				
				break;
			case 2:
				System.out.println("Modificacion del vehiculo");
				Vehiculo ve=new Vehiculo();
				teclado.nextLine();
				System.out.println("Matricula:");
				String matricula=teclado.nextLine();
				System.out.println("Nueva Matricula:");
				ma=teclado.nextLine();
				ve.setMatricula(ma);
				System.out.println("Nueva Marca:");
				mar=teclado.nextLine();
				ve.setMarca(mar);
				System.out.println("Nuevo Modelo:");
				mo=teclado.nextLine();
				ve.setModelo(mo);
				System.out.println("Nuevo Precio");
				p=teclado.nextDouble();
				ve.setPrecioVenta(p);
				vc=new VehiculoController();
				if(vc.modificarVehiculos(ve, matricula)==true) {
					System.out.println("Los cambios se han guardado");
				}else {
					System.out.println("Fallo al modificar");
				}
				break;
			case 3:
				System.out.println("Introduzca matricula del vehiculo:");
				teclado.nextLine();
				String maBuscar=teclado.nextLine();
				VehiculoController control=new VehiculoController();
				
				Vehiculo vehiculo=new Vehiculo();
				vehiculo=control.informacionDelVehiculo(maBuscar);
				
				if(vehiculo==null) {
					System.out.println("Este vehiculo no esta en la base de datos");
				}else {
					System.out.println(vehiculo.toString());
				}
				
				
				break;
			case 4:
				System.out.println("Listado de vehiculos de una marca:");
				System.out.println("¿¿Que marca consultara??");
				teclado.nextLine();
				String marcaB=teclado.nextLine();
				ArrayList <Vehiculo> vehiculosTaller=new ArrayList<Vehiculo>();
				vc=new VehiculoController();
				vehiculosTaller=vc.mostrarVehiculos(marcaB);
				
				if(vehiculosTaller.size()==0) {
					System.out.println("Esta marca no esta en la base de datos");
				}else {
					for(Vehiculo veh: vehiculosTaller) {
						System.out.println(veh.toString());
					}
				}
				break;
				
			case 5:
				System.out.println("Nuevo Cliente");
				teclado.nextLine();
				System.out.println("Introduzca DNI:");
				dni=teclado.nextLine();
				System.out.println("Introduzca nombre:");
				nombre=teclado.nextLine();
				System.out.println("Introduzca apellidos:");
				apellidos=teclado.nextLine();
				Cliente cli=new Cliente(dni,nombre,apellidos);
				cc=new ClienteController();
				if(cc.insertarCliente(cli)==true) {
					System.out.println("Los datos del cliente se guardaron");
				}else {
					System.out.println("El cliente no se guardo, Error");
				}
				
				break;
			case 6:
				System.out.println("Modificacion del cliente");
				teclado.nextLine();
				System.out.println("Introduzca el dni que busca:");
				String dniB=teclado.nextLine();
				System.out.println("Introduzca el nuevo dni:");
				dni=teclado.nextLine();
				System.out.println("Introduzca el nuevo nombre:");
				nombre=teclado.nextLine();
				System.out.println("Introduzca los nuevos apellidos:");
				apellidos=teclado.nextLine();
				Cliente cl=new Cliente();
				cl.setDni(dni);
				cl.setNombre(nombre);
				cl.setApellidos(apellidos);
				cc=new ClienteController();
				if(cc.modificarCliente(cl, dniB)==true) {
					System.out.println("Los datos del cliente se modificaron");
				}else {
					System.out.println("Error, algo fallo");
				}
				break;
			case 7:
				System.out.println("Listar clientes");
				cc=new ClienteController();
				ArrayList<Cliente>clientes=new ArrayList<Cliente>();
				clientes=cc.mostrarClientes();
				for(Cliente clie:clientes) {
					System.out.println(clie.toString());
				}
				break;
			case 8:
				System.out.println("Introduzca dni del cliente que solicita informacion:");
				teclado.nextLine();
				String dniBuscar=teclado.nextLine();
				cc=new ClienteController();
			
				Cliente c=cc.buscarClientePorDni(dniBuscar);
				if(c==null) {
					System.out.println("Este cliente no esta en la base de datos");
				}else {
					System.out.println(c.toString());
				}
				break;
			case 9:
				System.out.println("Verificando la matricula, introduce para comprobar:");	
				teclado.nextLine();
				ma=teclado.nextLine();
				int disponibilidad=comprobarDisponibilidadVehiculoEnTaller( ma);
				vcon=new VentaController();
				int num=vcon.verificarMatriculaVehiculos(ma);
				System.out.println(disponibilidad+"---------------------------"+num);
				if(disponibilidad==1) {
					int verificacion=comprobarMatricula(ma);
					
					if(verificacion==0) {
						System.out.println("Nueva venta");
						System.out.println("Introduzca id:");
						int id=teclado.nextInt();
						System.out.println("Introduzca matricula:");
						teclado.nextLine();
						ma=teclado.nextLine();
						System.out.println("Introduzca dni del cliente:");
						dni=teclado.nextLine();
						System.out.println("Introduzca el descuento a realizar:");
						double des=teclado.nextDouble();
						teclado.nextLine();
						System.out.println("Introduzca motivo del descuento:");
						String motivo=teclado.nextLine();
						System.out.println("Introduzca fecha de venta: (formato año/mes/dia)");
						String fecha=teclado.nextLine();
						String fechas[]=fecha.split("/");
						
						LocalDate fechaVenta= LocalDate.of(Integer.valueOf(fechas[0]), Integer.valueOf(fechas[1]), Integer.valueOf(fechas[2]));
						
						Venta venta=new Venta(id,ma,dni,des,motivo,fechaVenta);
						vcon=new VentaController();
						
						if(vcon.insertarVenta(venta)==true) {
							System.out.println("Venta realizada");
						}else {
							System.out.println("La venta no se realizo");
						}
					}else if(verificacion==1) {
						System.out.println("Ya hay una venta con esa matricula, no se puede hacer");
					}
				}else if(disponibilidad==0) {
					System.out.println("El vehiculo no se encuentra aqui disponible");
				}
				break;
			case 10:
				System.out.println("Listar ventas");
				vcon=new VentaController();
				ArrayList<Venta>ventas=new ArrayList<Venta>();
				ventas=vcon.listarVentas();
				for(Venta ven:ventas) {
					System.out.println(ven.toString());
				}
				break;
			case 11:
				System.out.println("Listar ventas de un dia concreto");
				System.out.println("Introduzca fecha de consulta (Formato: yyyy/mm/dd):");
				teclado.nextLine();
				String fechaConsultar=teclado.nextLine();
				String fechasConsulta[]=fechaConsultar.split("/");
				
				LocalDate fechaV= LocalDate.of(Integer.valueOf(fechasConsulta[0]), Integer.valueOf(fechasConsulta[1]), Integer.valueOf(fechasConsulta[2]));
				vcon=new VentaController();
				ArrayList<Venta>ventasDia=new ArrayList<Venta>();
				ventasDia=vcon.listadoVentas(fechaV);
				System.out.println("\n Dia "+fechaConsultar);
				for(Venta ven:ventasDia) {
					System.out.println(ven.toString());
				}
				
				vcon=new VentaController();
				double suma=vcon.sumaPreciosVenta(fechaV);
				
				System.out.println("-----TOTAL-------"+suma+"EUROS/DIA \n");

				break;
		
			case 12:
		
				System.out.println("Ha salido del taller, buen dia!!");
				vcon.cerrarConexion();
				cc.cerrarConexion();
				vc.cerrarConexion();
				break;
				
			}
		}while(opcion!=13);

	}
	
	public static int comprobarDisponibilidadVehiculoEnTaller(String matricula) {
		
		VentaController g=new VentaController();
		int existe=g.verificarMatriculaVehiculos(matricula);
		if(existe==0) {
			System.out.println("El vehiculo no se encuentra en este taller");
		}
		return existe;
		
	}
	public static int comprobarMatricula(String matricula) {
		
		VentaController g=new VentaController();
		int existe=g.verificarMatriculaListaVentas(matricula);
		System.out.println("Vehiculos con la misma matricula en listado de ventas: "+existe);
		return existe;
		
	}

}
