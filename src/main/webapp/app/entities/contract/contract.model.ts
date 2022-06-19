import dayjs from 'dayjs/esm';
import { ICustomer } from 'app/entities/customer/customer.model';
import { State } from 'app/entities/enumerations/state.model';

export interface IContract {
  id?: number;
  dataInit?: dayjs.Dayjs;
  dataFinal?: dayjs.Dayjs;
  contractTerm?: number;
  contractValue?: number;
  statecontract?: State;
  customer?: ICustomer | null;
}

export class Contract implements IContract {
  constructor(
    public id?: number,
    public dataInit?: dayjs.Dayjs,
    public dataFinal?: dayjs.Dayjs,
    public contractTerm?: number,
    public contractValue?: number,
    public statecontract?: State,
    public customer?: ICustomer | null
  ) {}
}

export function getContractIdentifier(contract: IContract): number | undefined {
  return contract.id;
}
