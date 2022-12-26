package ma.enset.commonapi.commands;

import lombok.Getter;
import ma.enset.commonapi.enums.ProductEtat;

public class CreateProductCommand extends  BaseCommand<String>{

   @Getter private String nom ;
   @Getter private double prix ;
   @Getter private  int qte  ;
   @Getter private ProductEtat etat ;
   @Getter private String categorie ;

    public CreateProductCommand(String id, String nom, double prix, int qte, ProductEtat etat,String categorie) {
        super(id);
        this.nom = nom;
        this.prix = prix;
        this.qte = qte;
        this.etat = etat;
        this.categorie =categorie ;
    }
}
