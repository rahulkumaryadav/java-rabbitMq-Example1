import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;


public class Send{

    private final static String QUEUE_NAME="hello";

    public static void main(String[] args) {
        try {
            System.out.println("Create connection factory");
            ConnectionFactory connectionFactory=new ConnectionFactory();
            System.out.println("configuration of connection factory");
            connectionFactory.setHost("localhost");
            System.out.println("Connecting.... .... ....");
            Connection connection=connectionFactory.newConnection("Sender");
            System.out.println("Creating Channel");
            Channel channel=connection.createChannel();
            System.out.println("Declare queue");
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
//            channel.queueDeclare();
            System.out.println("Send Mesage");
            for(int i=0; i<=100;i++){
                String message=i*10 + "";
                channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
                System.out.println(" [x] Sent '" + message + "'");
                Thread.sleep(1000);
            }

            /*channel.close();
            connection.close();*/

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
