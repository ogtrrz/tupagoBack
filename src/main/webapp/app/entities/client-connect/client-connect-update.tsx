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
import { IClientConnect } from 'app/shared/model/client-connect.model';
import { getEntity, updateEntity, createEntity, reset } from './client-connect.reducer';

export const ClientConnectUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const accountUsers = useAppSelector(state => state.accountUser.entities);
  const clientConnectEntity = useAppSelector(state => state.clientConnect.entity);
  const loading = useAppSelector(state => state.clientConnect.loading);
  const updating = useAppSelector(state => state.clientConnect.updating);
  const updateSuccess = useAppSelector(state => state.clientConnect.updateSuccess);

  const handleClose = () => {
    navigate('/client-connect');
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
      ...clientConnectEntity,
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
          ...clientConnectEntity,
          editDate: convertDateTimeFromServer(clientConnectEntity.editDate),
          createsDate: convertDateTimeFromServer(clientConnectEntity.createsDate),
          accountuser: clientConnectEntity?.accountuser?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tupagoBackApp.clientConnect.home.createOrEditLabel" data-cy="ClientConnectCreateUpdateHeading">
            <Translate contentKey="tupagoBackApp.clientConnect.home.createOrEditLabel">Create or edit a ClientConnect</Translate>
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
                  id="client-connect-id"
                  label={translate('tupagoBackApp.clientConnect.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tupagoBackApp.clientConnect.name')}
                id="client-connect-name"
                name="name"
                data-cy="name"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <ValidatedField
                label={translate('tupagoBackApp.clientConnect.type')}
                id="client-connect-type"
                name="type"
                data-cy="type"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <ValidatedField
                label={translate('tupagoBackApp.clientConnect.identifier')}
                id="client-connect-identifier"
                name="identifier"
                data-cy="identifier"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <ValidatedField
                label={translate('tupagoBackApp.clientConnect.located')}
                id="client-connect-located"
                name="located"
                data-cy="located"
                type="text"
                validate={{
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <ValidatedField
                label={translate('tupagoBackApp.clientConnect.editBy')}
                id="client-connect-editBy"
                name="editBy"
                data-cy="editBy"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <ValidatedField
                label={translate('tupagoBackApp.clientConnect.editDate')}
                id="client-connect-editDate"
                name="editDate"
                data-cy="editDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('tupagoBackApp.clientConnect.createdBy')}
                id="client-connect-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <ValidatedField
                label={translate('tupagoBackApp.clientConnect.createsDate')}
                id="client-connect-createsDate"
                name="createsDate"
                data-cy="createsDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                id="client-connect-accountuser"
                name="accountuser"
                data-cy="accountuser"
                label={translate('tupagoBackApp.clientConnect.accountuser')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/client-connect" replace color="info">
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

export default ClientConnectUpdate;
