package Entidades;

import Enums.EstadoMesa;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Sebastian Moreno
 */
@Entity
@Table(name =  "Mesa")
public class Mesa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "numeroMesa" , nullable = false)
    private Integer numMesa;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado" , nullable = true)
    private EstadoMesa estado;

    public Mesa() {
    }

    public Mesa(Long id, Integer numMesa) {
        this.id = id;
        this.numMesa = numMesa;
    }

    public Mesa(Long id, Integer numMesa, EstadoMesa estado) {
        this.id = id;
        this.numMesa = numMesa;
        this.estado = estado;
    }

    public Mesa(Integer numMesa, EstadoMesa estado) {
        this.numMesa = numMesa;
        this.estado = estado;
    }

    public Mesa(Integer numMesa) {
        this.numMesa = numMesa;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(Integer numMesa) {
        this.numMesa = numMesa;
    }

    public EstadoMesa getEstado() {
        return estado;
    }

    public void setEstado(EstadoMesa estado) {
        this.estado = estado;
    }

    

    @Override
    public String toString() {
        return "Mesa{" + "id=" + id + ", numMesa=" + numMesa + '}';
    }

    
    
    
}
