package services.api;

import java.util.List;

public interface IAdminStatisticService {

    int getMessageStatistic();

    List<String> getOnlineUsers();

    List<String> getUserStatistic();

}
