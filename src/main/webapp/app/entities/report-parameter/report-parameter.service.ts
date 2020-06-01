import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IReportParameter } from 'app/shared/model/report-parameter.model';

type EntityResponseType = HttpResponse<IReportParameter>;
type EntityArrayResponseType = HttpResponse<IReportParameter[]>;

@Injectable({ providedIn: 'root' })
export class ReportParameterService {
  public resourceUrl = SERVER_API_URL + 'api/report-parameters';

  constructor(protected http: HttpClient) {}

  create(reportParameter: IReportParameter): Observable<EntityResponseType> {
    return this.http.post<IReportParameter>(this.resourceUrl, reportParameter, { observe: 'response' });
  }

  update(reportParameter: IReportParameter): Observable<EntityResponseType> {
    return this.http.put<IReportParameter>(this.resourceUrl, reportParameter, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IReportParameter>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IReportParameter[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
