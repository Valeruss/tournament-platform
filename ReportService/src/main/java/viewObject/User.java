package viewObject;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
//    private long id;
    private String name;
    private String password;
    private Integer tournament_played;
}
