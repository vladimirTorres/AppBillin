package co.edu.sena.service.dto;

import co.edu.sena.domain.enumeration.State;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link co.edu.sena.domain.Contract} entity.
 */
public class ContractDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate dataInit;

    @NotNull
    private LocalDate dataFinal;

    @NotNull
    private Integer contractTerm;

    @NotNull
    private Double contractValue;

    @NotNull
    private State statecontract;

    private CustomerDTO customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataInit() {
        return dataInit;
    }

    public void setDataInit(LocalDate dataInit) {
        this.dataInit = dataInit;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Integer getContractTerm() {
        return contractTerm;
    }

    public void setContractTerm(Integer contractTerm) {
        this.contractTerm = contractTerm;
    }

    public Double getContractValue() {
        return contractValue;
    }

    public void setContractValue(Double contractValue) {
        this.contractValue = contractValue;
    }

    public State getStatecontract() {
        return statecontract;
    }

    public void setStatecontract(State statecontract) {
        this.statecontract = statecontract;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContractDTO)) {
            return false;
        }

        ContractDTO contractDTO = (ContractDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, contractDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContractDTO{" +
            "id=" + getId() +
            ", dataInit='" + getDataInit() + "'" +
            ", dataFinal='" + getDataFinal() + "'" +
            ", contractTerm=" + getContractTerm() +
            ", contractValue=" + getContractValue() +
            ", statecontract='" + getStatecontract() + "'" +
            ", customer=" + getCustomer() +
            "}";
    }
}
