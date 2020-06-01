import { IUser } from 'app/core/user/user.model';

export interface IUserProfile {
  id?: number;
  mobile?: string;
  email?: string;
  user?: IUser;
}

export class UserProfile implements IUserProfile {
  constructor(public id?: number, public mobile?: string, public email?: string, public user?: IUser) {}
}
