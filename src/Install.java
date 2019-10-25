import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Install {
    
    public static final int YES = 0;
    public static final int NO = 1;
    public static final int QUIT = 2;
    
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
    
    public int askUserYesNoQuestion(String question) throws IOException {
        String userResponse = null;
        int answer = -1;
        while (answer < 0) {
            System.out.print(question + " (y/n) ");
            userResponse = stdin.readLine().trim().toLowerCase();
            if (userResponse.equals("y")) {
                answer = YES;
            } else if (userResponse.equals("n")) {
                answer = NO;
            } else if (userResponse.equals("q")) {
                answer = QUIT;
            }
        }
        return answer;
    }
    
    public void install() throws IOException {
        
        int answer = askUserYesNoQuestion("Install for all users or just this user?");
        if (answer == YES) {
            
        }
        
        
    }

}
