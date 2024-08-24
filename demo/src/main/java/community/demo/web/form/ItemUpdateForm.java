package community.demo.web.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class ItemUpdateForm {

    private Long id;

    private String itemName;

    private Integer price;

    private Integer quantity;

    public ItemUpdateForm(Long id, String itemName, Integer price, Integer quantity) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
