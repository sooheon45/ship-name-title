package ship.name.title.command;

import lombok.Data;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@ToString
@Data
public class CatReceivceCommand {

    private Long id; // Please comment here if you want user to enter the id directly
}
