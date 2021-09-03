package com.informatorio.carrito.service;

import com.informatorio.carrito.domain.Carrito;
import com.informatorio.carrito.domain.DetalleCarrito;
import com.informatorio.carrito.domain.Producto;
import com.informatorio.carrito.repository.DetalleCarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetalleCarritoService {

    @Autowired
    private DetalleCarritoRepository detalleCarritoRepository;

    public DetalleCarritoService() {
    }



    public  boolean getDetalleCarrito(Producto p , Carrito c){


        return  this.detalleCarritoRepository.findFirstByProductoAndCarrito(p,c).isPresent();


    }


    public DetalleCarrito modificarCarrito(DetalleCarrito dc){


        // Compruebo si el el detalle con el carrito y el producto ya existe

        DetalleCarrito dcExist = this.detalleCarritoRepository.findFirstByProductoAndCarrito(dc.getProducto(),dc.getCarrito()).orElse(null);



        // Si el detalle ya existe :
        //    - Si la cantidad es=0 elimino un producto
        //    - Si la cantidad es > 0 agrego la cantidad a la ya existente
        //
        // Si el detalle no existe y cantidad es > 0 agrego el nuevo detalle con la cantidad correspondiente

        if (dcExist!=null){

            if (dc.getCantidad()>0){


                // Si la cantidad es mayor que cero significa que agrego un producto

                dcExist.setCantidad(dc.getCantidad()+ dcExist.getCantidad());
                return this.detalleCarritoRepository.save(dcExist);

            }else{

                // Si la cantidad del detalle existente es igual a 1 elimino por completo el detalle (Es el ultimo producto
                // con ese codigo que queda en el carrito)

                if (dcExist.getCantidad()==1){
                    this.detalleCarritoRepository.delete(dcExist);
                    return null;

                }else{

                    // Si la cantidad del producto existente es > 1 elimino 1
                    dcExist.setCantidad(dcExist.getCantidad()-1);
                    return this.detalleCarritoRepository.save(dcExist);
                }
            }

        }else{
            // Si el detalle no existe , guardo dc como el nuevo detalle
                return  this.detalleCarritoRepository.save(dc);
        }
    }



    public void delete(Long id){

        this.detalleCarritoRepository.deleteById(id);
    }







}
