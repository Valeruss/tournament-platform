package viewObject;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Tournament {
    private String name;
    private String description;
    private Integer size;
}
