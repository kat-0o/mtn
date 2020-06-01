import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'user-profile',
        loadChildren: () => import('./user-profile/user-profile.module').then(m => m.MtnDealerReportUserProfileModule),
      },
      {
        path: 'user-role',
        loadChildren: () => import('./user-role/user-role.module').then(m => m.MtnDealerReportUserRoleModule),
      },
      {
        path: 'report',
        loadChildren: () => import('./report/report.module').then(m => m.MtnDealerReportReportModule),
      },
      {
        path: 'report-parameter',
        loadChildren: () => import('./report-parameter/report-parameter.module').then(m => m.MtnDealerReportReportParameterModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class MtnDealerReportEntityModule {}
