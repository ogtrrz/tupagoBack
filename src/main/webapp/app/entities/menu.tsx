import React from 'react';
import { Translate } from 'react-jhipster';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/account-user">
        <Translate contentKey="global.menu.entities.accountUser" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/bank">
        <Translate contentKey="global.menu.entities.bank" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/client-connect">
        <Translate contentKey="global.menu.entities.clientConnect" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/transaction">
        <Translate contentKey="global.menu.entities.transaction" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
