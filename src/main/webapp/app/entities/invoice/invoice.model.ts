import dayjs from 'dayjs/esm';
import { ICustomer } from 'app/entities/customer/customer.model';

export interface IInvoice {
  id?: number;
  dataInit?: dayjs.Dayjs;
  socialReason?: string;
  clientAddress?: string;
  phoneNumber?: string;
  quantityForServices?: number;
  priceServices?: number;
  totalValueServices?: number;
  totalIva?: number;
  netValues?: number;
  customer?: ICustomer | null;
}

export class Invoice implements IInvoice {
  constructor(
    public id?: number,
    public dataInit?: dayjs.Dayjs,
    public socialReason?: string,
    public clientAddress?: string,
    public phoneNumber?: string,
    public quantityForServices?: number,
    public priceServices?: number,
    public totalValueServices?: number,
    public totalIva?: number,
    public netValues?: number,
    public customer?: ICustomer | null
  ) {}
}

export function getInvoiceIdentifier(invoice: IInvoice): number | undefined {
  return invoice.id;
}
