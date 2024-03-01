package ecommerce.ecommerce.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Producto")
public class ProductoController {

    @GetMapping("")
    public String show (){
        return "Producto/show";
    }
}
