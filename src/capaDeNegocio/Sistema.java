package capaDeNegocio;

import java.util.ArrayList;

import capaDeDatos.Mesa;
import capaDeDatos.Mozo;
import capaDeDatos.Operario;
import capaDeDatos.Producto;

public class Sistema {
	
	private static Sistema instance = null;
	public static final int prefijoOperario = 10000;
	public static final int prefijoMozo = 20000;
	public static final int prefijoMesa = 30000;
	public static final int prefijoProducto = 40000;
	

	
	public static Sistema getInstance() {
		if (Sistema.instance == null) {
			Sistema.instance = new Sistema();
		}
		return instance;
	}

	public ArrayList<Mozo> getMozos() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Operario> getOperarios() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Mesa> getMesas() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Producto> getProductos() {
		// TODO Auto-generated method stub
		return null;
	}

	public float getSueldo() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
