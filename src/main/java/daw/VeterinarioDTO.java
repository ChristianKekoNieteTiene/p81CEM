
package daw;

import java.util.Objects;


/**
 *
 * @author christian
 */
public class VeterinarioDTO {
    
    private int idVet;
    private String nifVet;
    private String nomVet;
    private String dirVet;
    private String telVet;
    private String emailVet;

    public VeterinarioDTO(int idVet, String nifVet, String nomVet, String dirVet, String telVet, String emailVet) {
        this.idVet = idVet;
        this.nifVet = nifVet;
        this.nomVet = nomVet;
        this.dirVet = dirVet;
        this.telVet = telVet;
        this.emailVet = emailVet;
    }

    public int getIdVet() {
        return idVet;
    }

    public void setIdVet(int idVet) {
        this.idVet = idVet;
    }

    public String getNifVet() {
        return nifVet;
    }

    public void setNifVet(String nifVet) {
        this.nifVet = nifVet;
    }

    public String getNomVet() {
        return nomVet;
    }

    public void setNomVet(String nomVet) {
        this.nomVet = nomVet;
    }

    public String getDirVet() {
        return dirVet;
    }

    public void setDirVet(String dirVet) {
        this.dirVet = dirVet;
    }

    public String getTelVet() {
        return telVet;
    }

    public void setTelVet(String telVet) {
        this.telVet = telVet;
    }

    public String getEmailVet() {
        return emailVet;
    }

    public void setEmailVet(String emailVet) {
        this.emailVet = emailVet;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.idVet;
        hash = 67 * hash + Objects.hashCode(this.nifVet);
        hash = 67 * hash + Objects.hashCode(this.nomVet);
        hash = 67 * hash + Objects.hashCode(this.dirVet);
        hash = 67 * hash + Objects.hashCode(this.telVet);
        hash = 67 * hash + Objects.hashCode(this.emailVet);
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
        final VeterinarioDTO other = (VeterinarioDTO) obj;
        if (this.idVet != other.idVet) {
            return false;
        }
        if (!Objects.equals(this.nifVet, other.nifVet)) {
            return false;
        }
        if (!Objects.equals(this.nomVet, other.nomVet)) {
            return false;
        }
        if (!Objects.equals(this.dirVet, other.dirVet)) {
            return false;
        }
        if (!Objects.equals(this.telVet, other.telVet)) {
            return false;
        }
        return Objects.equals(this.emailVet, other.emailVet);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("VeterinarioDTO{");
        sb.append("idVet=").append(idVet);
        sb.append(", nifVet=").append(nifVet);
        sb.append(", nomVet=").append(nomVet);
        sb.append(", dirVet=").append(dirVet);
        sb.append(", telVet=").append(telVet);
        sb.append(", emailVet=").append(emailVet);
        sb.append('}');
        return sb.toString();
    }
    
}
