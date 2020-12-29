package mas.emma.chatapi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import lombok.Getter;
import mas.emma.chatapi.services.BaseService;
import mas.emma.data.statictypes.constants.ChatAPIMessages;

/**
 * Created by jim on 4/19/17.
 */
public class Server extends BaseService {

    private final int ServerPort;
    @Getter
    public static volatile ArrayList<ServerWorker> WorkerList;

    public Server(int serverPort) {
        this.ServerPort = serverPort;
        this.WorkerList = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(ServerPort);
            while (true) {
                RunWithErrorHandeling(() -> {
                    WriteLine(ChatAPIMessages.CONNECTION_READY);
                    Socket clientSocket = serverSocket.accept();

                    WriteLine(ChatAPIMessages.CONNECTION_ACCEPTED + clientSocket);
                    ServerWorker worker = new ServerWorker(clientSocket);

                    WorkerList.add(worker);
                    worker.start();
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeWorker(ServerWorker serverWorker) {
        WorkerList.remove(serverWorker);
    }
}
