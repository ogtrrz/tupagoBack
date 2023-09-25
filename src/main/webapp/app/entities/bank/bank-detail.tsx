import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './bank.reducer';

export const BankDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const bankEntity = useAppSelector(state => state.bank.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="bankDetailsHeading">
          <Translate contentKey="tupagoBackApp.bank.detail.title">Bank</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="tupagoBackApp.bank.id">Id</Translate>
            </span>
          </dt>
          <dd>{bankEntity.id}</dd>
          <dt>
            <span id="bankName">
              <Translate contentKey="tupagoBackApp.bank.bankName">Bank Name</Translate>
            </span>
          </dt>
          <dd>{bankEntity.bankName}</dd>
          <dt>
            <span id="bankAccount">
              <Translate contentKey="tupagoBackApp.bank.bankAccount">Bank Account</Translate>
            </span>
          </dt>
          <dd>{bankEntity.bankAccount}</dd>
          <dt>
            <span id="editBy">
              <Translate contentKey="tupagoBackApp.bank.editBy">Edit By</Translate>
            </span>
          </dt>
          <dd>{bankEntity.editBy}</dd>
          <dt>
            <span id="editDate">
              <Translate contentKey="tupagoBackApp.bank.editDate">Edit Date</Translate>
            </span>
          </dt>
          <dd>{bankEntity.editDate ? <TextFormat value={bankEntity.editDate} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="tupagoBackApp.bank.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{bankEntity.createdBy}</dd>
          <dt>
            <span id="createsDate">
              <Translate contentKey="tupagoBackApp.bank.createsDate">Creates Date</Translate>
            </span>
          </dt>
          <dd>{bankEntity.createsDate ? <TextFormat value={bankEntity.createsDate} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <Translate contentKey="tupagoBackApp.bank.accountuser">Accountuser</Translate>
          </dt>
          <dd>{bankEntity.accountuser ? bankEntity.accountuser.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/bank" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/bank/${bankEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default BankDetail;
