package co.edu.sena.domain;

import co.edu.sena.domain.enumeration.State;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Contract.
 */
@Entity
@Table(name = "contract")
public class Contract implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "data_init", nullable = false)
    private LocalDate dataInit;

    @NotNull
    @Column(name = "data_final", nullable = false)
    private LocalDate dataFinal;

    @NotNull
    @Column(name = "contract_term", nullable = false)
    private Integer contractTerm;

    @NotNull
    @Column(name = "contract_value", nullable = false)
    private Double contractValue;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "statecontract", nullable = false)
    private State statecontract;

    @ManyToOne
    @JsonIgnoreProperties(value = { "user", "invoices", "contracts" }, allowSetters = true)
    private Customer customer;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Contract id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataInit() {
        return this.dataInit;
    }

    public Contract dataInit(LocalDate dataInit) {
        this.setDataInit(dataInit);
        return this;
    }

    public void setDataInit(LocalDate dataInit) {
        this.dataInit = dataInit;
    }

    public LocalDate getDataFinal() {
        return this.dataFinal;
    }

    public Contract dataFinal(LocalDate dataFinal) {
        this.setDataFinal(dataFinal);
        return this;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Integer getContractTerm() {
        return this.contractTerm;
    }

    public Contract contractTerm(Integer contractTerm) {
        this.setContractTerm(contractTerm);
        return this;
    }

    public void setContractTerm(Integer contractTerm) {
        this.contractTerm = contractTerm;
    }

    public Double getContractValue() {
        return this.contractValue;
    }

    public Contract contractValue(Double contractValue) {
        this.setContractValue(contractValue);
        return this;
    }

    public void setContractValue(Double contractValue) {
        this.contractValue = contractValue;
    }

    public State getStatecontract() {
        return this.statecontract;
    }

    public Contract statecontract(State statecontract) {
        this.setStatecontract(statecontract);
        return this;
    }

    public void setStatecontract(State statecontract) {
        this.statecontract = statecontract;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Contract customer(Customer customer) {
        this.setCustomer(customer);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Contract)) {
            return false;
        }
        return id != null && id.equals(((Contract) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Contract{" +
            "id=" + getId() +
            ", dataInit='" + getDataInit() + "'" +
            ", dataFinal='" + getDataFinal() + "'" +
            ", contractTerm=" + getContractTerm() +
            ", contractValue=" + getContractValue() +
            ", statecontract='" + getStatecontract() + "'" +
            "}";
    }
}
