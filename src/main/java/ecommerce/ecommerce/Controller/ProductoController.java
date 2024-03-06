package ecommerce.ecommerce.Controller;

import ecommerce.ecommerce.Services.ProductoServices;
import ecommerce.ecommerce.model.Producto;
import ecommerce.ecommerce.model.Usuario;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/Producto")
public class ProductoController {

    private final Logger Loger= LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoServices productoServices;

    @GetMapping("")
    public String show(Model model) {
       model.addAttribute("productos", productoServices.mostrar());
        return "Producto/show";
    }


    @GetMapping("/Create")
    public String Create() {
        return "Producto/create";
    }

    @PostMapping("/Guardar")
    public String Guardar(Producto producto){
        Loger.info("el producto es {}", producto);
        Usuario u= new Usuario(1,"","","","","","","" );
        producto.setUsuario(u);
        productoServices.guardar(producto);
        return "redirect:/Producto";

    }




}
