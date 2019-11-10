package Model;

public class Vehiculo {
	private String marca;
	private String matricula;
	private String modelo;
	private double precioVenta;
	
	public Vehiculo() {
		this.matricula=matricula;
		this.marca=marca;
		this.modelo=modelo;
		this.precioVenta=precioVenta;
		
	}
	
	public Vehiculo(String m,String ma,String mo,double p) {
		this.matricula=m;
		this.marca=ma;
		this.modelo=mo;
		this.precioVenta=p;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	@Override
	public String toString() {
		return "Vehiculo [marca=" + marca + ", matricula=" + matricula + ", modelo=" + modelo + ", precioVenta="
				+ precioVenta + "]";
	}
	
	

}
