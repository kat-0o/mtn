import { Moment } from 'moment';
import { IUserRole } from 'app/shared/model/user-role.model';
import { IReportParameter } from 'app/shared/model/report-parameter.model';
import { ReportType } from 'app/shared/model/enumerations/report-type.model';

export interface IReport {
  id?: number;
  name?: string;
  description?: string;
  type?: ReportType;
  lastUpdate?: Moment;
  config?: any;
  roles?: IUserRole[];
  reportParameters?: IReportParameter[];
}

export class Report implements IReport {
  constructor(
    public id?: number,
    public name?: string,
    public description?: string,
    public type?: ReportType,
    public lastUpdate?: Moment,
    public config?: any,
    public roles?: IUserRole[],
    public reportParameters?: IReportParameter[]
  ) {}
}
