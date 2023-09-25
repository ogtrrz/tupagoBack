import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IAccountUser } from 'app/shared/model/account-user.model';
import { getEntities as getAccountUsers } from 'app/entities/account-user/account-user.reducer';
import { IBank } from 'app/shared/model/bank.model';
import { getEntities as getBanks } from 'app/entities/bank/bank.reducer';
import { IClientConnect } from 'app/shared/model/client-connect.model';
import { getEntities as getClientConnects } from 'app/entities/client-connect/client-connect.reducer';
import { ITransaction } from 'app/shared/model/transaction.model';
import { Status } from 'app/shared/model/enumerations/status.model';
import { getEntity, updateEntity, createEntity, reset } from './transaction.reducer';

export const TransactionUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const accountUsers = useAppSelector(state => state.accountUser.entities);
  const banks = useAppSelector(state => state.bank.entities);
  const clientConnects = useAppSelector(state => state.clientConnect.entities);
  const transactionEntity = useAppSelector(state => state.transaction.entity);
  const loading = useAppSelector(state => state.transaction.loading);
  const updating = useAppSelector(state => state.transaction.updating);
  const updateSuccess = useAppSelector(state => state.transaction.updateSuccess);
  const statusValues = Object.keys(Status);

  const handleClose = () => {
    navigate('/transaction');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getAccountUsers({}));
    dispatch(getBanks({}));
    dispatch(getClientConnects({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.inicialDate = convertDateTimeToServer(values.inicialDate);
    values.creadoDate = convertDateTimeToServer(values.creadoDate);
    values.enviadoDate = convertDateTimeToServer(values.enviadoDate);
    values.errorDate = convertDateTimeToServer(values.errorDate);
    values.pagadoDate = convertDateTimeToServer(values.pagadoDate);
    values.editDate = convertDateTimeToServer(values.editDate);
    values.createsDate = convertDateTimeToServer(values.createsDate);

    const entity = {
      ...transactionEntity,
      ...values,
      accountuser: accountUsers.find(it => it.id.toString() === values.accountuser.toString()),
      bank: banks.find(it => it.id.toString() === values.bank.toString()),
      clientconnect: clientConnects.find(it => it.id.toString() === values.clientconnect.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          inicialDate: displayDefaultDateTime(),
          creadoDate: displayDefaultDateTime(),
          enviadoDate: displayDefaultDateTime(),
          errorDate: displayDefaultDateTime(),
          pagadoDate: displayDefaultDateTime(),
          editDate: displayDefaultDateTime(),
          createsDate: displayDefaultDateTime(),
        }
      : {
          status: 'INICIAL',
          ...transactionEntity,
          inicialDate: convertDateTimeFromServer(transactionEntity.inicialDate),
          creadoDate: convertDateTimeFromServer(transactionEntity.creadoDate),
          enviadoDate: convertDateTimeFromServer(transactionEntity.enviadoDate),
          errorDate: convertDateTimeFromServer(transactionEntity.errorDate),
          pagadoDate: convertDateTimeFromServer(transactionEntity.pagadoDate),
          editDate: convertDateTimeFromServer(transactionEntity.editDate),
          createsDate: convertDateTimeFromServer(transactionEntity.createsDate),
          accountuser: transactionEntity?.accountuser?.id,
          bank: transactionEntity?.bank?.id,
          clientconnect: transactionEntity?.clientconnect?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tupagoBackApp.transaction.home.createOrEditLabel" data-cy="TransactionCreateUpdateHeading">
            <Translate contentKey="tupagoBackApp.transaction.home.createOrEditLabel">Create or edit a Transaction</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="transaction-id"
                  label={translate('tupagoBackApp.transaction.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tupagoBackApp.transaction.inicialDate')}
                id="transaction-inicialDate"
                name="inicialDate"
                data-cy="inicialDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('tupagoBackApp.transaction.creadoDate')}
                id="transaction-creadoDate"
                name="creadoDate"
                data-cy="creadoDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('tupagoBackApp.transaction.enviadoDate')}
                id="transaction-enviadoDate"
                name="enviadoDate"
                data-cy="enviadoDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('tupagoBackApp.transaction.errorDate')}
                id="transaction-errorDate"
                name="errorDate"
                data-cy="errorDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('tupagoBackApp.transaction.pagadoDate')}
                id="transaction-pagadoDate"
                name="pagadoDate"
                data-cy="pagadoDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('tupagoBackApp.transaction.status')}
                id="transaction-status"
                name="status"
                data-cy="status"
                type="select"
              >
                {statusValues.map(status => (
                  <option value={status} key={status}>
                    {translate('tupagoBackApp.Status.' + status)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('tupagoBackApp.transaction.reference')}
                id="transaction-reference"
                name="reference"
                data-cy="reference"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('tupagoBackApp.transaction.amount')}
                id="transaction-amount"
                name="amount"
                data-cy="amount"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('tupagoBackApp.transaction.type')}
                id="transaction-type"
                name="type"
                data-cy="type"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tupagoBackApp.transaction.from')}
                id="transaction-from"
                name="from"
                data-cy="from"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('tupagoBackApp.transaction.accountFrom')}
                id="transaction-accountFrom"
                name="accountFrom"
                data-cy="accountFrom"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('tupagoBackApp.transaction.referenceFrom')}
                id="transaction-referenceFrom"
                name="referenceFrom"
                data-cy="referenceFrom"
                type="text"
              />
              <ValidatedField
                label={translate('tupagoBackApp.transaction.messageFrom')}
                id="transaction-messageFrom"
                name="messageFrom"
                data-cy="messageFrom"
                type="text"
              />
              <ValidatedField
                label={translate('tupagoBackApp.transaction.paymentString')}
                id="transaction-paymentString"
                name="paymentString"
                data-cy="paymentString"
                type="text"
              />
              <ValidatedField
                label={translate('tupagoBackApp.transaction.editBy')}
                id="transaction-editBy"
                name="editBy"
                data-cy="editBy"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <ValidatedField
                label={translate('tupagoBackApp.transaction.editDate')}
                id="transaction-editDate"
                name="editDate"
                data-cy="editDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('tupagoBackApp.transaction.createdBy')}
                id="transaction-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <ValidatedField
                label={translate('tupagoBackApp.transaction.createsDate')}
                id="transaction-createsDate"
                name="createsDate"
                data-cy="createsDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                id="transaction-accountuser"
                name="accountuser"
                data-cy="accountuser"
                label={translate('tupagoBackApp.transaction.accountuser')}
                type="select"
              >
                <option value="" key="0" />
                {accountUsers
                  ? accountUsers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="transaction-bank"
                name="bank"
                data-cy="bank"
                label={translate('tupagoBackApp.transaction.bank')}
                type="select"
              >
                <option value="" key="0" />
                {banks
                  ? banks.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="transaction-clientconnect"
                name="clientconnect"
                data-cy="clientconnect"
                label={translate('tupagoBackApp.transaction.clientconnect')}
                type="select"
              >
                <option value="" key="0" />
                {clientConnects
                  ? clientConnects.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/transaction" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default TransactionUpdate;
