package model;

import java.util.ArrayList;

public class DataBase {
	
	private ArrayList<Producto>products;
	
	public DataBase() {
		products = new ArrayList<Producto>();
	}

	public ArrayList<Producto> getListaCompra() {
		return products;
	}

	public void addProducto(Producto prod) {
		products.add(prod);
	}

	
	public Object[]getProductos(){
		
		return products.toArray();
	}

	public void deleteProducto(int index) {
		products.remove(index);
		
		
	}
	
	
	

}
