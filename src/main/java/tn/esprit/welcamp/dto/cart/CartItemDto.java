package tn.esprit.welcamp.dto.cart;



import tn.esprit.welcamp.entities.CommandLine;
import tn.esprit.welcamp.entities.ToolOffer;


public class CartItemDto {
    private long id;
    private Integer quantity;
    private ToolOffer product;

    public CartItemDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ToolOffer getProduct() {
        return product;
    }

    public void setProduct(ToolOffer product) {
        this.product = product;
    }
    public CartItemDto(CommandLine cart) {
        this.id = cart.getIdCart();
        this.quantity = cart.getToolsQuantity();
        this.setProduct(cart.getToolOffer());
    }

}
