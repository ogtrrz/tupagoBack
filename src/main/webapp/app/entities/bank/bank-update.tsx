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
import { getEntity, updateEntity, createEntity, reset } from './bank.reducer';

export const BankUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const accountUsers = useAppSelector(state => state.accountUser.entities);
  const bankEntity = useAppSelector(state => state.bank.entity);
  const loading = useAppSelector(state => state.bank.loading);
  const updating = useAppSelector(state => state.bank.updating);
  const updateSuccess = useAppSelector(state => state.bank.updateSuccess);

  const handleClose = () => {
    navigate('/bank');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getAccountUsers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.editDate = convertDateTimeToServer(values.editDate);
    values.createsDate = convertDateTimeToServer(values.createsDate);

    const entity = {
      ...bankEntity,
      ...values,
      accountuser: accountUsers.find(it => it.id.toString() === values.accountuser.toString()),
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
          editDate: displayDefaultDateTime(),
          createsDate: displayDefaultDateTime(),
        }
      : {
          ...bankEntity,
          editDate: convertDateTimeFromServer(bankEntity.editDate),
          createsDate: convertDateTimeFromServer(bankEntity.createsDate),
          accountuser: bankEntity?.accountuser?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tupagoBackApp.bank.home.createOrEditLabel" data-cy="BankCreateUpdateHeading">
            <Translate contentKey="tupagoBackApp.bank.home.createOrEditLabel">Create or edit a Bank</Translate>
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
                  id="bank-id"
                  label={translate('tupagoBackApp.bank.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tupagoBackApp.bank.bankName')}
                id="bank-bankName"
                name="bankName"
                data-cy="bankName"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <ValidatedField
                label={translate('tupagoBackApp.bank.bankAccount')}
                id="bank-bankAccount"
                name="bankAccount"
                data-cy="bankAccount"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                label={translate('tupagoBackApp.bank.editBy')}
                id="bank-editBy"
                name="editBy"
                data-cy="editBy"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <ValidatedField
                label={translate('tupagoBackApp.bank.editDate')}
                id="bank-editDate"
                name="editDate"
                data-cy="editDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('tupagoBackApp.bank.createdBy')}
                id="bank-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <ValidatedField
                label={translate('tupagoBackApp.bank.createsDate')}
                id="bank-createsDate"
                name="createsDate"
                data-cy="createsDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                id="bank-accountuser"
                name="accountuser"
                data-cy="accountuser"
                label={translate('tupagoBackApp.bank.accountuser')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/bank" replace color="info">
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

export default BankUpdate;
