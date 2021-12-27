package viewObject;

import com.reportservice.reportservice.repo.model.Report;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseTemplate {
    private Report report;
    private User user;
    private Tournament tournament;
}
