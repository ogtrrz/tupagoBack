import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './transaction.reducer';

export const TransactionDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const transactionEntity = useAppSelector(state => state.transaction.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="transactionDetailsHeading">
          <Translate contentKey="tupagoBackApp.transaction.detail.title">Transaction</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="tupagoBackApp.transaction.id">Id</Translate>
            </span>
          </dt>
          <dd>{transactionEntity.id}</dd>
          <dt>
            <span id="inicialDate">
              <Translate contentKey="tupagoBackApp.transaction.inicialDate">Inicial Date</Translate>
            </span>
          </dt>
          <dd>
            {transactionEntity.inicialDate ? (
              <TextFormat value={transactionEntity.inicialDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="creadoDate">
              <Translate contentKey="tupagoBackApp.transaction.creadoDate">Creado Date</Translate>
            </span>
          </dt>
          <dd>
            {transactionEntity.creadoDate ? <TextFormat value={transactionEntity.creadoDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="enviadoDate">
              <Translate contentKey="tupagoBackApp.transaction.enviadoDate">Enviado Date</Translate>
            </span>
          </dt>
          <dd>
            {transactionEntity.enviadoDate ? (
              <TextFormat value={transactionEntity.enviadoDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="errorDate">
              <Translate contentKey="tupagoBackApp.transaction.errorDate">Error Date</Translate>
            </span>
          </dt>
          <dd>
            {transactionEntity.errorDate ? <TextFormat value={transactionEntity.errorDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="pagadoDate">
              <Translate contentKey="tupagoBackApp.transaction.pagadoDate">Pagado Date</Translate>
            </span>
          </dt>
          <dd>
            {transactionEntity.pagadoDate ? <TextFormat value={transactionEntity.pagadoDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="status">
              <Translate contentKey="tupagoBackApp.transaction.status">Status</Translate>
            </span>
          </dt>
          <dd>{transactionEntity.status}</dd>
          <dt>
            <span id="reference">
              <Translate contentKey="tupagoBackApp.transaction.reference">Reference</Translate>
            </span>
          </dt>
          <dd>{transactionEntity.reference}</dd>
          <dt>
            <span id="amount">
              <Translate contentKey="tupagoBackApp.transaction.amount">Amount</Translate>
            </span>
          </dt>
          <dd>{transactionEntity.amount}</dd>
          <dt>
            <span id="type">
              <Translate contentKey="tupagoBackApp.transaction.type">Type</Translate>
            </span>
          </dt>
          <dd>{transactionEntity.type ? 'true' : 'false'}</dd>
          <dt>
            <span id="from">
              <Translate contentKey="tupagoBackApp.transaction.from">From</Translate>
            </span>
          </dt>
          <dd>{transactionEntity.from}</dd>
          <dt>
            <span id="accountFrom">
              <Translate contentKey="tupagoBackApp.transaction.accountFrom">Account From</Translate>
            </span>
          </dt>
          <dd>{transactionEntity.accountFrom}</dd>
          <dt>
            <span id="referenceFrom">
              <Translate contentKey="tupagoBackApp.transaction.referenceFrom">Reference From</Translate>
            </span>
          </dt>
          <dd>{transactionEntity.referenceFrom}</dd>
          <dt>
            <span id="messageFrom">
              <Translate contentKey="tupagoBackApp.transaction.messageFrom">Message From</Translate>
            </span>
          </dt>
          <dd>{transactionEntity.messageFrom}</dd>
          <dt>
            <span id="paymentString">
              <Translate contentKey="tupagoBackApp.transaction.paymentString">Payment String</Translate>
            </span>
          </dt>
          <dd>{transactionEntity.paymentString}</dd>
          <dt>
            <span id="editBy">
              <Translate contentKey="tupagoBackApp.transaction.editBy">Edit By</Translate>
            </span>
          </dt>
          <dd>{transactionEntity.editBy}</dd>
          <dt>
            <span id="editDate">
              <Translate contentKey="tupagoBackApp.transaction.editDate">Edit Date</Translate>
            </span>
          </dt>
          <dd>
            {transactionEntity.editDate ? <TextFormat value={transactionEntity.editDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="tupagoBackApp.transaction.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{transactionEntity.createdBy}</dd>
          <dt>
            <span id="createsDate">
              <Translate contentKey="tupagoBackApp.transaction.createsDate">Creates Date</Translate>
            </span>
          </dt>
          <dd>
            {transactionEntity.createsDate ? (
              <TextFormat value={transactionEntity.createsDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="tupagoBackApp.transaction.accountuser">Accountuser</Translate>
          </dt>
          <dd>{transactionEntity.accountuser ? transactionEntity.accountuser.id : ''}</dd>
          <dt>
            <Translate contentKey="tupagoBackApp.transaction.bank">Bank</Translate>
          </dt>
          <dd>{transactionEntity.bank ? transactionEntity.bank.id : ''}</dd>
          <dt>
            <Translate contentKey="tupagoBackApp.transaction.clientconnect">Clientconnect</Translate>
          </dt>
          <dd>{transactionEntity.clientconnect ? transactionEntity.clientconnect.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/transaction" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/transaction/${transactionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default TransactionDetail;
