import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IUserRole, UserRole } from 'app/shared/model/user-role.model';
import { UserRoleService } from './user-role.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { IReport } from 'app/shared/model/report.model';
import { ReportService } from 'app/entities/report/report.service';

type SelectableEntity = IUser | IReport;

@Component({
  selector: 'jhi-user-role-update',
  templateUrl: './user-role-update.component.html',
})
export class UserRoleUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];
  reports: IReport[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    description: [],
    users: [],
    report: [],
  });

  constructor(
    protected userRoleService: UserRoleService,
    protected userService: UserService,
    protected reportService: ReportService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userRole }) => {
      this.updateForm(userRole);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));

      this.reportService.query().subscribe((res: HttpResponse<IReport[]>) => (this.reports = res.body || []));
    });
  }

  updateForm(userRole: IUserRole): void {
    this.editForm.patchValue({
      id: userRole.id,
      name: userRole.name,
      description: userRole.description,
      users: userRole.users,
      report: userRole.report,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const userRole = this.createFromForm();
    if (userRole.id !== undefined) {
      this.subscribeToSaveResponse(this.userRoleService.update(userRole));
    } else {
      this.subscribeToSaveResponse(this.userRoleService.create(userRole));
    }
  }

  private createFromForm(): IUserRole {
    return {
      ...new UserRole(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      description: this.editForm.get(['description'])!.value,
      users: this.editForm.get(['users'])!.value,
      report: this.editForm.get(['report'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserRole>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }

  getSelected(selectedVals: IUser[], option: IUser): IUser {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
