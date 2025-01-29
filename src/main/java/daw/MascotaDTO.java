
package daw;

import java.time.LocalDate;

/**
 *
 * @author christian
 */
public class MascotaDTO {
    
    private int id;
    private String nombre;
    private int nChip;
    private double peso;
    private LocalDate fechNac;
    private String tipo;
    private int idVet;

    public MascotaDTO(int id, String nombre, int nChip, double peso, LocalDate fechNac, String tipo, int idVet) {
        this.id = id;
        this.nombre = nombre;
        this.nChip = nChip;
        this.peso = peso;
        this.fechNac = fechNac;
        this.tipo = tipo;
        this.idVet = idVet;
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

    public LocalDate getFechNac() {
        return fechNac;
    }

    public void setFechNac(LocalDate fechNac) {
        this.fechNac = fechNac;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdVet() {
        return idVet;
    }

    public void setIdVet(int idVet) {
        this.idVet = idVet;
    }
    
}

    

