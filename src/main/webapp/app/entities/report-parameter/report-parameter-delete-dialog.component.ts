import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IReportParameter } from 'app/shared/model/report-parameter.model';
import { ReportParameterService } from './report-parameter.service';

@Component({
  templateUrl: './report-parameter-delete-dialog.component.html',
})
export class ReportParameterDeleteDialogComponent {
  reportParameter?: IReportParameter;

  constructor(
    protected reportParameterService: ReportParameterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.reportParameterService.delete(id).subscribe(() => {
      this.eventManager.broadcast('reportParameterListModification');
      this.activeModal.close();
    });
  }
}
