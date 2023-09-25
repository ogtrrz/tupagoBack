import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ITransaction } from 'app/shared/model/transaction.model';
import { getEntities } from './transaction.reducer';

export const Transaction = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const transactionList = useAppSelector(state => state.transaction.entities);
  const loading = useAppSelector(state => state.transaction.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="transaction-heading" data-cy="TransactionHeading">
        <Translate contentKey="tupagoBackApp.transaction.home.title">Transactions</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tupagoBackApp.transaction.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/transaction/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tupagoBackApp.transaction.home.createLabel">Create new Transaction</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {transactionList && transactionList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="tupagoBackApp.transaction.id">Id</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.transaction.inicialDate">Inicial Date</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.transaction.creadoDate">Creado Date</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.transaction.enviadoDate">Enviado Date</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.transaction.errorDate">Error Date</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.transaction.pagadoDate">Pagado Date</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.transaction.status">Status</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.transaction.reference">Reference</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.transaction.amount">Amount</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.transaction.type">Type</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.transaction.from">From</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.transaction.accountFrom">Account From</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.transaction.referenceFrom">Reference From</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.transaction.messageFrom">Message From</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.transaction.paymentString">Payment String</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.transaction.editBy">Edit By</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.transaction.editDate">Edit Date</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.transaction.createdBy">Created By</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.transaction.createsDate">Creates Date</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.transaction.accountuser">Accountuser</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.transaction.bank">Bank</Translate>
                </th>
                <th>
                  <Translate contentKey="tupagoBackApp.transaction.clientconnect">Clientconnect</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {transactionList.map((transaction, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/transaction/${transaction.id}`} color="link" size="sm">
                      {transaction.id}
                    </Button>
                  </td>
                  <td>
                    {transaction.inicialDate ? <TextFormat type="date" value={transaction.inicialDate} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    {transaction.creadoDate ? <TextFormat type="date" value={transaction.creadoDate} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    {transaction.enviadoDate ? <TextFormat type="date" value={transaction.enviadoDate} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    {transaction.errorDate ? <TextFormat type="date" value={transaction.errorDate} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    {transaction.pagadoDate ? <TextFormat type="date" value={transaction.pagadoDate} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    <Translate contentKey={`tupagoBackApp.Status.${transaction.status}`} />
                  </td>
                  <td>{transaction.reference}</td>
                  <td>{transaction.amount}</td>
                  <td>{transaction.type ? 'true' : 'false'}</td>
                  <td>{transaction.from}</td>
                  <td>{transaction.accountFrom}</td>
                  <td>{transaction.referenceFrom}</td>
                  <td>{transaction.messageFrom}</td>
                  <td>{transaction.paymentString}</td>
                  <td>{transaction.editBy}</td>
                  <td>{transaction.editDate ? <TextFormat type="date" value={transaction.editDate} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{transaction.createdBy}</td>
                  <td>
                    {transaction.createsDate ? <TextFormat type="date" value={transaction.createsDate} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    {transaction.accountuser ? (
                      <Link to={`/account-user/${transaction.accountuser.id}`}>{transaction.accountuser.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{transaction.bank ? <Link to={`/bank/${transaction.bank.id}`}>{transaction.bank.id}</Link> : ''}</td>
                  <td>
                    {transaction.clientconnect ? (
                      <Link to={`/client-connect/${transaction.clientconnect.id}`}>{transaction.clientconnect.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/transaction/${transaction.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/transaction/${transaction.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/transaction/${transaction.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="tupagoBackApp.transaction.home.notFound">No Transactions found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Transaction;
