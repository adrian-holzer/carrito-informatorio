package com.informatorio.carrito.config;

import com.informatorio.carrito.domain.*;
import com.informatorio.carrito.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class CarritoRunner implements CommandLineRunner {

      @Autowired
      private UsuarioRepository usuarioRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private OrdenRepository ordenRepository;


    @Autowired
    private OrdenProductoRepository ordenProductoRepository;


    @Autowired
    private DetalleCarritoRepository detalleCarritoRepository;



    @Override
    public void run(String... args) throws Exception {

//
//
//
//        Orden o = new Orden();
//
//        o.setCodigoOrden("0001");
//        o.setTipo(Tipo.A);
//        o.setEstadoOrden(EstadoOrden.CONFIRMADO);
//        o.setCarrito(this.carritoRepository.findById(Long.valueOf(1)).get());
//
//
//
//        o = this.ordenRepository.save(o);
//
//
//        for (DetalleCarrito dc : o.getCarrito().getListaDetallesCarrito()
//             ) {
//
//            OrdenProducto op = new OrdenProducto();
//
//            op.setOrden(o);
//            op.setProducto(dc.getProducto());
//            op.setCantidad(dc.getCantidad());
//            op.setPrecio(dc.getProducto().getPrecioUnitario());
//
//            this.ordenProductoRepository.save(op);
//
//
//        }







    }
}
