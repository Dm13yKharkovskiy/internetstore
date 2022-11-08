package service;

import model.Code;

public interface MessageService {
    void sendMessage(Code code, String email);
}
