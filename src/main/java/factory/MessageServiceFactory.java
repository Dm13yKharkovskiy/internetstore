package factory;

import dao.UserDao;
import dao.impl.UserDaoJDBCImpl;
import service.MessageService;
import service.impl.MessageServiceImpl;

public class MessageServiceFactory {
    private MessageServiceFactory() {
    }

    private static volatile MessageService messageService;

    static {
        getInstance();
    }
    public static MessageService getMessageService() {
        return messageService;
    }

    private static synchronized MessageService getInstance() {
        MessageService localInstance = messageService;
        if (localInstance == null) {
            synchronized (UserDao.class) {
                localInstance = messageService;
                if (localInstance == null) {
                    messageService = localInstance = new MessageServiceImpl();
                }
            }
        }
        return localInstance;
    }
}
