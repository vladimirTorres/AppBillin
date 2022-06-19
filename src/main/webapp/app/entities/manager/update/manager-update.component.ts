import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IManager, Manager } from '../manager.model';
import { ManagerService } from '../service/manager.service';
import { IUser } from 'app/entities/user/user.model';
import { UserService } from 'app/entities/user/user.service';
import { State } from 'app/entities/enumerations/state.model';

@Component({
  selector: 'appb-manager-update',
  templateUrl: './manager-update.component.html',
})
export class ManagerUpdateComponent implements OnInit {
  isSaving = false;
  stateValues = Object.keys(State);

  usersSharedCollection: IUser[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.maxLength(50)]],
    email: [null, [Validators.required, Validators.maxLength(200)]],
    phoneNumber: [null, [Validators.required, Validators.maxLength(50)]],
    statusClient: [null, [Validators.required]],
    user: [null, Validators.required],
  });

  constructor(
    protected managerService: ManagerService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ manager }) => {
      this.updateForm(manager);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const manager = this.createFromForm();
    if (manager.id !== undefined) {
      this.subscribeToSaveResponse(this.managerService.update(manager));
    } else {
      this.subscribeToSaveResponse(this.managerService.create(manager));
    }
  }

  trackUserById(_index: number, item: IUser): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IManager>>): void {
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

  protected updateForm(manager: IManager): void {
    this.editForm.patchValue({
      id: manager.id,
      name: manager.name,
      email: manager.email,
      phoneNumber: manager.phoneNumber,
      statusClient: manager.statusClient,
      user: manager.user,
    });

    this.usersSharedCollection = this.userService.addUserToCollectionIfMissing(this.usersSharedCollection, manager.user);
  }

  protected loadRelationshipsOptions(): void {
    this.userService
      .query()
      .pipe(map((res: HttpResponse<IUser[]>) => res.body ?? []))
      .pipe(map((users: IUser[]) => this.userService.addUserToCollectionIfMissing(users, this.editForm.get('user')!.value)))
      .subscribe((users: IUser[]) => (this.usersSharedCollection = users));
  }

  protected createFromForm(): IManager {
    return {
      ...new Manager(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      email: this.editForm.get(['email'])!.value,
      phoneNumber: this.editForm.get(['phoneNumber'])!.value,
      statusClient: this.editForm.get(['statusClient'])!.value,
      user: this.editForm.get(['user'])!.value,
    };
  }
}
