package Model;

import java.time.LocalDate;

public class Venta {
	private int id;
	private String matricula;
	private String dniCliente;
	private double descuento;
	private String motivoDes;
	private LocalDate fecha;
	
	public Venta() {
		this.id=id;
		this.matricula=matricula;
		this.dniCliente=dniCliente;
		this.descuento=descuento;
		this.motivoDes=motivoDes;
		this.fecha=fecha;
	}
	
	public Venta(int i,String m,String dC,double des, String mDes, LocalDate f) {
		this.id=i;
		this.matricula=m;
		this.dniCliente=dC;
		this.descuento=des;
		this.motivoDes=mDes;
		this.fecha=f;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public String getMotivoDes() {
		return motivoDes;
	}

	public void setMotivoDes(String motivoDes) {
		this.motivoDes = motivoDes;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Venta [id=" + id + ", matricula=" + matricula + ", dniCliente=" + dniCliente + ", descuento="
				+ descuento + ", motivoDes=" + motivoDes + ", fecha=" + fecha + "]";
	}

	
}
