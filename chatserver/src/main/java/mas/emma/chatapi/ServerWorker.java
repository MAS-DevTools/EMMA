package mas.emma.chatapi;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import mas.emma.chatapi.services.BaseService;
import mas.emma.data.models.Auth;
import mas.emma.data.models.GroupMember;
import mas.emma.data.models.UserMaster;
import mas.emma.data.statictypes.constants.ApplicationConstants;
import mas.emma.data.statictypes.constants.ChatAPIMessages;
import mas.emma.data.statictypes.constants.ChatApiCommands;
import mas.emma.data.statictypes.enums.ProfileStatus;
import mas.emma.services.helpers.AES;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by jim on 4/18/17.
 */
public class ServerWorker extends BaseService {

    private final Socket clientSocket;

    //private String login = null;
    private OutputStream outputStream;
    @Getter
    private UserMaster user;

    public ServerWorker(Socket clientSocket) {

        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            handleClientSocket();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void handleClientSocket() throws IOException, InterruptedException {
        InputStream inputStream = clientSocket.getInputStream();
        this.outputStream = clientSocket.getOutputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String message;

        while ((message = reader.readLine()) != null) {
            String[] tokens = StringUtils.split(message);
            if (tokens != null && tokens.length > 0) {
                String cmd = tokens[0];

                if (ChatApiCommands.LOGOFF.equals(cmd) || ChatApiCommands.QUIT.equalsIgnoreCase(cmd)) {
                    handleLogoff();
                    break;
                } else if (ChatApiCommands.LOGIN.equalsIgnoreCase(cmd)) {
                    handleLogin(outputStream, tokens);
                } else if (ChatApiCommands.MESSAGE.equalsIgnoreCase(cmd)) {
                    String[] tokensMsg = StringUtils.split(message, null, 3);
                    handleMessage(tokensMsg);
                } else if (ChatApiCommands.JOIN_GROUP.equalsIgnoreCase(cmd)) {
                    handleJoin(tokens);
                } else if (ChatApiCommands.LEAVE_GROUP.equalsIgnoreCase(cmd)) {
                    handleLeave(tokens);
                } else if (ChatApiCommands.SEND_STATUS_CHANGED.equalsIgnoreCase(cmd)) {
                    handleStatusChanged(tokens);
                } else {
                    String msg = ChatAPIMessages.UNKNOWN_MESSAGE + cmd + "\n";
                    outputStream.write(msg.getBytes());
                }
            }
        }

        clientSocket.close();
    }

    private void handleStatusChanged(String[] tokens) {
        if (tokens.length > 1) {
            ProfileStatus status = ProfileStatus.valueOf(tokens[1]);
            this.user = changeStatus(status);
        }
    }

    private void handleLeave(String[] tokens) {
        if (tokens.length > 1) {
            String topic = tokens[1];

            user.getTopics().forEach(x -> {
                if (x.getGroup_id().equals(topic)) {
                    this.user = removeTopic(x);
                    return;
                }
            });

        }
    }

    public boolean isMemberOfTopic(String topic) {
        for (GroupMember group : user.getTopics()) {
            if (group.getGroup_id().contains(topic)) {
                return true;
            }
        }
        return false;
    }

    private void handleJoin(String[] tokens) {
        if (tokens.length > 1) {
            String topic = tokens[1];
            GroupMember group = new GroupMember();
            this.user = addTopic(group);
        }
    }

    private void handleMessage(String[] tokens) throws IOException {
        String sendTo = tokens[1];
        String body = tokens[2];
        saveMessage(this.user.getId(), sendTo, body, false);
        boolean isTopic = sendTo.charAt(0) == ChatApiCommands.TOPIC_SIGN;

        List<ServerWorker> workerList = Server.getWorkerList();
        for (ServerWorker worker : workerList) {
            if (isTopic) {
                if (worker.isMemberOfTopic(sendTo)) {
                    String outMsg = ChatApiCommands.MESSAGE + ChatApiCommands.SEPERATOR + sendTo
                            + ChatApiCommands.SEPERATOR2 + user.getId() + ChatApiCommands.SEPERATOR + body + ApplicationConstants.NEWLINE;
                    worker.send(outMsg);
                }
            } else {
                if (sendTo.equalsIgnoreCase(worker.getUserId())) {
                    String outMsg = ChatApiCommands.MESSAGE + ChatApiCommands.SEPERATOR + ChatApiCommands.SEPERATOR2 + user.getId() + ChatApiCommands.SEPERATOR + body + ApplicationConstants.NEWLINE;
                    worker.send(outMsg);
                }
            }
        }
    }

    private void handleLogoff() throws IOException {
        Server.removeWorker(this);
        List<ServerWorker> workerList = Server.getWorkerList();

        // send other online users current user's status
        String onlineMsg = ChatApiCommands.STATUS_CHANGED_RECEIVED + ChatApiCommands.SEPERATOR
                + this.user.getUserDetails().getProfile_status() + ChatApiCommands.SEPERATOR
                + this.user.getId() + ApplicationConstants.NEWLINE;
        for (ServerWorker worker : workerList) {
            if (!this.user.getId().equals(worker.getUser().getId())) {
                worker.send(onlineMsg);
            }
        }
        clientSocket.close();
    }

    public String getUserId() {
        return this.user.getId();
    }

    private void handleLogin(OutputStream outputStream, String[] tokens) throws IOException {
        if (tokens.length == 3) {
            String id = tokens[1];
            String password = tokens[2];

            if (isAuthenticated(id, password)) {
                String msg = ChatApiCommands.LOGIN + ApplicationConstants.SPACE + ChatApiCommands.SUCCESFULL + ApplicationConstants.NEWLINE;
                outputStream.write(msg.getBytes());

                WriteLine(ChatAPIMessages.LOGIN_SUCCESFUL + user.getId());

                List<ServerWorker> workerList = Server.getWorkerList();

                // send current user all other online logins
                for (ServerWorker worker : workerList) {
                    if (worker.getUserId() != null) {
                        if (!this.user.getId().equals(worker.getUserId())) {
                            String msg2 = worker.getUser().getUserDetails().getProfile_status().name()
                                    + ApplicationConstants.SPACE
                                    + worker.getUserId()
                                    + ApplicationConstants.NEWLINE;
                            send(msg2);
                        }
                    }
                }

                // send other online users current user's status
                String onlineMsg = this.user.getUserDetails().getProfile_status().name()
                        + ApplicationConstants.SPACE
                        + this.getUserId()
                        + ApplicationConstants.NEWLINE;
                for (ServerWorker worker : workerList) {
                    if (!this.user.getId().equals(worker.getUserId())) {
                        worker.send(onlineMsg);
                    }
                }
            } else {
                String msg = ChatApiCommands.ERROR
                        + ApplicationConstants.SPACE
                        + ChatApiCommands.LOGIN
                        + ApplicationConstants.NEWLINE;
                outputStream.write(msg.getBytes());
                WriteLine(ChatAPIMessages.LOGIN_FAILED + this.user.getEmail());
            }
        }
    }

    private void send(String msg) throws IOException {
        if (user != null) {
            try {
                outputStream.write(msg.getBytes());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private UserMaster removeTopic(GroupMember topic) {
        return null;
    }

    private UserMaster addTopic(GroupMember topic) {
        return null;
    }

    private UserMaster changeStatus(ProfileStatus status) {
        return null;
    }

    private void saveMessage(String id, String sendTo, String body, boolean receiverRead) {

    }

    private boolean isAuthenticated(String email, String password) {
        String key = UUID.randomUUID().toString();
        try ( AES Aes = new AES(key)) { // set new GUIDKey
            Auth auth = new Auth();
            String new_Auth_token = Aes.encrypt(Aes.encrypt(password));
            auth.setAuth(new_Auth_token);
            auth.setAuth_key(key);
            return userMasterServiceInstance().isAuthenticated(null, email, auth);
        } catch (Exception ex) {
            WriteLine(ex.getMessage());
        }
        return true;
    }
}
