import accountUser from 'app/entities/account-user/account-user.reducer';
import bank from 'app/entities/bank/bank.reducer';
import clientConnect from 'app/entities/client-connect/client-connect.reducer';
import transaction from 'app/entities/transaction/transaction.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  accountUser,
  bank,
  clientConnect,
  transaction,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
