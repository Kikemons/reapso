package ecommerce.ecommerce.Controller;

import ecommerce.ecommerce.model.Producto;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/Producto")
public class ProductoController {



    @GetMapping("")
    public String show() {
        return "Producto/show";
    }


    @GetMapping("/Create")
    public String Create() {
        return "Producto/create";
    }

    @PostMapping("/Guardar")
    public String Guardar(Producto producto){

        return "redirect:/Producto";

    }




}
