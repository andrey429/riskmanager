package org.riskmanager.domain;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 20.10.12
 * Time: 13:22
 * To change this template use File | Settings | File Templates.
 */
public class AssetQuery {

    private Organisation organisation;
    private Person person;
    private Integer businessProcessType;
    private boolean requiresConfidentiality;
    private boolean requiresIntegrity;
    private boolean requiresAvailability;

    public AssetQuery() {

    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Integer getBusinessProcessType() {
        return businessProcessType;
    }

    public void setBusinessProcessType(Integer businessProcessType) {
        this.businessProcessType = businessProcessType;
    }

    public boolean getRequiresConfidentiality() {
        return requiresConfidentiality;
    }

    public void setRequiresConfidentiality(boolean requiresConfidentiality) {
        this.requiresConfidentiality = requiresConfidentiality;
    }

    public boolean getRequiresIntegrity() {
        return requiresIntegrity;
    }

    public void setRequiresIntegrity(boolean requiresIntegrity) {
        this.requiresIntegrity = requiresIntegrity;
    }

    public boolean getRequiresAvailability() {
        return requiresAvailability;
    }

    public void setRequiresAvailability(boolean requiresAvailability) {
        this.requiresAvailability = requiresAvailability;
    }



}
