import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IReportParameter, ReportParameter } from 'app/shared/model/report-parameter.model';
import { ReportParameterService } from './report-parameter.service';
import { ReportParameterComponent } from './report-parameter.component';
import { ReportParameterDetailComponent } from './report-parameter-detail.component';
import { ReportParameterUpdateComponent } from './report-parameter-update.component';

@Injectable({ providedIn: 'root' })
export class ReportParameterResolve implements Resolve<IReportParameter> {
  constructor(private service: ReportParameterService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IReportParameter> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((reportParameter: HttpResponse<ReportParameter>) => {
          if (reportParameter.body) {
            return of(reportParameter.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ReportParameter());
  }
}

export const reportParameterRoute: Routes = [
  {
    path: '',
    component: ReportParameterComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mtnDealerReportApp.reportParameter.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ReportParameterDetailComponent,
    resolve: {
      reportParameter: ReportParameterResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mtnDealerReportApp.reportParameter.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ReportParameterUpdateComponent,
    resolve: {
      reportParameter: ReportParameterResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mtnDealerReportApp.reportParameter.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ReportParameterUpdateComponent,
    resolve: {
      reportParameter: ReportParameterResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mtnDealerReportApp.reportParameter.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
