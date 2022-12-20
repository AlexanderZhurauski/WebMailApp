package services.api;

import dto.UserSessionDTO;

public interface IAdminStatisticService {

    int getMessageStatistic();

    String getOnlineUsers();

    String getUserStatistic();

    boolean verifyRole(UserSessionDTO user);
}
