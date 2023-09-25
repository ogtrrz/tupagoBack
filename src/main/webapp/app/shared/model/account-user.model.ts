import dayjs from 'dayjs';
import { IBank } from 'app/shared/model/bank.model';
import { IClientConnect } from 'app/shared/model/client-connect.model';
import { ITransaction } from 'app/shared/model/transaction.model';

export interface IAccountUser {
  id?: number;
  user?: string;
  userAccount?: string;
  inscription?: string;
  userTelephone?: string;
  userName?: string;
  editBy?: string;
  editDate?: string;
  createdBy?: string;
  createsDate?: string;
  banks?: IBank[] | null;
  clientconnects?: IClientConnect[] | null;
  transactions?: ITransaction[] | null;
}

export const defaultValue: Readonly<IAccountUser> = {};
