package dao.util;

import dao.api.util.IDAOProvider;

public class DAOProviderFactory {
    private DAOProviderFactory() {
    }

    public static IDAOProvider getInstance(DAOType type) {
        switch (type) {
            case MEMORY:
                return DAOProvider.getInstance();
            default:
                throw new IllegalArgumentException("You should provide one of the DAO type.");
        }

    }
}
