package ma.enset.inventoryservice.inventoryCommands.aggregates;

import ma.enset.commonapi.commands.CreateProductCommand;
import ma.enset.commonapi.commands.UpdateProductCommand;
import ma.enset.commonapi.enums.ProductEtat;
import ma.enset.commonapi.events.ProductCreatedEvent;
import ma.enset.commonapi.events.ProductUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Date;

@Aggregate
public class PoductAggregate {
    @AggregateIdentifier
    private String id;
    private String nom ;
    private double prix ;
    private  int qte  ;
    private ProductEtat etat ;

    public PoductAggregate() {
    }

    @CommandHandler
    public PoductAggregate(CreateProductCommand command) {
        AggregateLifecycle.apply(
                new ProductCreatedEvent(
                        command.getId(),
                        command.getNom(),
                        command.getPrix(),
                        command.getQte(),
                        command.getEtat(),
                        command.getCategorie()
                )
        );
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent event) {
        this.id = event.getId();
        this.nom = event.getNom();
        this.prix = event.getPrix();
        this.qte = event.getQte();
        this.etat = event.getEtat();
    }

    @CommandHandler
    public void handle(UpdateProductCommand command) {
        AggregateLifecycle.apply(
                new ProductUpdatedEvent(
                        command.getId(),
                        command.getNom(),
                        command.getPrix(),
                        command.getQte(),
                        command.getEtat(),
                        command.getCategorie()
                )
        );
    }

    @EventSourcingHandler
    public void on(ProductUpdatedEvent event) {
        this.id = event.getId();
        this.nom = event.getNom();
        this.prix = event.getPrix();
        this.qte = event.getQte();
        this.etat = event.getEtat();
    }

}
