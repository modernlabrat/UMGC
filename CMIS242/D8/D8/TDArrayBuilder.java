import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

public class TDArrayBuilder {
    private LinkedHashMap<Integer, String> tokens;
    private String[] columns;
    private String[][] result;
    private int noColonCount;

    public TDArrayBuilder(LinkedHashMap<Integer, String> tokens) {
        this.tokens = tokens;
        noColonCount = 0;
        
        result = build();
        display(result);
    }
    
    public String[][] build() {
        int columnCount = 0;
        int rowIndex = 0;
        int rowCount = 0;
        int groupedRows = 0;
        ArrayList<String[]> rows = new ArrayList<String[]>();

        for (int i = 1; i < tokens.size(); i++) {
            String token = tokens.get(i);
            String lastCharacter = token.substring(token.length() - 1);

            if (lastCharacter.equals(":")) {
                columns = new String[noColonCount + 1];
                int key = 1;
                for (int y = 1; y <= noColonCount + 1; y++) {
                    columns[y - 1] = tokens.get(key);
                    key++;
                }

            } else
                noColonCount++;
        }

        int rowDataSize = tokens.size() - columns.length;
        String[] rowData = new String[rowDataSize];

        for (int z = 1; z <= tokens.size(); z++) {
            String token = tokens.get(z);

            boolean contains = Arrays.stream(columns).anyMatch(token::equals);
            if (!contains) {
                rowData[rowIndex] = token;
                rowIndex++;
            } else
                columnCount++;
        }

        int rowSize = columnCount;
        String[] row = new String[rowSize];

        for (int i = 1; i <= rowData.length; i++) {
            row[rowCount] = rowData[i - 1];
            rowCount++;

            if (rowCount == columnCount) {
                rows.add(row);
                rowCount = 0;
                row = new String[rowSize];
            }
        }

        for (int x = 0; x <= rows.size(); x++)
            groupedRows++;

        result = new String[groupedRows][];

        String lastColumn = columns[columns.length - 1];
        columns[columns.length - 1] = lastColumn.replace(":", "");

        result[0] = columns;

        for (int i = 1; i < groupedRows; i++)
            result[i] = rows.get(i - 1);

        return result;
    }

    public void display(String[][] tDArray) {        
        for (int i = 0; i < tDArray.length; i++) {
            for (int j = 0; j < tDArray[i].length; j++)
                System.out.print(tDArray[i][j] + " ");
            System.out.println();
        }

    }
}
