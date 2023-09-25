import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import AccountUser from './account-user';
import Bank from './bank';
import ClientConnect from './client-connect';
import Transaction from './transaction';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="account-user/*" element={<AccountUser />} />
        <Route path="bank/*" element={<Bank />} />
        <Route path="client-connect/*" element={<ClientConnect />} />
        <Route path="transaction/*" element={<Transaction />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
