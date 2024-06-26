package br.com.atilarodrigues.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "cep_model")
public class CepModel {

    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(unique = true)
    private String cep;

    private String state;

    private String city;

    private String neighborhood;

    private String street;

    public CepModel() {
    }

    public CepModel(String cep, String state, String city, String neighborhood, String street) {
        this.cep = cep;
        this.state = state;
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "\n{" +
                "\n\tid='" + id + '\'' +
                "\n\tcep='" + cep + '\'' +
                "\n\tstate='" + state + '\'' +
                "\n\tcity='" + city + '\'' +
                "\n\tneighborhood='" + neighborhood + '\'' +
                "\n\tstreet='" + street + '\'' +
                "\n}";
    }
}
