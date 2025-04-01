
package DTOSalida;

/**
 *
 * @author Sebastian Moreno
 */
public class MesaDTO {
 
   private Integer numeroMesa;

    public MesaDTO(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public Integer getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    @Override
    public String toString() {
        return "MesaDTO{" + "numeroMesa=" + numeroMesa + '}';
    }
   
   
   
}
