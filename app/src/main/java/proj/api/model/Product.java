package proj.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@NoArgsConstructor
public class Product {
    private String id;
    @Indexed
    private String name;
    private String manufacture;
    private String type;

    public Product(String name, String manufacture, String type) {
        this.name = name;
        this.manufacture = manufacture;
        this.type = type;
    }
}
