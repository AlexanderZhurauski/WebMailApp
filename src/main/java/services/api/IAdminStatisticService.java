package services.api;

import dto.UserSessionDTO;

public interface IAdminStatisticService {
    String getStatistic();

    int getMessageStatistic();

    String getOnlineUsers();

    String getUserStatistic();

    boolean verifyRole(UserSessionDTO user);
}
