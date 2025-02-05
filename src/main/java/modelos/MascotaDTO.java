
package modelos;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author christian
 */
public class MascotaDTO {
    
    private int id;
    private String nombre;
    private int nChip;
    private double peso;
    private Date fechNac;
    private String tipo;
    private Integer idVet;

    public MascotaDTO(int id, String nombre, int nChip, double peso, Date fechNac, String tipo, Integer idVet) {
        this.id = id;
        this.nombre = nombre;
        this.nChip = nChip;
        this.peso = peso;
        this.fechNac = fechNac;
        this.tipo = tipo;
        this.idVet = idVet;
    }

    public MascotaDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getnChip() {
        return nChip;
    }

    public void setnChip(int nChip) {
        this.nChip = nChip;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Date getFechNac() {
        return fechNac;
    }

    public void setFechNac(Date fechNac) {
        this.fechNac = fechNac;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getIdVet() {
        return idVet;
    }

    public void setIdVet(Integer idVet) {
        this.idVet = idVet;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.nombre);
        hash = 97 * hash + this.nChip;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.peso) ^ (Double.doubleToLongBits(this.peso) >>> 32));
        hash = 97 * hash + Objects.hashCode(this.fechNac);
        hash = 97 * hash + Objects.hashCode(this.tipo);
        hash = 97 * hash + this.idVet;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MascotaDTO other = (MascotaDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.nChip != other.nChip) {
            return false;
        }
        if (Double.doubleToLongBits(this.peso) != Double.doubleToLongBits(other.peso)) {
            return false;
        }
        if (this.idVet != other.idVet) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        return Objects.equals(this.fechNac, other.fechNac);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MascotaDTO{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", nChip=").append(nChip);
        sb.append(", peso=").append(peso);
        sb.append(", fechNac=").append(fechNac);
        sb.append(", tipo=").append(tipo);
        sb.append(", idVet=").append(idVet);
        sb.append('}');
        return sb.toString();
    }
    
}

    

