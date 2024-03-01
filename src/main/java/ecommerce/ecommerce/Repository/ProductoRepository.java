package ecommerce.ecommerce.Repository;

import ecommerce.ecommerce.model.Producto;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository <Producto, Integer> {


}
