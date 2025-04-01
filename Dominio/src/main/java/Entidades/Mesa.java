package Entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Sebastian Moreno
 */
@Entity
public class Mesa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numMesa;

    public Mesa() {
    }

    public Mesa(Long id, Integer numMesa) {
        this.id = id;
        this.numMesa = numMesa;
    }

    public Mesa(Integer numMesa) {
        this.numMesa = numMesa;
    }

    public Integer getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(Integer numMesa) {
        this.numMesa = numMesa;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Mesa{" + "id=" + id + ", numMesa=" + numMesa + '}';
    }

    
    
    
}
