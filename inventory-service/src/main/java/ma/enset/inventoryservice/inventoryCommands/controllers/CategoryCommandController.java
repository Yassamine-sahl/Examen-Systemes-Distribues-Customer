package ma.enset.inventoryservice.inventoryCommands.controllers;

import lombok.AllArgsConstructor;
import ma.enset.commonapi.commands.CreateCategorieCommand;
import ma.enset.commonapi.commands.UpdateCategorieCommand;
import ma.enset.commonapi.dtos.CreateCategorieRequestDTO;
import ma.enset.commonapi.dtos.UpdateCategorieRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/category/commands")
@AllArgsConstructor
@CrossOrigin("*")
public class CategoryCommandController {
    private final CommandGateway commandGateway;
    private final EventStore eventStore;

    @PostMapping("/create")
    public CompletableFuture<String> createCategory(@RequestBody CreateCategorieRequestDTO request) {
        return commandGateway.send(new CreateCategorieCommand(
                UUID.randomUUID().toString(),
                request.getNom(),
                request.getDescription()

        ));
    }

    @PutMapping("/update")
    public CompletableFuture<String> updateCategory(@RequestBody UpdateCategorieRequestDTO request) {
        return commandGateway.send(new UpdateCategorieCommand(
                request.getId(),
                request.getNom(),
                request.getDescription()
        ));
    }

    @GetMapping("/eventStore/{id}")
    public Stream getEventStore(@PathVariable String id) {
        return eventStore.readEvents(id).asStream();
    }





}
