package ma.enset.commonapi.events;

import lombok.Getter;

public class CustomerUpdatedEvent extends BaseEvent<String> {
  @Getter private String nom ;
  @Getter  private String adresse ;
  @Getter  private  String email  ;
  @Getter private String telephone ;

    public CustomerUpdatedEvent(String id, String nom, String adresse, String email, String telephone) {
        super(id);
        this.nom = nom;
        this.adresse = adresse;
        this.email = email;
        this.telephone = telephone;
    }
}
