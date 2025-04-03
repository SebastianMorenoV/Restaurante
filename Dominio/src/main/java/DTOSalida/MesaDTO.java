
package DTOSalida;

import Enums.EstadoMesa;

/**
 *
 * @author Sebastian Moreno
 */
public class MesaDTO {
    
   private Long id; 
   private Integer numeroMesa;
   private EstadoMesa estado;

    public MesaDTO(Long id, Integer numeroMesa, EstadoMesa estado) {
        this.id = id;
        this.numeroMesa = numeroMesa;
        this.estado = estado;
    }

    public MesaDTO(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public EstadoMesa getEstado() {
        return estado;
    }

    public void setEstado(EstadoMesa estado) {
        this.estado = estado;
    }
   
   

   
   
}
