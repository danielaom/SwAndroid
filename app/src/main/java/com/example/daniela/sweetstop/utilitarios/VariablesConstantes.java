package com.example.daniela.sweetstop.utilitarios;

/**
 * Created by daniela on 9/10/2016.
 */
public class VariablesConstantes {

    // IP
    public static final String IP = "192.168.1.35";
    public static final String PORT = "8888";

    // HTTPS REQUEST
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;

    // WEB SERVICES REST
    public static final String _URL_AUTENTIFICACION = "http://" + IP + ":" + PORT +"/wsadmin/servicios/autentificacion.php";
    public static final String _URL_REGISTRO = "http://" + IP + ":" + PORT +"/wsadmin/servicios/registro.php";
    public static final String _URL_CATEGORIA = "http://" + IP + ":" + PORT +"/wsadmin/servicios/categorias.php";
    public static final String _URL_CATALOGO = "http://" + IP + ":" + PORT +"/wsadmin/servicios/productos.php";
    public static final String _URL_PRODUCTO = "http://" + IP + ":" + PORT +"/wsadmin/servicios/productosall.php";
    public static final String _URL_PEDIDO = "http://" + IP + ":" + PORT +"/wsadmin/servicios/pedido.php";
    public static final String _URL_ESTADO_MESA = "http://" + IP + ":" + PORT +"/wsadmin/servicios/estadoMesa.php";
}
