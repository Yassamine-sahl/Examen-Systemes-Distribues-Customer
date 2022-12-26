package ma.enset.inventoryservice.InventoryQuery.repositories;

import ma.enset.inventoryservice.InventoryQuery.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
}
