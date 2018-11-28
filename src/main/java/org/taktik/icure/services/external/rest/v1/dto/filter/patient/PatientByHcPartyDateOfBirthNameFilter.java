package org.taktik.icure.services.external.rest.v1.dto.filter.patient;

import com.google.common.base.Objects;
import org.taktik.icure.entities.Patient;
import org.taktik.icure.services.external.rest.handlers.JsonPolymorphismRoot;
import org.taktik.icure.services.external.rest.v1.dto.filter.Filter;

import java.util.Optional;

import static org.taktik.icure.db.StringUtils.sanitizeString;

@JsonPolymorphismRoot(Filter.class)
public class PatientByHcPartyDateOfBirthNameFilter extends Filter<Patient> implements org.taktik.icure.dto.filter.patient.PatientByHcPartyDateOfBirthNameFilter {

    private String name;
    private Integer dateOfBirth;
    private String healthcarePartyId;
    private Integer ssin;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Integer dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String getHealthcarePartyId() {
        return healthcarePartyId;
    }

    public void setHealthcarePartyId(String healthcarePartyId) {
        this.healthcarePartyId = healthcarePartyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PatientByHcPartyDateOfBirthNameFilter that = (PatientByHcPartyDateOfBirthNameFilter) o;

        return Objects.equal(this.dateOfBirth, that.dateOfBirth) &&
            Objects.equal(this.healthcarePartyId, that.healthcarePartyId) &&
            Objects.equal(this.name, that.name) &&
            Objects.equal(this.ssin, that.ssin);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dateOfBirth, healthcarePartyId, name);
    }

    @Override
    public boolean matches(Patient item) {
        String ss = sanitizeString(name);
        return (healthcarePartyId == null || item.getDelegations().keySet().contains(healthcarePartyId))
            && ((dateOfBirth == null || item.getDateOfBirth()!=null && java.util.Objects.equals(item.getDateOfBirth(), dateOfBirth))
            || (ssin == null || item.getSsin()!=null && java.util.Objects.equals(item.getSsin(), ssin))
            || (sanitizeString(Optional.of(item.getLastName()).orElse("") + Optional.of(item.getFirstName()).orElse("")).contains(ss) ||
            sanitizeString(Optional.of(item.getMaidenName()).orElse("")).contains(ss) ||
            sanitizeString(Optional.of(item.getPartnerName()).orElse("")).contains(ss)));
    }

    @Override
    public Integer getSsin() {
        return ssin;
    }

    public void setSsin(Integer ssin) {
        this.ssin = ssin;
    }
}
