import dayjs from 'dayjs';
import { ITransaction } from 'app/shared/model/transaction.model';
import { IAccountUser } from 'app/shared/model/account-user.model';

export interface IClientConnect {
  id?: number;
  name?: string;
  type?: string;
  identifier?: string;
  located?: string | null;
  editBy?: string;
  editDate?: string;
  createdBy?: string;
  createsDate?: string;
  transactions?: ITransaction[] | null;
  accountuser?: IAccountUser | null;
}

export const defaultValue: Readonly<IClientConnect> = {};
