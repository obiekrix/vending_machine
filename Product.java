
import javax.persistence.*;

@Entity
@Table(name = "_product")
public class Product {
    @Id
    @Column(name = "_id")
    private Long id;

    @Column(name = "_name")
    private String name;

    @Column(name = "_cost")
    private Long cost;

    @Column(name = "_amount_available")
    private Long amountAvailable;

    @Column(name = "_seller_id")
    private Long sellerId;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCost() {
        return this.cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Long getAmountAvailable() {
        return this.amountAvailable;
    }

    public void setAmountAvailable(Long amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public Long getSellerId() {
        return this.sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}
