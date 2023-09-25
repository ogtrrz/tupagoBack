import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import AccountUser from './account-user';
import AccountUserDetail from './account-user-detail';
import AccountUserUpdate from './account-user-update';
import AccountUserDeleteDialog from './account-user-delete-dialog';

const AccountUserRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<AccountUser />} />
    <Route path="new" element={<AccountUserUpdate />} />
    <Route path=":id">
      <Route index element={<AccountUserDetail />} />
      <Route path="edit" element={<AccountUserUpdate />} />
      <Route path="delete" element={<AccountUserDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default AccountUserRoutes;
