package co.edu.sena.service.mapper;

import co.edu.sena.domain.Contract;
import co.edu.sena.domain.Customer;
import co.edu.sena.service.dto.ContractDTO;
import co.edu.sena.service.dto.CustomerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Contract} and its DTO {@link ContractDTO}.
 */
@Mapper(componentModel = "spring")
public interface ContractMapper extends EntityMapper<ContractDTO, Contract> {
    @Mapping(target = "customer", source = "customer", qualifiedByName = "customerId")
    ContractDTO toDto(Contract s);

    @Named("customerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CustomerDTO toDtoCustomerId(Customer customer);
}
