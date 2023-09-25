import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IAccountUser } from 'app/shared/model/account-user.model';
import { getEntities } from './account-user.reducer';

export const AccountUser = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const accountUserList = useAppSelector(state => state.accountUser.entities);
  const loading = useAppSelector(state => state.accountUser.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="account-user-heading" data-cy="AccountUserHeading">
        <Translate contentKey="tupagoBackApp.accountUser.home.title">Account Users</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tupagoBackApp.accountUser.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/account-user/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tupagoBackApp.accountUser.home.createLabel">Create new Account User</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {accountUserList && accountUserList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="tupagoBackApp.accountUser.id">Id</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.accountUser.user">User</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.accountUser.userAccount">User Account</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.accountUser.inscription">Inscription</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.accountUser.userTelephone">User Telephone</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.accountUser.userName">User Name</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.accountUser.editBy">Edit By</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.accountUser.editDate">Edit Date</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.accountUser.createdBy">Created By</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.accountUser.createsDate">Creates Date</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {accountUserList.map((accountUser, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/account-user/${accountUser.id}`} color="link" size="sm">
                      {accountUser.id}
                    </Button>
                  </td>
                  <td>{accountUser.user}</td>
                  <td>{accountUser.userAccount}</td>
                  <td>
                    {accountUser.inscription ? <TextFormat type="date" value={accountUser.inscription} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{accountUser.userTelephone}</td>
                  <td>{accountUser.userName}</td>
                  <td>{accountUser.editBy}</td>
                  <td>{accountUser.editDate ? <TextFormat type="date" value={accountUser.editDate} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{accountUser.createdBy}</td>
                  <td>
                    {accountUser.createsDate ? <TextFormat type="date" value={accountUser.createsDate} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/account-user/${accountUser.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/account-user/${accountUser.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/account-user/${accountUser.id}/delete`}
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
              <Translate contentKey="tupagoBackApp.accountUser.home.notFound">No Account Users found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default AccountUser;
