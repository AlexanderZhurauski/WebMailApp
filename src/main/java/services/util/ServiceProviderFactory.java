package services.util;

import services.ServiceProvider;
import services.api.util.IServiceProvider;

public class ServiceProviderFactory {
    private ServiceProviderFactory() {
    }

    public static IServiceProvider getInstance(ServiceType type) {
        switch (type) {
            case TYPE1:
                return ServiceProvider.getInstance();
            default:
                throw new IllegalArgumentException("You should provide one of the service type.");
        }
    }
}
