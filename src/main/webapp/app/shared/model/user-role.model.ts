import { IUser } from 'app/core/user/user.model';
import { IReport } from 'app/shared/model/report.model';

export interface IUserRole {
  id?: number;
  name?: string;
  description?: string;
  users?: IUser[];
  report?: IReport;
}

export class UserRole implements IUserRole {
  constructor(public id?: number, public name?: string, public description?: string, public users?: IUser[], public report?: IReport) {}
}
