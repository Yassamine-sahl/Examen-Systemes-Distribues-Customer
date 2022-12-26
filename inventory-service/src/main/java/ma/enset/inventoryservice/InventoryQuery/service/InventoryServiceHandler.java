package ma.enset.inventoryservice.InventoryQuery.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.commonapi.events.CategorieCreatedEvent;
import ma.enset.commonapi.events.CategorieUpdatedEvent;
import ma.enset.commonapi.events.ProductCreatedEvent;
import ma.enset.commonapi.events.ProductUpdatedEvent;
import ma.enset.inventoryservice.InventoryQuery.entities.Categorie;
import ma.enset.inventoryservice.InventoryQuery.entities.Product;
import ma.enset.inventoryservice.InventoryQuery.repositories.CategoryRepository;
import ma.enset.inventoryservice.InventoryQuery.repositories.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class InventoryServiceHandler {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @EventHandler
    public void on(CategorieCreatedEvent event) {
        log.info("*********************************");
        log.info("CategorieCreatedEvent received");
        Categorie categorie = new Categorie() ;
        categorie.setId(event.getId());
        categorie.setDescription(event.getDescription());
        categorie.setNom(event.getNom());
        categoryRepository.save(categorie) ;
    }

    @EventHandler
    public void on(CategorieUpdatedEvent event) {
        log.info("*********************************");
        log.info("CategorieUpdatedEvent received");
        Categorie categorie= categoryRepository.findById(event.getId()).get();
        categorie.setDescription(event.getDescription());
        categorie.setNom(event.getNom());
        categoryRepository.save(categorie) ;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        log.info("*********************************");
        log.info("ProductCreatedEvent received");
        Product product = new Product() ;
        product.setId(event.getId());
        product.setNom(event.getNom());
        product.setPrix(event.getPrix());
        product.setQte(event.getQte());
        product.setEtat(event.getEtat());

       Categorie categorie = categoryRepository.findById(event.getCategorie()).get();
        if (categorie != null) {
            product.setCategorie(categorie);
        }
        productRepository.save(product) ;
    }

    @EventHandler
    public void on(ProductUpdatedEvent event) {
        log.info("*********************************");
        log.info("ProductUpdateddEvent received");
        Product product = productRepository.findById(event.getId()).get();
        product.setNom(event.getNom());
        product.setPrix(event.getPrix());
        product.setQte(event.getQte());
        product.setEtat(event.getEtat());

        Categorie categorie = categoryRepository.findById(event.getCategorie()).get();

        product.setCategorie(categorie);

        productRepository.save(product) ;
    }

}
