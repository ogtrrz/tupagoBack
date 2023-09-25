import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './client-connect.reducer';

export const ClientConnectDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const clientConnectEntity = useAppSelector(state => state.clientConnect.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="clientConnectDetailsHeading">
          <Translate contentKey="tupagoBackApp.clientConnect.detail.title">ClientConnect</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="tupagoBackApp.clientConnect.id">Id</Translate>
            </span>
          </dt>
          <dd>{clientConnectEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="tupagoBackApp.clientConnect.name">Name</Translate>
            </span>
          </dt>
          <dd>{clientConnectEntity.name}</dd>
          <dt>
            <span id="type">
              <Translate contentKey="tupagoBackApp.clientConnect.type">Type</Translate>
            </span>
          </dt>
          <dd>{clientConnectEntity.type}</dd>
          <dt>
            <span id="identifier">
              <Translate contentKey="tupagoBackApp.clientConnect.identifier">Identifier</Translate>
            </span>
          </dt>
          <dd>{clientConnectEntity.identifier}</dd>
          <dt>
            <span id="located">
              <Translate contentKey="tupagoBackApp.clientConnect.located">Located</Translate>
            </span>
          </dt>
          <dd>{clientConnectEntity.located}</dd>
          <dt>
            <span id="editBy">
              <Translate contentKey="tupagoBackApp.clientConnect.editBy">Edit By</Translate>
            </span>
          </dt>
          <dd>{clientConnectEntity.editBy}</dd>
          <dt>
            <span id="editDate">
              <Translate contentKey="tupagoBackApp.clientConnect.editDate">Edit Date</Translate>
            </span>
          </dt>
          <dd>
            {clientConnectEntity.editDate ? <TextFormat value={clientConnectEntity.editDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="tupagoBackApp.clientConnect.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{clientConnectEntity.createdBy}</dd>
          <dt>
            <span id="createsDate">
              <Translate contentKey="tupagoBackApp.clientConnect.createsDate">Creates Date</Translate>
            </span>
          </dt>
          <dd>
            {clientConnectEntity.createsDate ? (
              <TextFormat value={clientConnectEntity.createsDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="tupagoBackApp.clientConnect.accountuser">Accountuser</Translate>
          </dt>
          <dd>{clientConnectEntity.accountuser ? clientConnectEntity.accountuser.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/client-connect" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/client-connect/${clientConnectEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ClientConnectDetail;
