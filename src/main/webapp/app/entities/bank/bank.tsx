import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IBank } from 'app/shared/model/bank.model';
import { getEntities } from './bank.reducer';

export const Bank = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const bankList = useAppSelector(state => state.bank.entities);
  const loading = useAppSelector(state => state.bank.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="bank-heading" data-cy="BankHeading">
        <Translate contentKey="tupagoBackApp.bank.home.title">Banks</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tupagoBackApp.bank.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/bank/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tupagoBackApp.bank.home.createLabel">Create new Bank</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {bankList && bankList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="tupagoBackApp.bank.id">Id</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.bank.bankName">Bank Name</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.bank.bankAccount">Bank Account</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.bank.editBy">Edit By</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.bank.editDate">Edit Date</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.bank.createdBy">Created By</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.bank.createsDate">Creates Date</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.bank.accountuser">Accountuser</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {bankList.map((bank, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/bank/${bank.id}`} color="link" size="sm">
                      {bank.id}
                    </Button>
                  </td>
                  <td>{bank.bankName}</td>
                  <td>{bank.bankAccount}</td>
                  <td>{bank.editBy}</td>
                  <td>{bank.editDate ? <TextFormat type="date" value={bank.editDate} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{bank.createdBy}</td>
                  <td>{bank.createsDate ? <TextFormat type="date" value={bank.createsDate} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{bank.accountuser ? <Link to={`/account-user/${bank.accountuser.id}`}>{bank.accountuser.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/bank/${bank.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/bank/${bank.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/bank/${bank.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="tupagoBackApp.bank.home.notFound">No Banks found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Bank;
