package objects;

import utils.JacksonUtils;
import java.io.IOException;
import java.util.Objects;

public class Products {
    private int id;
    private String name;

    public Products() {}

    public Products(int id) throws IOException {
        Products[] products = JacksonUtils.deserializeJson("products.json", Products[].class);
        for (Products product : products) {
            if (Objects.equals(product.getId(), id)) {
                this.id = id;
                this.name = product.getName();
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
