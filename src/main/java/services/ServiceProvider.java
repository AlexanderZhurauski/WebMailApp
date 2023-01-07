package services;

import dao.api.util.IDAOProvider;
import dao.util.DAOProviderFactory;
import dao.util.DAOType;
import services.api.*;
import services.api.util.IServiceProvider;
import services.util.HashSHA3Generator;

public class ServiceProvider implements IServiceProvider {
    private final IAdminStatisticService adminStatisticService;
    private final IMessageService messageService;
    private final IRegistrationService registrationService;
    private final ILoginService loginService;
    private final IOnlineUsersService onlineUsersService;
    private static volatile ServiceProvider instance;

    private ServiceProvider() {
        IDAOProvider daoProvider = DAOProviderFactory.getInstance(DAOType.MEMORY);
        messageService = new MessageService(daoProvider
                .getMessageDAO(), daoProvider.getUserDAO());
        adminStatisticService = new AdminStatisticService(
                daoProvider.getUserDAO(),
                daoProvider.getMessageDAO(),
                daoProvider.getOnlineUserDAO());
        registrationService = new RegistrationService(
                daoProvider,
                new HashSHA3Generator());
        loginService = new LoginService(
                daoProvider.getUserDAO(),
                new HashSHA3Generator());
        onlineUsersService = new OnlineUserService(daoProvider.getOnlineUserDAO());
    }

    public static ServiceProvider getInstance() {
        if (instance == null) {
            synchronized (ServiceProvider.class) {
                if (instance == null) {
                    instance = new ServiceProvider();
                }
            }
        }
        return instance;
    }

    @Override
    public IMessageService getMessageService() {
        return messageService;
    }

    @Override
    public IAdminStatisticService getAdminStatisticService() {
        return adminStatisticService;
    }

    @Override
    public IRegistrationService getRegistrationService() {
        return registrationService;
    }

    @Override
    public ILoginService getLoginService() {
        return loginService;
    }

    @Override
    public IOnlineUsersService getOnlineUserService() {
        return onlineUsersService;
    }
}
