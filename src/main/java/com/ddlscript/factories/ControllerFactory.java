package com.ddlscript.factories;

import com.ddlscript.def.controllers.SessionController;
import com.ddlscript.def.controllers.UserController;
import com.ddlscript.repository.embedded.controllers.EmbeddedDatabaseSessionController;
import com.ddlscript.repository.embedded.controllers.EmbeddedDatabaseUserController;
import lombok.Getter;

/**
 * Controller factory.
 */
public enum ControllerFactory {

	/**
	 * Singleton instance.
	 */
	INSTANCE;

	@Getter
	private final SessionController sessionController = new EmbeddedDatabaseSessionController(DataSourceFactory.INSTANCE.getDataSource());

	@Getter
	private final UserController userController = new EmbeddedDatabaseUserController(DataSourceFactory.INSTANCE.getDataSource());

}
