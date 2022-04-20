package model;

public class Producto {
	
	public static final String NAME_PROD = "Nombre del Producto: ";
	public static final String CANT_PROD = "Cantidad: ";
	public static final String UNID_PROD = "Unidad: ";
	public static final String[]MEDIDA_UNID = {"-","Kg","L","Ud"};
	
	
	private String nombre;
	private int cantidad;
	private String unidad;
	
	
	public Producto(String nombre, int cantidad, String unidad) {
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.unidad = unidad;
	}

	
	

	public String getNombre() {
		return nombre;
	}




	@Override
	public String toString() {
		return  "\n"+nombre + " - " + cantidad + " " + unidad;
	}
	
	
	
	
	
	

}
