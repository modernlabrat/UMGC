import java.util.LinkedHashMap;

public class Discussion8 {
    public Discussion8() {
        Tokenizer tokenizer = new Tokenizer();
        LinkedHashMap<Integer, String> preTokens = tokenizer.getTokens();
        LinkedHashMap<Integer, String> tokens;

        int colonCount = tokenizer.getColonCount();

        if (isValid(preTokens, colonCount)) {
            tokens = preTokens;
            TDArrayBuilder arrayBuilder = new TDArrayBuilder(tokens);
        } else
            new Discussion8();
    }

    public boolean isValid(LinkedHashMap<Integer, String> preTokens, int colonCount) {
        if (preTokens.size() > 0 && colonCount == 1) {
            String lastToken = preTokens.get(preTokens.size());
            String lastCharacter = lastToken.substring(lastToken.length() - 1);

            if (!lastCharacter.equals(":"))
                return true;
            else
                System.out.println("The .txt file does not contain rows");
        } else 
            System.out.println("The .txt file does not one column only.");
        
        return false;
    }
}
