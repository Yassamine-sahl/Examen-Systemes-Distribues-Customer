package ma.enset.inventoryservice.InventoryQuery.repositories;

import ma.enset.inventoryservice.InventoryQuery.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categorie,String> {
}
