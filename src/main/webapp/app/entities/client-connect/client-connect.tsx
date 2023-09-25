import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IClientConnect } from 'app/shared/model/client-connect.model';
import { getEntities } from './client-connect.reducer';

export const ClientConnect = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const clientConnectList = useAppSelector(state => state.clientConnect.entities);
  const loading = useAppSelector(state => state.clientConnect.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="client-connect-heading" data-cy="ClientConnectHeading">
        <Translate contentKey="tupagoBackApp.clientConnect.home.title">Client Connects</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tupagoBackApp.clientConnect.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/client-connect/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tupagoBackApp.clientConnect.home.createLabel">Create new Client Connect</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {clientConnectList && clientConnectList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="tupagoBackApp.clientConnect.id">Id</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.clientConnect.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.clientConnect.type">Type</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.clientConnect.identifier">Identifier</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.clientConnect.located">Located</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.clientConnect.editBy">Edit By</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.clientConnect.editDate">Edit Date</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.clientConnect.createdBy">Created By</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.clientConnect.createsDate">Creates Date</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.clientConnect.accountuser">Accountuser</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {clientConnectList.map((clientConnect, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/client-connect/${clientConnect.id}`} color="link" size="sm">
                      {clientConnect.id}
                    </Button>
                  </td>
                  <td>{clientConnect.name}</td>
                  <td>{clientConnect.type}</td>
                  <td>{clientConnect.identifier}</td>
                  <td>{clientConnect.located}</td>
                  <td>{clientConnect.editBy}</td>
                  <td>
                    {clientConnect.editDate ? <TextFormat type="date" value={clientConnect.editDate} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{clientConnect.createdBy}</td>
                  <td>
                    {clientConnect.createsDate ? (
                      <TextFormat type="date" value={clientConnect.createsDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {clientConnect.accountuser ? (
                      <Link to={`/account-user/${clientConnect.accountuser.id}`}>{clientConnect.accountuser.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/client-connect/${clientConnect.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/client-connect/${clientConnect.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/client-connect/${clientConnect.id}/delete`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="tupagoBackApp.clientConnect.home.notFound">No Client Connects found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default ClientConnect;
