import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IAccountUser } from 'app/shared/model/account-user.model';
import { getEntity, updateEntity, createEntity, reset } from './account-user.reducer';

export const AccountUserUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const accountUserEntity = useAppSelector(state => state.accountUser.entity);
  const loading = useAppSelector(state => state.accountUser.loading);
  const updating = useAppSelector(state => state.accountUser.updating);
  const updateSuccess = useAppSelector(state => state.accountUser.updateSuccess);

  const handleClose = () => {
    navigate('/account-user');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.inscription = convertDateTimeToServer(values.inscription);
    values.editDate = convertDateTimeToServer(values.editDate);
    values.createsDate = convertDateTimeToServer(values.createsDate);

    const entity = {
      ...accountUserEntity,
      ...values,
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
          inscription: displayDefaultDateTime(),
          editDate: displayDefaultDateTime(),
          createsDate: displayDefaultDateTime(),
        }
      : {
          ...accountUserEntity,
          inscription: convertDateTimeFromServer(accountUserEntity.inscription),
          editDate: convertDateTimeFromServer(accountUserEntity.editDate),
          createsDate: convertDateTimeFromServer(accountUserEntity.createsDate),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tupagoBackApp.accountUser.home.createOrEditLabel" data-cy="AccountUserCreateUpdateHeading">
            <Translate contentKey="tupagoBackApp.accountUser.home.createOrEditLabel">Create or edit a AccountUser</Translate>
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
                  id="account-user-id"
                  label={translate('tupagoBackApp.accountUser.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tupagoBackApp.accountUser.user')}
                id="account-user-user"
                name="user"
                data-cy="user"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <ValidatedField
                label={translate('tupagoBackApp.accountUser.userAccount')}
                id="account-user-userAccount"
                name="userAccount"
                data-cy="userAccount"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('tupagoBackApp.accountUser.inscription')}
                id="account-user-inscription"
                name="inscription"
                data-cy="inscription"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('tupagoBackApp.accountUser.userTelephone')}
                id="account-user-userTelephone"
                name="userTelephone"
                data-cy="userTelephone"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  minLength: { value: 10, message: translate('entity.validation.minlength', { min: 10 }) },
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <ValidatedField
                label={translate('tupagoBackApp.accountUser.userName')}
                id="account-user-userName"
                name="userName"
                data-cy="userName"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <ValidatedField
                label={translate('tupagoBackApp.accountUser.editBy')}
                id="account-user-editBy"
                name="editBy"
                data-cy="editBy"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <ValidatedField
                label={translate('tupagoBackApp.accountUser.editDate')}
                id="account-user-editDate"
                name="editDate"
                data-cy="editDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('tupagoBackApp.accountUser.createdBy')}
                id="account-user-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <ValidatedField
                label={translate('tupagoBackApp.accountUser.createsDate')}
                id="account-user-createsDate"
                name="createsDate"
                data-cy="createsDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/account-user" replace color="info">
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

export default AccountUserUpdate;
