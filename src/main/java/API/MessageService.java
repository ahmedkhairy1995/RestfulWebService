package API;

import API.msgData.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service("messageService")
@Transactional
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserService userService;

    //Check if it is a new user
    public boolean sendMessage(Message message) {
        if(userService.existing(message.getSender()) && userService.existing(message.getReceiver())){
            messageRepository.save(message);
            return true;
        }
        return false;
    }
}
