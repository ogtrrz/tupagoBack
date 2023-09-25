import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './account-user.reducer';

export const AccountUserDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const accountUserEntity = useAppSelector(state => state.accountUser.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="accountUserDetailsHeading">
          <Translate contentKey="tupagoBackApp.accountUser.detail.title">AccountUser</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="tupagoBackApp.accountUser.id">Id</Translate>
            </span>
          </dt>
          <dd>{accountUserEntity.id}</dd>
          <dt>
            <span id="user">
              <Translate contentKey="tupagoBackApp.accountUser.user">User</Translate>
            </span>
          </dt>
          <dd>{accountUserEntity.user}</dd>
          <dt>
            <span id="userAccount">
              <Translate contentKey="tupagoBackApp.accountUser.userAccount">User Account</Translate>
            </span>
          </dt>
          <dd>{accountUserEntity.userAccount}</dd>
          <dt>
            <span id="inscription">
              <Translate contentKey="tupagoBackApp.accountUser.inscription">Inscription</Translate>
            </span>
          </dt>
          <dd>
            {accountUserEntity.inscription ? (
              <TextFormat value={accountUserEntity.inscription} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="userTelephone">
              <Translate contentKey="tupagoBackApp.accountUser.userTelephone">User Telephone</Translate>
            </span>
          </dt>
          <dd>{accountUserEntity.userTelephone}</dd>
          <dt>
            <span id="userName">
              <Translate contentKey="tupagoBackApp.accountUser.userName">User Name</Translate>
            </span>
          </dt>
          <dd>{accountUserEntity.userName}</dd>
          <dt>
            <span id="editBy">
              <Translate contentKey="tupagoBackApp.accountUser.editBy">Edit By</Translate>
            </span>
          </dt>
          <dd>{accountUserEntity.editBy}</dd>
          <dt>
            <span id="editDate">
              <Translate contentKey="tupagoBackApp.accountUser.editDate">Edit Date</Translate>
            </span>
          </dt>
          <dd>
            {accountUserEntity.editDate ? <TextFormat value={accountUserEntity.editDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="tupagoBackApp.accountUser.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{accountUserEntity.createdBy}</dd>
          <dt>
            <span id="createsDate">
              <Translate contentKey="tupagoBackApp.accountUser.createsDate">Creates Date</Translate>
            </span>
          </dt>
          <dd>
            {accountUserEntity.createsDate ? (
              <TextFormat value={accountUserEntity.createsDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/account-user" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/account-user/${accountUserEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default AccountUserDetail;
