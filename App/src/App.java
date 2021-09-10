
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class App {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        try {

            FileInputStream file = new FileInputStream("input.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            FileWriter fileWriter = new FileWriter("output.txt");
            String line;

            while ((line = reader.readLine()) != null) {

                if (checkLine(line)) {

                    fileWriter.write(line + " - " + "Válido\n");
                }

                else {

                    fileWriter.write(line + " - " + "Inválido\n");
                }
            }

            fileWriter.close();
            reader.close();

        } catch (Exception e) {

            System.out.println("Ocorreu um erro ler o arquivo de entrada: " + e);
        }
    }

    public static boolean checkLine(String line) {

        Stack<Character> symbol = new Stack<Character>();
        boolean isValid = false;

        try {

            for (int i = 0; i < line.length(); i++) {

                if (line.charAt(i) == '(' || line.charAt(i) == '[' || line.charAt(i) == '{') {

                    symbol.push(line.charAt(i));
                }

                else {

                    if (symbol.isEmpty()) {
                        isValid = false;
                        break;
                    }

                    else if (line.charAt(i) == ')' && symbol.peek() != '('
                            || line.charAt(i) == ']' && symbol.peek() != '['
                            || line.charAt(i) == '}' && symbol.peek() != '{') {
                                
                        isValid = false;
                        break;
                    }

                    symbol.pop();

                }

                if (i == line.length() - 1 && symbol.isEmpty())
                    isValid = true;
            }

            return isValid;

        } catch (Exception e) {

            System.out.println("Ocorreu um erro verificar os dados do arquivo de entrada: " + e);
            return false;
        }
    }
}
