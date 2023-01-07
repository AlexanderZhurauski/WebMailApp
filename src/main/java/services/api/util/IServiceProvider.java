package services.api.util;

import services.api.*;

public interface IServiceProvider {
    IMessageService getMessageService();

    IAdminStatisticService getAdminStatisticService();

    IRegistrationService getRegistrationService();

    ILoginService getLoginService();

    IOnlineUsersService getOnlineUserService();
}
