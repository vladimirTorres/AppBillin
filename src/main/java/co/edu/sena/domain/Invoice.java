package co.edu.sena.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Invoice.
 */
@Entity
@Table(name = "invoice")
public class Invoice implements Serializable {

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
    @Size(max = 50)
    @Column(name = "social_reason", length = 50, nullable = false)
    private String socialReason;

    @NotNull
    @Size(max = 200)
    @Column(name = "client_address", length = 200, nullable = false)
    private String clientAddress;

    @NotNull
    @Size(max = 50)
    @Column(name = "phone_number", length = 50, nullable = false)
    private String phoneNumber;

    @NotNull
    @Column(name = "quantity_for_services", nullable = false)
    private Integer quantityForServices;

    @NotNull
    @Column(name = "price_services", nullable = false)
    private Double priceServices;

    @NotNull
    @Column(name = "total_value_services", nullable = false)
    private Double totalValueServices;

    @NotNull
    @Column(name = "total_iva", nullable = false)
    private Double totalIva;

    @NotNull
    @Column(name = "net_values", nullable = false)
    private Double netValues;

    @ManyToOne
    @JsonIgnoreProperties(value = { "user", "invoices", "contracts" }, allowSetters = true)
    private Customer customer;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Invoice id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataInit() {
        return this.dataInit;
    }

    public Invoice dataInit(LocalDate dataInit) {
        this.setDataInit(dataInit);
        return this;
    }

    public void setDataInit(LocalDate dataInit) {
        this.dataInit = dataInit;
    }

    public String getSocialReason() {
        return this.socialReason;
    }

    public Invoice socialReason(String socialReason) {
        this.setSocialReason(socialReason);
        return this;
    }

    public void setSocialReason(String socialReason) {
        this.socialReason = socialReason;
    }

    public String getClientAddress() {
        return this.clientAddress;
    }

    public Invoice clientAddress(String clientAddress) {
        this.setClientAddress(clientAddress);
        return this;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public Invoice phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getQuantityForServices() {
        return this.quantityForServices;
    }

    public Invoice quantityForServices(Integer quantityForServices) {
        this.setQuantityForServices(quantityForServices);
        return this;
    }

    public void setQuantityForServices(Integer quantityForServices) {
        this.quantityForServices = quantityForServices;
    }

    public Double getPriceServices() {
        return this.priceServices;
    }

    public Invoice priceServices(Double priceServices) {
        this.setPriceServices(priceServices);
        return this;
    }

    public void setPriceServices(Double priceServices) {
        this.priceServices = priceServices;
    }

    public Double getTotalValueServices() {
        return this.totalValueServices;
    }

    public Invoice totalValueServices(Double totalValueServices) {
        this.setTotalValueServices(totalValueServices);
        return this;
    }

    public void setTotalValueServices(Double totalValueServices) {
        this.totalValueServices = totalValueServices;
    }

    public Double getTotalIva() {
        return this.totalIva;
    }

    public Invoice totalIva(Double totalIva) {
        this.setTotalIva(totalIva);
        return this;
    }

    public void setTotalIva(Double totalIva) {
        this.totalIva = totalIva;
    }

    public Double getNetValues() {
        return this.netValues;
    }

    public Invoice netValues(Double netValues) {
        this.setNetValues(netValues);
        return this;
    }

    public void setNetValues(Double netValues) {
        this.netValues = netValues;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Invoice customer(Customer customer) {
        this.setCustomer(customer);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Invoice)) {
            return false;
        }
        return id != null && id.equals(((Invoice) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Invoice{" +
            "id=" + getId() +
            ", dataInit='" + getDataInit() + "'" +
            ", socialReason='" + getSocialReason() + "'" +
            ", clientAddress='" + getClientAddress() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", quantityForServices=" + getQuantityForServices() +
            ", priceServices=" + getPriceServices() +
            ", totalValueServices=" + getTotalValueServices() +
            ", totalIva=" + getTotalIva() +
            ", netValues=" + getNetValues() +
            "}";
    }
}
