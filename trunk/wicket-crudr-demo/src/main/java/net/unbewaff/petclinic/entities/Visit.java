package net.unbewaff.petclinic.entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * @author David Hendrix (Nicktarix)
 *
 */
public class Visit implements Serializable {

    private static final long serialVersionUID = 1118459259952527439L;
    private static final transient DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
    private Integer id;
    private Pet patient;
    private Veterinarian vet;
    private Date date;
    private String treatment;

    /**
     * @param id
     * @param patient
     * @param vet
     * @param date
     * @param treatment
     * @throws ParseException
     */
    public Visit(Integer id, Pet patient, Veterinarian vet, String date, String treatment) throws ParseException {
        this.id = id;
        this.patient = patient;
        this.vet = vet;
        this.date = df.parse(date);
        this.treatment = treatment;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the patient
     */
    public Pet getPatient() {
        return patient;
    }

    /**
     * @param patient
     *            the patient to set
     */
    public void setPatient(Pet patient) {
        this.patient = patient;
    }

    /**
     * @return the vet
     */
    public Veterinarian getVet() {
        return vet;
    }

    /**
     * @param vet
     *            the vet to set
     */
    public void setVet(Veterinarian vet) {
        this.vet = vet;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the treatment
     */
    public String getTreatment() {
        return treatment;
    }

    /**
     * @param treatment
     *            the treatment to set
     */
    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

}