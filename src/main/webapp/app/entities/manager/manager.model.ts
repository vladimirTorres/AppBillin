import { IUser } from 'app/entities/user/user.model';
import { State } from 'app/entities/enumerations/state.model';

export interface IManager {
  id?: number;
  name?: string;
  email?: string;
  phoneNumber?: string;
  statusClient?: State;
  user?: IUser;
}

export class Manager implements IManager {
  constructor(
    public id?: number,
    public name?: string,
    public email?: string,
    public phoneNumber?: string,
    public statusClient?: State,
    public user?: IUser
  ) {}
}

export function getManagerIdentifier(manager: IManager): number | undefined {
  return manager.id;
}
