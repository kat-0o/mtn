import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IReportParameter, ReportParameter } from 'app/shared/model/report-parameter.model';
import { ReportParameterService } from './report-parameter.service';
import { IReport } from 'app/shared/model/report.model';
import { ReportService } from 'app/entities/report/report.service';

@Component({
  selector: 'jhi-report-parameter-update',
  templateUrl: './report-parameter-update.component.html',
})
export class ReportParameterUpdateComponent implements OnInit {
  isSaving = false;
  reports: IReport[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    description: [],
    type: [],
    dataType: [],
    defaultValue: [],
    report: [],
  });

  constructor(
    protected reportParameterService: ReportParameterService,
    protected reportService: ReportService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ reportParameter }) => {
      this.updateForm(reportParameter);

      this.reportService.query().subscribe((res: HttpResponse<IReport[]>) => (this.reports = res.body || []));
    });
  }

  updateForm(reportParameter: IReportParameter): void {
    this.editForm.patchValue({
      id: reportParameter.id,
      name: reportParameter.name,
      description: reportParameter.description,
      type: reportParameter.type,
      dataType: reportParameter.dataType,
      defaultValue: reportParameter.defaultValue,
      report: reportParameter.report,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const reportParameter = this.createFromForm();
    if (reportParameter.id !== undefined) {
      this.subscribeToSaveResponse(this.reportParameterService.update(reportParameter));
    } else {
      this.subscribeToSaveResponse(this.reportParameterService.create(reportParameter));
    }
  }

  private createFromForm(): IReportParameter {
    return {
      ...new ReportParameter(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      description: this.editForm.get(['description'])!.value,
      type: this.editForm.get(['type'])!.value,
      dataType: this.editForm.get(['dataType'])!.value,
      defaultValue: this.editForm.get(['defaultValue'])!.value,
      report: this.editForm.get(['report'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IReportParameter>>): void {
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

  trackById(index: number, item: IReport): any {
    return item.id;
  }
}
