import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import ClientConnect from './client-connect';
import ClientConnectDetail from './client-connect-detail';
import ClientConnectUpdate from './client-connect-update';
import ClientConnectDeleteDialog from './client-connect-delete-dialog';

const ClientConnectRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<ClientConnect />} />
    <Route path="new" element={<ClientConnectUpdate />} />
    <Route path=":id">
      <Route index element={<ClientConnectDetail />} />
      <Route path="edit" element={<ClientConnectUpdate />} />
      <Route path="delete" element={<ClientConnectDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default ClientConnectRoutes;
