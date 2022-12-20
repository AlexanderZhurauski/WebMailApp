package services;

import dao.DAOProvider;
import services.api.IAdminStatisticService;
import services.api.IMessageService;
import services.api.IRegistrationService;
import services.util.HashSHA3Generator;

public class ServiceProvider {
    private final IAdminStatisticService adminStatisticService;
    private final IMessageService messageService;
    private final IRegistrationService registrationService;
    private static volatile ServiceProvider instance;

    private ServiceProvider() {
        messageService = new MessageService(DAOProvider.getInstance()
                .getMessageDAO());
        adminStatisticService = new AdminStatisticService(
                DAOProvider.getInstance().getUserDAO(),
                DAOProvider.getInstance().getMessageDAO());
        registrationService = new RegistrationService(
                DAOProvider.getInstance(),
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

    public IAdminStatisticService getAdminStatisticService(){
        return adminStatisticService;
    }

    public IRegistrationService getRegistrationService() {
        return registrationService;
    }

}
