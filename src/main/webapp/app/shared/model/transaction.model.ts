import dayjs from 'dayjs';
import { IAccountUser } from 'app/shared/model/account-user.model';
import { IBank } from 'app/shared/model/bank.model';
import { IClientConnect } from 'app/shared/model/client-connect.model';
import { Status } from 'app/shared/model/enumerations/status.model';

export interface ITransaction {
  id?: number;
  inicialDate?: string | null;
  creadoDate?: string | null;
  enviadoDate?: string | null;
  errorDate?: string | null;
  pagadoDate?: string | null;
  status?: Status;
  reference?: string;
  amount?: number;
  type?: boolean;
  from?: string;
  accountFrom?: string;
  referenceFrom?: string | null;
  messageFrom?: string | null;
  paymentString?: string | null;
  editBy?: string;
  editDate?: string;
  createdBy?: string;
  createsDate?: string;
  accountuser?: IAccountUser | null;
  bank?: IBank | null;
  clientconnect?: IClientConnect | null;
}

export const defaultValue: Readonly<ITransaction> = {
  type: false,
};
