package com.ddlscript.repository.embedded.controllers;

import com.ddlscript.def.controllers.UserGroupController;
import com.ddlscript.def.models.usergroups.CreateUserGroupRequest;
import com.ddlscript.def.models.usergroups.UserGroupIdentifier;
import com.ddlscript.def.models.usergroups.UserGroupModel;
import com.ddlscript.repository.embedded.models.EmbeddedDatabaseUserGroupModel;
import com.ddlscript.repository.embedded.rowdata.UserGroupRowData;
import com.ddlscript.utils.ResourceUtils;
import lombok.NonNull;

import javax.sql.DataSource;
import java.util.Optional;

public class EmbeddedDatabaseUserGroupController
		extends AbstractEmbeddedDatabaseController
		implements UserGroupController {

	private static final String SQL_FIND = ResourceUtils.getResourceAsString("/database/dml/usergroups/find.sql");

	private static final String SQL_CREATE = ResourceUtils.getResourceAsString("/database/dml/usergroups/create.sql");

	public EmbeddedDatabaseUserGroupController(@NonNull final DataSource withDataSource) {
		super(withDataSource);
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public Optional<UserGroupModel> find(
			final UserGroupIdentifier withIdentifier
	) {
		var instance = super.describe(
				UserGroupRowData.class
				, SQL_FIND
				, withIdentifier.getRawValue()
		);
		return Optional.ofNullable(instance)
				.map(EmbeddedDatabaseUserGroupModel::new);
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public Optional<UserGroupModel> create(final CreateUserGroupRequest withRequest) {
		var rowData = super.insert(
				UserGroupRowData.class
				, SQL_CREATE
				, withRequest.getName()
				, withRequest.getDescription()
				, withRequest.getUserCreated()
						.getIdentifier()
						.getRawValue()
		);
		return this.find(new UserGroupIdentifier(rowData.getId()));
	}
}
