package services;

import dao.DAOProvider;
import services.api.IAdminStatisticService;
import services.api.IMessageService;

public class ServiceProvider {
    private final IAdminStatisticService adminStatisticService;
    private IMessageService messageService;
    private static volatile ServiceProvider instance;

    private ServiceProvider() {
        messageService = new MessageService(DAOProvider.getInstance()
                .getMessageDAO());
        adminStatisticService = new AdminStatisticService(
                DAOProvider.getInstance().getUserDAO(),
                DAOProvider.getInstance().getMessageDAO());
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

}
