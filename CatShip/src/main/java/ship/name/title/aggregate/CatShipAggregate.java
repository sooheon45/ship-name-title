package ship.name.title.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.*;

import java.util.List;
import lombok.Data;
import lombok.ToString;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;
import ship.name.title.command.*;
import ship.name.title.event.*;
import ship.name.title.query.*;

@Aggregate
@Data
@ToString
public class CatShipAggregate {

    @AggregateIdentifier
    private Long id;

    public CatShipAggregate() {}

    @CommandHandler
    public CatShipAggregate(CatReceivceCommand command) {}
    //>>> EDA / Event Sourcing

}
