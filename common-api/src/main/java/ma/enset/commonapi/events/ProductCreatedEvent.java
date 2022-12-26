package ma.enset.commonapi.events;

import lombok.Getter;
import ma.enset.commonapi.enums.ProductEtat;

public class ProductCreatedEvent extends BaseEvent<String> {

    @Getter private String nom ;
    @Getter private double prix ;
    @Getter private  int qte  ;
    @Getter private ProductEtat etat ;
    @Getter private String categorie ;

    public ProductCreatedEvent(String id, String nom, double prix, int qte, ProductEtat etat,String categorie) {
        super(id);
        this.nom = nom;
        this.prix = prix;
        this.qte = qte;
        this.etat = etat;
        this.categorie =categorie ;
    }
}