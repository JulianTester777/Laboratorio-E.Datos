package ECommerce;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Catalogo {

    private HashMap<String, Producto> productos;

    public Catalogo() {
        productos = new HashMap<>();

    }

    //Insertar un producto
    public void insertar(Producto producto) {
        productos.put(producto.getCodigo(), producto);
    }

    //Buscar por codigo
    public Producto buscar(String codigo) {
        return productos.get(codigo);
    }

    //Mostrar ordenados por precio
    public List<Producto> OrdenarPorPrecio(){

        List<Producto> lista =
                new ArrayList<>(productos.values());
        lista.sort(
                Comparator.comparingDouble(Producto :: getPrecio));

        return lista;
    }

    //Cantidad productos metodo adicional
    public int cantidad(){
        return productos.size();
    }



}
