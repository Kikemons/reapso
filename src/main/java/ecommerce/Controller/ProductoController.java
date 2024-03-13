package ecommerce.Controller;

import ecommerce.Services.ProductoServices;
import ecommerce.Services.UploadFileServices;
import ecommerce.model.Producto;
import ecommerce.model.Usuario;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


@Controller
@RequestMapping("/Producto")
public class ProductoController {

    private final Logger Loger= LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoServices productoServices;

    @Autowired
    private UploadFileServices uploadFileServices;

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
    public String Guardar(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {
        Loger.info("el producto es {}", producto);
        Usuario u= new Usuario(1,"","","","","","","");
        producto.setUsuario(u);
        //imagen

            // imagen
            if (producto.getId()==null){//validacion cuando se crea un producto
                String nombreImagen= uploadFileServices.saveImage(file);
                producto.setImagen(nombreImagen);
            }
            productoServices.guardar(producto);
            return "redirect:/Producto";
    }

    @GetMapping("/Editar/{id}")
    public String Editar(@PathVariable Integer id, Model model){
        Producto producto= new Producto();
        Optional<Producto>optionalProducto=productoServices.obtener(id);
        producto=optionalProducto.get();
        model.addAttribute("producto", producto);

        Loger.info("producto buscado {}",producto);
        return  "Producto/edit";
    }

    @PostMapping("/Actualizar")
    public String Actualizar(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {
        if (file.isEmpty()){
            Producto p= new Producto();
            p= productoServices.obtener(producto.getId()).get();
            producto.setImagen(p.getImagen());
        }
        else {
            Producto p= new Producto();
            p=productoServices.obtener(producto.getId()).get();
            if (!p.getImagen().equals("default.jpg")){
                uploadFileServices.deleteImage(p.getImagen());
            }
            String nombreImagen=uploadFileServices.saveImage(file);
            producto.setImagen(nombreImagen);
        }
        productoServices.actualizar(producto);

        return "redirect:/Producto";
    }

    @GetMapping("/Eliminar/{id}")
    public String Eliminar(@PathVariable Integer id){
        Producto p= new Producto();
        p=productoServices.obtener(id).get();
        if (!p.getImagen().equals("default.jpg")){
        uploadFileServices.deleteImage(p.getImagen());
        }
        productoServices.borrar(id);
        return "redirect:/Producto";
    }


}
