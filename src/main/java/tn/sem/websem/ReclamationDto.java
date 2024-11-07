package tn.sem.websem;

import java.util.Date;

public class ReclamationDto {

    private Date dateReclamation;
    private String description_reclamation;
    private String statut_Reclamation;

    public Date getDateReclamation() {
        return dateReclamation;
    }

    public void setDateReclamation(Date dateReclamation) {
        this.dateReclamation = dateReclamation;
    }

    public String getDescription_reclamation() {
        return description_reclamation;
    }

    public void setDescription_reclamation(String description_reclamation) {
        this.description_reclamation = description_reclamation;
    }

    public String getStatut_Reclamation() {
        return statut_Reclamation;
    }

    public void setStatut_Reclamation(String statut_Reclamation) {
        this.statut_Reclamation = statut_Reclamation;
    }
}
