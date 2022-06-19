import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IContract, Contract } from '../contract.model';
import { ContractService } from '../service/contract.service';
import { ICustomer } from 'app/entities/customer/customer.model';
import { CustomerService } from 'app/entities/customer/service/customer.service';
import { State } from 'app/entities/enumerations/state.model';

@Component({
  selector: 'appb-contract-update',
  templateUrl: './contract-update.component.html',
})
export class ContractUpdateComponent implements OnInit {
  isSaving = false;
  stateValues = Object.keys(State);

  customersSharedCollection: ICustomer[] = [];

  editForm = this.fb.group({
    id: [],
    dataInit: [null, [Validators.required]],
    dataFinal: [null, [Validators.required]],
    contractTerm: [null, [Validators.required]],
    contractValue: [null, [Validators.required]],
    statecontract: [null, [Validators.required]],
    customer: [],
  });

  constructor(
    protected contractService: ContractService,
    protected customerService: CustomerService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ contract }) => {
      this.updateForm(contract);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const contract = this.createFromForm();
    if (contract.id !== undefined) {
      this.subscribeToSaveResponse(this.contractService.update(contract));
    } else {
      this.subscribeToSaveResponse(this.contractService.create(contract));
    }
  }

  trackCustomerById(_index: number, item: ICustomer): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IContract>>): void {
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

  protected updateForm(contract: IContract): void {
    this.editForm.patchValue({
      id: contract.id,
      dataInit: contract.dataInit,
      dataFinal: contract.dataFinal,
      contractTerm: contract.contractTerm,
      contractValue: contract.contractValue,
      statecontract: contract.statecontract,
      customer: contract.customer,
    });

    this.customersSharedCollection = this.customerService.addCustomerToCollectionIfMissing(
      this.customersSharedCollection,
      contract.customer
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

  protected createFromForm(): IContract {
    return {
      ...new Contract(),
      id: this.editForm.get(['id'])!.value,
      dataInit: this.editForm.get(['dataInit'])!.value,
      dataFinal: this.editForm.get(['dataFinal'])!.value,
      contractTerm: this.editForm.get(['contractTerm'])!.value,
      contractValue: this.editForm.get(['contractValue'])!.value,
      statecontract: this.editForm.get(['statecontract'])!.value,
      customer: this.editForm.get(['customer'])!.value,
    };
  }
}
