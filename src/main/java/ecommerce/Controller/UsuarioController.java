package ecommerce.Controller;

import ecommerce.Services.ProductoServices;
import ecommerce.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class UsuarioController {

    @Autowired
    private ProductoServices productoServices;

    @GetMapping("")
    public String Home(Model model){
        List<Producto> productos=productoServices.mostrar();
        model.addAttribute("productos", productos);
        return "Usuario/home";
    }

    @GetMapping("/ProductoHome/{id}")
    public String productoHome(@PathVariable Integer id, Model model){
        Producto producto=productoServices.obtener(id).get();
        model.addAttribute("producto", producto);
        return "Usuario/productohome";
    }
}
