import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Install {
    
    protected static BufferedReader stdin;

    public Install() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        try {
            stdin = new BufferedReader(new InputStreamReader(System.in));
            Install installer = new Install();
            installer.install();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void install() {
        
        
        
        String userResponse = null;
        while (userResponse == null && !userResponse.equals("y") && !userResponse.equals("n")) {
            System.out.print("Install for all users or just for this user? (y/n) ");
            userResponse = stdin.readLine().trim().toLowerCase();
    }

}
