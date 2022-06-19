import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IInvoice, Invoice } from '../invoice.model';
import { InvoiceService } from '../service/invoice.service';
import { ICustomer } from 'app/entities/customer/customer.model';
import { CustomerService } from 'app/entities/customer/service/customer.service';

@Component({
  selector: 'appb-invoice-update',
  templateUrl: './invoice-update.component.html',
})
export class InvoiceUpdateComponent implements OnInit {
  isSaving = false;

  customersSharedCollection: ICustomer[] = [];

  editForm = this.fb.group({
    id: [],
    dataInit: [null, [Validators.required]],
    socialReason: [null, [Validators.required, Validators.maxLength(50)]],
    clientAddress: [null, [Validators.required, Validators.maxLength(200)]],
    phoneNumber: [null, [Validators.required, Validators.maxLength(50)]],
    quantityForServices: [null, [Validators.required]],
    priceServices: [null, [Validators.required]],
    totalValueServices: [null, [Validators.required]],
    totalIva: [null, [Validators.required]],
    netValues: [null, [Validators.required]],
    customer: [],
  });

  constructor(
    protected invoiceService: InvoiceService,
    protected customerService: CustomerService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ invoice }) => {
      this.updateForm(invoice);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const invoice = this.createFromForm();
    if (invoice.id !== undefined) {
      this.subscribeToSaveResponse(this.invoiceService.update(invoice));
    } else {
      this.subscribeToSaveResponse(this.invoiceService.create(invoice));
    }
  }

  trackCustomerById(_index: number, item: ICustomer): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInvoice>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(invoice: IInvoice): void {
    this.editForm.patchValue({
      id: invoice.id,
      dataInit: invoice.dataInit,
      socialReason: invoice.socialReason,
      clientAddress: invoice.clientAddress,
      phoneNumber: invoice.phoneNumber,
      quantityForServices: invoice.quantityForServices,
      priceServices: invoice.priceServices,
      totalValueServices: invoice.totalValueServices,
      totalIva: invoice.totalIva,
      netValues: invoice.netValues,
      customer: invoice.customer,
    });

    this.customersSharedCollection = this.customerService.addCustomerToCollectionIfMissing(
      this.customersSharedCollection,
      invoice.customer
    );
  }

  protected loadRelationshipsOptions(): void {
    this.customerService
      .query()
      .pipe(map((res: HttpResponse<ICustomer[]>) => res.body ?? []))
      .pipe(
        map((customers: ICustomer[]) =>
          this.customerService.addCustomerToCollectionIfMissing(customers, this.editForm.get('customer')!.value)
        )
      )
      .subscribe((customers: ICustomer[]) => (this.customersSharedCollection = customers));
  }

  protected createFromForm(): IInvoice {
    return {
      ...new Invoice(),
      id: this.editForm.get(['id'])!.value,
      dataInit: this.editForm.get(['dataInit'])!.value,
      socialReason: this.editForm.get(['socialReason'])!.value,
      clientAddress: this.editForm.get(['clientAddress'])!.value,
      phoneNumber: this.editForm.get(['phoneNumber'])!.value,
      quantityForServices: this.editForm.get(['quantityForServices'])!.value,
      priceServices: this.editForm.get(['priceServices'])!.value,
      totalValueServices: this.editForm.get(['totalValueServices'])!.value,
      totalIva: this.editForm.get(['totalIva'])!.value,
      netValues: this.editForm.get(['netValues'])!.value,
      customer: this.editForm.get(['customer'])!.value,
    };
  }
}
