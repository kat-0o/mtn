import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IReportParameter } from 'app/shared/model/report-parameter.model';
import { ReportParameterService } from './report-parameter.service';
import { ReportParameterDeleteDialogComponent } from './report-parameter-delete-dialog.component';

@Component({
  selector: 'jhi-report-parameter',
  templateUrl: './report-parameter.component.html',
})
export class ReportParameterComponent implements OnInit, OnDestroy {
  reportParameters?: IReportParameter[];
  eventSubscriber?: Subscription;

  constructor(
    protected reportParameterService: ReportParameterService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.reportParameterService.query().subscribe((res: HttpResponse<IReportParameter[]>) => (this.reportParameters = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInReportParameters();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IReportParameter): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInReportParameters(): void {
    this.eventSubscriber = this.eventManager.subscribe('reportParameterListModification', () => this.loadAll());
  }

  delete(reportParameter: IReportParameter): void {
    const modalRef = this.modalService.open(ReportParameterDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.reportParameter = reportParameter;
  }
}
