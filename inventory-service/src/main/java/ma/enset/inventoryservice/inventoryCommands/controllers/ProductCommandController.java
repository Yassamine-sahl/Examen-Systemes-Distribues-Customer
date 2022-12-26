package ma.enset.inventoryservice.inventoryCommands.controllers;

import lombok.AllArgsConstructor;
import ma.enset.commonapi.commands.CreateProductCommand;
import ma.enset.commonapi.commands.UpdateProductCommand;
import ma.enset.commonapi.dtos.CreateProductRequestDTO;
import ma.enset.commonapi.dtos.UpdateProductRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/proprietaire/commands")
@AllArgsConstructor
@CrossOrigin("*")
public class ProductCommandController {
    private CommandGateway commandGateway;
    private EventStore eventStore;

    @PostMapping("/create")
    public CompletableFuture<String> createProduct(@RequestBody CreateProductRequestDTO request) {
        return commandGateway.send(new CreateProductCommand(
                UUID.randomUUID().toString(),
                request.getNom(),
                request.getPrix(),
                request.getQte(),
                request.getEtat(),
                request.getCategorie()
        ));
    }

    @PutMapping("/update")
    public CompletableFuture<String> updateProduct(@RequestBody UpdateProductRequestDTO request) {
        return commandGateway.send(new UpdateProductCommand(
                request.getId(),
                request.getNom(),
                request.getPrix(),
                request.getQte(),
                request.getEtat(),
                request.getCategorie()
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        ResponseEntity<String> entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }

    @GetMapping("/eventStore/{id}")
    public Stream getEventStore(@PathVariable String id) {
        return eventStore.readEvents(id).asStream();
    }

}
