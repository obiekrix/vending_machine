
import javax.persistence.*;

@Entity
@Table(name = "_user")
public class User {
    @Id
    @Column(name = "_id")
    private Long id;

    @Column(name = "_username")
    private String username;

    @Column(name = "_deposit")
    private Boolean deposit;

    @Column(name = "_password")
    private null password;

    @Column(name = "_role_id")
    private Long roleId;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getDeposit() {
        return this.deposit;
    }

    public void setDeposit(Boolean deposit) {
        this.deposit = deposit;
    }

    public null getPassword() {
        return this.password;
    }

    public void setPassword(null password) {
        this.password = password;
    }

    public Long getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
