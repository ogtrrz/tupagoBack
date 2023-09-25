import dayjs from 'dayjs';
import { ITransaction } from 'app/shared/model/transaction.model';
import { IAccountUser } from 'app/shared/model/account-user.model';

export interface IBank {
  id?: number;
  bankName?: string;
  bankAccount?: string;
  editBy?: string;
  editDate?: string;
  createdBy?: string;
  createsDate?: string;
  transactions?: ITransaction[] | null;
  accountuser?: IAccountUser | null;
}

export const defaultValue: Readonly<IBank> = {};
