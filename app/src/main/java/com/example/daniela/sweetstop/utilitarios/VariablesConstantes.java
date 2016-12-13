package com.example.daniela.sweetstop.utilitarios;

/**
 * Created by daniela on 9/10/2016.
 */
public class VariablesConstantes {

    // IP
    public static final String IP = "192.168.1.38";
    public static final String PORT = "80";

    // HTTPS REQUEST
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;

    // WEB SERVICES REST
    public static final String _URL_AUTENTIFICACION = "http://" + IP + ":" + PORT +"/Daniela/ServiciosWeb/autentificacion.php";
    public static final String _URL_REGISTRO = "http://" + IP + ":" + PORT +"/Daniela/ServiciosWeb/registro.php";
    public static final String _URL_CATEGORIA = "http://" + IP + ":" + PORT +"/Daniela/ServiciosWeb/categorias.php";
    public static final String _URL_CATALOGO = "http://" + IP + ":" + PORT +"/Daniela/ServiciosWeb/productos.php";
    public static final String _URL_PRODUCTO = "http://" + IP + ":" + PORT +"/Daniela/ServiciosWeb/productosall.php";
    public static final String _URL_PEDIDO = "http://" + IP + ":" + PORT +"/Daniela/ServiciosWeb/pedido.php";
    public static final String _URL_ESTADO_MESA = "http://" + IP + ":" + PORT +"/Daniela/ServiciosWeb/estadoMesa.php";
    public static final String _URL_RESERVA = "http://" + IP + ":" + PORT +"/Daniela/ServiciosWeb/reserva.php";
    public static final String _URL_ESTADO_PEDIDO = "http://" + IP + ":" + PORT +"/Daniela/ServiciosWeb/estadosPedidos.php";
    public static final String _URL_PROMOCION = "http://" + IP + ":" + PORT +"/Daniela/ServiciosWeb/promocion.php";
    public static final String _URL_PROMOCION_DETALLE = "http://" + IP + ":" + PORT +"/Daniela/ServiciosWeb/detallepromocion.php";
    public static final String _URL_RESERVAR_PROMOCION = "http://" + IP + ":" + PORT +"/Daniela/ServiciosWeb/recibirPromocion.php";
    public static final String _URL_ACTUALIZAR_ESTADO = "http://" + IP + ":" + PORT +"/Daniela/ServiciosWeb/enviarEstado.php";
    public static final String _URL_PUBLICIDAD = "http://" + IP + ":" + PORT +"/Daniela/ServiciosWeb/publicidad.php";
}
