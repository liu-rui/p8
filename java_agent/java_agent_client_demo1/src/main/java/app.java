/**
 * @author liu-rui
 * @date 2020/3/30 下午2:58
 * @description
 * @since
 *
 * -javaagent:./java_agent/java_agent_server_demo1/target/java_agent_server_demo1-1.0-SNAPSHOT.jar
 *
 */
public class app {
    public static void main(String[] args) {
        System.out.println("hello");
        System.out.println(System.getProperty("user.dir"));
    }
}
