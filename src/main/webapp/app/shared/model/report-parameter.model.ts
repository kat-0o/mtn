import { IReport } from 'app/shared/model/report.model';
import { ParameterType } from 'app/shared/model/enumerations/parameter-type.model';
import { ParameterDataType } from 'app/shared/model/enumerations/parameter-data-type.model';

export interface IReportParameter {
  id?: number;
  name?: string;
  description?: string;
  type?: ParameterType;
  dataType?: ParameterDataType;
  defaultValue?: string;
  report?: IReport;
}

export class ReportParameter implements IReportParameter {
  constructor(
    public id?: number,
    public name?: string,
    public description?: string,
    public type?: ParameterType,
    public dataType?: ParameterDataType,
    public defaultValue?: string,
    public report?: IReport
  ) {}
}
