import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'customer',
        data: { pageTitle: 'appbillingApp.customer.home.title' },
        loadChildren: () => import('./customer/customer.module').then(m => m.CustomerModule),
      },
      {
        path: 'manager',
        data: { pageTitle: 'appbillingApp.manager.home.title' },
        loadChildren: () => import('./manager/manager.module').then(m => m.ManagerModule),
      },
      {
        path: 'contract',
        data: { pageTitle: 'appbillingApp.contract.home.title' },
        loadChildren: () => import('./contract/contract.module').then(m => m.ContractModule),
      },
      {
        path: 'invoice',
        data: { pageTitle: 'appbillingApp.invoice.home.title' },
        loadChildren: () => import('./invoice/invoice.module').then(m => m.InvoiceModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
