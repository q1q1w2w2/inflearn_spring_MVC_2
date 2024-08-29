package community.demo.web.form;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Range;

@Data
public class ItemUpdateForm {

    @NotNull
    private Long id;

    @NotBlank
    private String itemName;

    @NotNull
    @Range(min = 1000, max = 1000000)
    private Integer price;

//    @NotNull
//    @Max(9999)
    private Integer quantity;

    public ItemUpdateForm() {
    }

    public ItemUpdateForm(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

//    public ItemUpdateForm(Long id, String itemName, Integer price, Integer quantity) {
//        this.id = id;
//        this.itemName = itemName;
//        this.price = price;
//        this.quantity = quantity;
//    }
}
