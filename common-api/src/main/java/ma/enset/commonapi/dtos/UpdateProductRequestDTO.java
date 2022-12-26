package ma.enset.commonapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.commonapi.enums.ProductEtat;

@Data @NoArgsConstructor @AllArgsConstructor
public class UpdateProductRequestDTO {
    private String id;
    private String nom ;
    private double prix ;
    private  int qte  ;
    private ProductEtat etat ;
    private  String categorie ;
}
