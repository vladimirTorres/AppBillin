package co.edu.sena.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link co.edu.sena.domain.Invoice} entity.
 */
public class InvoiceDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate dataInit;

    @NotNull
    @Size(max = 50)
    private String socialReason;

    @NotNull
    @Size(max = 200)
    private String clientAddress;

    @NotNull
    @Size(max = 50)
    private String phoneNumber;

    @NotNull
    private Integer quantityForServices;

    @NotNull
    private Double priceServices;

    @NotNull
    private Double totalValueServices;

    @NotNull
    private Double totalIva;

    @NotNull
    private Double netValues;

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

    public String getSocialReason() {
        return socialReason;
    }

    public void setSocialReason(String socialReason) {
        this.socialReason = socialReason;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getQuantityForServices() {
        return quantityForServices;
    }

    public void setQuantityForServices(Integer quantityForServices) {
        this.quantityForServices = quantityForServices;
    }

    public Double getPriceServices() {
        return priceServices;
    }

    public void setPriceServices(Double priceServices) {
        this.priceServices = priceServices;
    }

    public Double getTotalValueServices() {
        return totalValueServices;
    }

    public void setTotalValueServices(Double totalValueServices) {
        this.totalValueServices = totalValueServices;
    }

    public Double getTotalIva() {
        return totalIva;
    }

    public void setTotalIva(Double totalIva) {
        this.totalIva = totalIva;
    }

    public Double getNetValues() {
        return netValues;
    }

    public void setNetValues(Double netValues) {
        this.netValues = netValues;
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
        if (!(o instanceof InvoiceDTO)) {
            return false;
        }

        InvoiceDTO invoiceDTO = (InvoiceDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, invoiceDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InvoiceDTO{" +
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
            ", customer=" + getCustomer() +
            "}";
    }
}
