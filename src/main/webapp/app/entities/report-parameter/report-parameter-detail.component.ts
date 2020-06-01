import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IReportParameter } from 'app/shared/model/report-parameter.model';

@Component({
  selector: 'jhi-report-parameter-detail',
  templateUrl: './report-parameter-detail.component.html',
})
export class ReportParameterDetailComponent implements OnInit {
  reportParameter: IReportParameter | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ reportParameter }) => (this.reportParameter = reportParameter));
  }

  previousState(): void {
    window.history.back();
  }
}
