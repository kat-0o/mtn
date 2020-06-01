import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MtnDealerReportSharedModule } from 'app/shared/shared.module';
import { ReportParameterComponent } from './report-parameter.component';
import { ReportParameterDetailComponent } from './report-parameter-detail.component';
import { ReportParameterUpdateComponent } from './report-parameter-update.component';
import { ReportParameterDeleteDialogComponent } from './report-parameter-delete-dialog.component';
import { reportParameterRoute } from './report-parameter.route';

@NgModule({
  imports: [MtnDealerReportSharedModule, RouterModule.forChild(reportParameterRoute)],
  declarations: [
    ReportParameterComponent,
    ReportParameterDetailComponent,
    ReportParameterUpdateComponent,
    ReportParameterDeleteDialogComponent,
  ],
  entryComponents: [ReportParameterDeleteDialogComponent],
})
export class MtnDealerReportReportParameterModule {}
