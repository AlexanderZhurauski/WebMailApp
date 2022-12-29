package services;

import dao.DAOProvider;
import services.api.IAdminStatisticService;
import services.api.ILoginService;
import services.api.IMessageService;
import services.api.IRegistrationService;
import services.util.HashSHA3Generator;

public class ServiceProvider {
    private final IAdminStatisticService adminStatisticService;
    private final IMessageService messageService;
    private final IRegistrationService registrationService;
    private final ILoginService loginService;
    private static volatile ServiceProvider instance;

    private ServiceProvider() {
        messageService = new MessageService(DAOProvider.getInstance()
                .getMessageDAO(), DAOProvider.getInstance().getUserDAO());
        adminStatisticService = new AdminStatisticService(
                DAOProvider.getInstance().getUserDAO(),
                DAOProvider.getInstance().getMessageDAO(),
                DAOProvider.getInstance().getUserOnlineDAO());
        registrationService = new RegistrationService(
                DAOProvider.getInstance(),
                new HashSHA3Generator());
        loginService = new LoginService(
                DAOProvider.getInstance().getUserDAO(),
                new HashSHA3Generator());
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

    public IMessageService getMessageService() {
        return messageService;
    }

    public IAdminStatisticService getAdminStatisticService() {
        return adminStatisticService;
    }

    public IRegistrationService getRegistrationService() {
        return registrationService;
    }

    public ILoginService getLoginService() {
        return loginService;
    }
}
