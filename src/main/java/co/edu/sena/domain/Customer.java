package co.edu.sena.domain;

import co.edu.sena.domain.enumeration.State;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "social_reason", length = 50, nullable = false)
    private String socialReason;

    @NotNull
    @Size(max = 50)
    @Column(name = "name_contact", length = 50, nullable = false)
    private String nameContact;

    @NotNull
    @Size(max = 200)
    @Column(name = "email", length = 200, nullable = false)
    private String email;

    @NotNull
    @Size(max = 50)
    @Column(name = "phone_number", length = 50, nullable = false)
    private String phoneNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status_client", nullable = false)
    private State statusClient;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private User user;

    @OneToMany(mappedBy = "customer")
    @JsonIgnoreProperties(value = { "customer" }, allowSetters = true)
    private Set<Invoice> invoices = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    @JsonIgnoreProperties(value = { "customer" }, allowSetters = true)
    private Set<Contract> contracts = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Customer id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSocialReason() {
        return this.socialReason;
    }

    public Customer socialReason(String socialReason) {
        this.setSocialReason(socialReason);
        return this;
    }

    public void setSocialReason(String socialReason) {
        this.socialReason = socialReason;
    }

    public String getNameContact() {
        return this.nameContact;
    }

    public Customer nameContact(String nameContact) {
        this.setNameContact(nameContact);
        return this;
    }

    public void setNameContact(String nameContact) {
        this.nameContact = nameContact;
    }

    public String getEmail() {
        return this.email;
    }

    public Customer email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public Customer phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public State getStatusClient() {
        return this.statusClient;
    }

    public Customer statusClient(State statusClient) {
        this.setStatusClient(statusClient);
        return this;
    }

    public void setStatusClient(State statusClient) {
        this.statusClient = statusClient;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Customer user(User user) {
        this.setUser(user);
        return this;
    }

    public Set<Invoice> getInvoices() {
        return this.invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        if (this.invoices != null) {
            this.invoices.forEach(i -> i.setCustomer(null));
        }
        if (invoices != null) {
            invoices.forEach(i -> i.setCustomer(this));
        }
        this.invoices = invoices;
    }

    public Customer invoices(Set<Invoice> invoices) {
        this.setInvoices(invoices);
        return this;
    }

    public Customer addInvoice(Invoice invoice) {
        this.invoices.add(invoice);
        invoice.setCustomer(this);
        return this;
    }

    public Customer removeInvoice(Invoice invoice) {
        this.invoices.remove(invoice);
        invoice.setCustomer(null);
        return this;
    }

    public Set<Contract> getContracts() {
        return this.contracts;
    }

    public void setContracts(Set<Contract> contracts) {
        if (this.contracts != null) {
            this.contracts.forEach(i -> i.setCustomer(null));
        }
        if (contracts != null) {
            contracts.forEach(i -> i.setCustomer(this));
        }
        this.contracts = contracts;
    }

    public Customer contracts(Set<Contract> contracts) {
        this.setContracts(contracts);
        return this;
    }

    public Customer addContract(Contract contract) {
        this.contracts.add(contract);
        contract.setCustomer(this);
        return this;
    }

    public Customer removeContract(Contract contract) {
        this.contracts.remove(contract);
        contract.setCustomer(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customer)) {
            return false;
        }
        return id != null && id.equals(((Customer) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Customer{" +
            "id=" + getId() +
            ", socialReason='" + getSocialReason() + "'" +
            ", nameContact='" + getNameContact() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", statusClient='" + getStatusClient() + "'" +
            "}";
    }
}
