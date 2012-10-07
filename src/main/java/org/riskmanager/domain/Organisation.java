package org.riskmanager.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 07.10.12
 * Time: 20:34
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "ORGANISATIONS")
public class Organisation implements Serializable {


    private static final long serialVersionUID = 6237433546050798377L;

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;


    @Column(name = "ORGANISATION_NAME")
    private String organisationName;

    @Column(name = "ORGANISATION_ADDRESS")
    private String organisationAddress;

    public Organisation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public String getOrganisationAddress() {
        return organisationAddress;
    }

    public void setOrganisationAddress(String organisationAddress) {
        this.organisationAddress = organisationAddress;
    }
}
