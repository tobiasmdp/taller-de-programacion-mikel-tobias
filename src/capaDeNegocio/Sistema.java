package capaDeNegocio;

import java.util.ArrayList;

import capaDeDatos.Mesa;
import capaDeDatos.Mozo;
import capaDeDatos.Operario;
import capaDeDatos.Producto;

public class Sistema {
	
	private static Sistema instance = null;
	final private int prefijoOperario = 10000;
	final private int prefijoMozo = 20000;
	final private int prefijoMesa = 30000;
	final private int prefijoProducto = 40000;
	

	
	public static Sistema getInstance() {
		if (Sistema.instance == null) {
			Sistema.instance = new Sistema();
		}
		return instance;
	}

	public int getPrefijoOperario() {
		return prefijoOperario;
	}

	public int getPrefijoMozo() {
		return prefijoMozo;
	}

	public int getPrefijoMesa() {
		return prefijoMesa;
	}

	public int getPrefijoProducto() {
		return prefijoProducto;
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
	
}
