package ship.name.title.api;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ship.name.title.aggregate.*;
import ship.name.title.command.*;

@RestController
public class CatShipController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public CatShipController(
        CommandGateway commandGateway,
        QueryGateway queryGateway
    ) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @RequestMapping(value = "/catShips", method = RequestMethod.POST)
    public CompletableFuture catReceivce(
        @RequestBody CatReceivceCommand catReceivceCommand
    ) throws Exception {
        System.out.println("##### /catShip/catReceivce  called #####");

        // send command
        return commandGateway
            .send(catReceivceCommand)
            .thenApply(id -> {
                CatShipAggregate resource = new CatShipAggregate();
                BeanUtils.copyProperties(catReceivceCommand, resource);

                resource.setId((Long) id);

                EntityModel<CatShipAggregate> model = EntityModel.of(resource);
                model.add(
                    Link.of("/catShips/" + resource.getId()).withSelfRel()
                );

                return new ResponseEntity<>(model, HttpStatus.OK);
            });
    }

    @Autowired
    EventStore eventStore;

    @GetMapping(value = "/catShips/{id}/events")
    public ResponseEntity getEvents(@PathVariable("id") String id) {
        ArrayList resources = new ArrayList<CatShipAggregate>();
        eventStore.readEvents(id).asStream().forEach(resources::add);

        CollectionModel<CatShipAggregate> model = CollectionModel.of(resources);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
