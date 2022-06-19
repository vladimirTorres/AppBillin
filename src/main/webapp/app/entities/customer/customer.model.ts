import { IUser } from 'app/entities/user/user.model';
import { IInvoice } from 'app/entities/invoice/invoice.model';
import { IContract } from 'app/entities/contract/contract.model';
import { State } from 'app/entities/enumerations/state.model';

export interface ICustomer {
  id?: number;
  socialReason?: string;
  nameContact?: string;
  email?: string;
  phoneNumber?: string;
  statusClient?: State;
  user?: IUser;
  invoices?: IInvoice[] | null;
  contracts?: IContract[] | null;
}

export class Customer implements ICustomer {
  constructor(
    public id?: number,
    public socialReason?: string,
    public nameContact?: string,
    public email?: string,
    public phoneNumber?: string,
    public statusClient?: State,
    public user?: IUser,
    public invoices?: IInvoice[] | null,
    public contracts?: IContract[] | null
  ) {}
}

export function getCustomerIdentifier(customer: ICustomer): number | undefined {
  return customer.id;
}
